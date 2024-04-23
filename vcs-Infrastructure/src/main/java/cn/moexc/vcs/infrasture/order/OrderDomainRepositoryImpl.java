package cn.moexc.vcs.infrasture.order;

import cn.moexc.vcs.domain.order.OrderDomain;
import cn.moexc.vcs.domain.order.OrderDomainRepository;
import cn.moexc.vcs.infrasture.jpa.entity.GoodsEntity;
import cn.moexc.vcs.infrasture.jpa.entity.IndentEntity;
import cn.moexc.vcs.infrasture.jpa.repository.GoodsEntityRepository;
import cn.moexc.vcs.infrasture.jpa.repository.IndentEntityRepository;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderDomainRepositoryImpl implements OrderDomainRepository {

    private final IndentEntityRepository indentEntityRepository;
    private final GoodsEntityRepository goodsEntityRepository;
    private final RocketMQTemplate rocketMQTemplate;

    public OrderDomainRepositoryImpl(IndentEntityRepository indentEntityRepository,
                                     GoodsEntityRepository goodsEntityRepository,
                                     RocketMQTemplate rocketMQTemplate) {
        this.indentEntityRepository = indentEntityRepository;
        this.goodsEntityRepository = goodsEntityRepository;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @Override
    public OrderDomain byId(String id) {
        Optional<IndentEntity> indentEntityOptional = indentEntityRepository.findById(id);
        IndentEntity indentEntity = indentEntityOptional.orElseThrow(() -> new RuntimeException("获取订单信息失败"));
        Optional<GoodsEntity> goodsEntityOptional = goodsEntityRepository.findById(indentEntity.getGoodsId());
        GoodsEntity goodsEntity = goodsEntityOptional.orElseThrow(() -> new RuntimeException("获取商品信息失败"));
        return OrderDomainFactory.genDomain(indentEntity, goodsEntity.getQuantity());
    }

    @Override
    public void save(OrderDomain domain) {
        final boolean orderExists = indentEntityRepository.existsById(domain.getId());
        if (!orderExists || "04".equals(domain.getStatus()) || "05".equals(domain.getStatus())){
            GoodsEntity goodsEntity = goodsEntityRepository.findById(domain.getGoodsId()).orElseThrow(()-> new RuntimeException("获取商品信息失败"));
            goodsEntity.setQuantity(domain.getGoodsQuantity());
            goodsEntityRepository.save(goodsEntity);
        }
        indentEntityRepository.save(OrderDomainFactory.genEntity(domain));
        if (!orderExists){
            rocketMQTemplate.syncSend("topic-order-delay", MessageBuilder.withPayload(domain.getId()).build(), 3000, 14);
        }
    }
}
