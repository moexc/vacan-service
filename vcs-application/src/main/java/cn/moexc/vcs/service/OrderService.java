package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.order.CreateOrderCommand;
import cn.moexc.vcs.domain.order.OrderDomain;
import cn.moexc.vcs.domain.order.OrderDomainRepository;
import cn.moexc.vcs.infrasture.jpa.entity.IndentEntity;
import cn.moexc.vcs.infrasture.jpa.entity.queryresult.Goods4CreateOrder;
import cn.moexc.vcs.infrasture.jpa.repository.GoodsEntityRepository;
import cn.moexc.vcs.infrasture.jpa.repository.IndentEntityRepository;
import cn.moexc.vcs.service.cmdfactory.CreaterOrderCmdFactory;
import cn.moexc.vcs.service.dto.CreateOrderDTO;
import cn.moexc.vcs.service.dto.SearchOrderDTO;
import cn.moexc.vcs.service.vo.OrderVO;
import cn.moexc.vcs.service.vo.PageResult;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RocketMQMessageListener(consumerGroup = "order-delay", topic = "topic-order-delay", selectorExpression = "*")
public class OrderService implements RocketMQListener<String> {

    private final OrderDomainRepository orderDomainRepository;
    private final GoodsEntityRepository goodsEntityRepository;
    private final IndentEntityRepository indentEntityRepository;

    public OrderService(OrderDomainRepository orderDomainRepository,
                        GoodsEntityRepository goodsEntityRepository,
                        IndentEntityRepository indentEntityRepository) {
        this.orderDomainRepository = orderDomainRepository;
        this.goodsEntityRepository = goodsEntityRepository;
        this.indentEntityRepository = indentEntityRepository;
    }

    /**
     * 创建订单
     * @param createOrderDTO et
     * @param cid 操作人（买方）
     * @return 订单ID
     */
    @Transactional(rollbackFor = Exception.class)
    public String create(CreateOrderDTO createOrderDTO, String cid){
        OrderDomain orderDomain = new OrderDomain();
        final Goods4CreateOrder goods4CreateOrder = goodsEntityRepository.selectGoods4CreateOrder(createOrderDTO.getShopId());
        CreateOrderCommand cmd = CreaterOrderCmdFactory.gen(createOrderDTO, goods4CreateOrder, cid);
        String orderId = orderDomain.create(cmd);
        orderDomainRepository.save(orderDomain);
        return orderId;
    }

    public PageResult<OrderVO> selectList(String cid, SearchOrderDTO searchOrderDTO, Integer page, Integer rows){

        Specification<IndentEntity> specification = new Specification<IndentEntity>() {
            @Override
            public Predicate toPredicate(Root<IndentEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (!StringUtils.isEmpty(searchOrderDTO.getTitle())){
                    predicates.add(criteriaBuilder.like(root.get("title"), "%"+ searchOrderDTO.getTitle() +"%"));
                }
                if (!StringUtils.isEmpty(searchOrderDTO.getStatus())){
                    predicates.add(criteriaBuilder.equal(root.get("status"), searchOrderDTO.getStatus()));
                }
                if (searchOrderDTO.getCreateTimeRangeBefore() != null){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createTime") ,searchOrderDTO.getCreateTimeRangeBefore()));
                }
                if (searchOrderDTO.getCreateTimeRangeAfter() != null){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createTime"), searchOrderDTO.getCreateTimeRangeAfter()));
                }
                if (searchOrderDTO.getPayTimeRangeBefore() != null){
                    predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("payTime"), searchOrderDTO.getPayTimeRangeBefore()));
                }
                if (searchOrderDTO.getPayTimeRangeAfter() != null){
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("payTime"), searchOrderDTO.getPayTimeRangeAfter()));
                }

                if (!"0".equals(cid)){
                    predicates.add(criteriaBuilder.equal(root.get("customerId"), cid));
                    predicates.add(criteriaBuilder.notEqual(root.get("status"), "06"));
                }

                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime")));
                return criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        };
        Pageable pageable = PageRequest.of(page - 1, rows);
        Page<IndentEntity> source = indentEntityRepository.findAll(specification, pageable);

        PageResult<OrderVO> pageResult = new PageResult<>();
        pageResult.setTotal(source.getTotalElements());
        pageResult.setData(source.getContent().stream().map(OrderVO::gen).collect(Collectors.toList()));

        return pageResult;
    }

    /**
     * 取消订单
     * @param orderId 订单ID
     * @param cid 操作人
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelOne(String orderId, String cid){
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);
        orderDomain.cancel(cid);
        orderDomainRepository.save(orderDomain);
    }

    /**
     * 发货
     * @param orderId 订单ID
     */
    @Transactional(rollbackFor = Exception.class)
    public void deliver(String orderId){
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);
        orderDomain.deliver();
        orderDomainRepository.save(orderDomain);
    }

    /**
     * 删除
     * @param orderId 订单ID
     * @param cid 操作人
     */
    public void deleteOne(String orderId, String cid){
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);
        orderDomain.delete(cid);
        orderDomainRepository.save(orderDomain);
    }

    /**
     * 收货
     * @param orderId 订单ID
     */
    public void accept(String orderId) {
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);
        orderDomain.accept();
        orderDomainRepository.save(orderDomain);
    }

    /**
     * 接收到订单超时推送
     * @param orderId 订单ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void onMessage(String orderId) {
        OrderDomain orderDomain = orderDomainRepository.byId(orderId);
        if (!"00".equals(orderDomain.getStatus())){
            return;
        }
        orderDomain.expired();
        orderDomainRepository.save(orderDomain);
    }
}
