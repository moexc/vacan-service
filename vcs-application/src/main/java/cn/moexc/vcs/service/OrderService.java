package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.order.CreateOrderCommand;
import cn.moexc.vcs.domain.order.OrderDomain;
import cn.moexc.vcs.domain.order.OrderDomainRepository;
import cn.moexc.vcs.infrasture.queryresult.Goods4CreateOrder;
import cn.moexc.vcs.service.cmdfactory.CreaterOrderCmdFactory;
import cn.moexc.vcs.service.dto.CreateOrderDTO;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RocketMQMessageListener(consumerGroup = "order-delay", topic = "topic-order-delay", selectorExpression = "*")
public class OrderService implements RocketMQListener<String> {

    private final OrderDomainRepository orderDomainRepository;

    public OrderService(OrderDomainRepository orderDomainRepository) {
        this.orderDomainRepository = orderDomainRepository;
    }

    /**
     * 创建订单
     * @param createOrderDTO et
     * @param cid 操作人（买方）
     * @return 订单ID
     */
    @Transactional(rollbackFor = Exception.class)
    public String create(CreateOrderDTO createOrderDTO, String cid){
//        OrderDomain orderDomain = new OrderDomain();
//        final Goods4CreateOrder goods4CreateOrder = goodsEntityRepository.selectGoods4CreateOrder(createOrderDTO.getShopId());
//        CreateOrderCommand cmd = CreaterOrderCmdFactory.gen(createOrderDTO, goods4CreateOrder, cid);
//        String orderId = orderDomain.create(cmd);
//        orderDomainRepository.save(orderDomain);
//        return orderId;
        return null;
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
