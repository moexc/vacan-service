package cn.moexc.vcs.infrasture.order;

import cn.moexc.vcs.domain.order.OrderDomain;
import cn.moexc.vcs.domain.order.OrderDomainRepository;
import cn.moexc.vcs.infrasture.mybatis.entity.Goods;
import cn.moexc.vcs.infrasture.mybatis.entity.Indent;
import cn.moexc.vcs.infrasture.mybatis.mapper.GoodsMapper;
import cn.moexc.vcs.infrasture.mybatis.mapper.IndentMapper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDomainRepositoryImpl implements OrderDomainRepository {

    private final IndentMapper indentMapper;
    private final GoodsMapper goodsMapper;
    private final RocketMQTemplate rocketMQTemplate;

    public OrderDomainRepositoryImpl(IndentMapper indentMapper, GoodsMapper goodsMapper, RocketMQTemplate rocketMQTemplate) {
        this.indentMapper = indentMapper;
        this.goodsMapper = goodsMapper;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @Override
    public OrderDomain byId(String id) {
        Indent indent = indentMapper.selectByPrimaryKey(id);
        Goods goods = goodsMapper.selectByPrimaryKey(indent.getGoodsId());
        return OrderDomainFactory.genDomain(indent, goods.getQuantity());
    }

    @Override
    public void save(OrderDomain domain) {
        Indent indent = indentMapper.selectByPrimaryKey(domain.getId());
        final boolean orderExists = indent != null;
        if (!orderExists || "04".equals(domain.getStatus()) || "05".equals(domain.getStatus())){
            Goods goods = goodsMapper.selectByPrimaryKey(domain.getGoodsId());
            goods.setQuantity(domain.getGoodsQuantity());
            goodsMapper.updateByPrimaryKey(goods);
        }
        indentMapper.updateByPrimaryKey(OrderDomainFactory.genEntity(domain));
        if (!orderExists){
            rocketMQTemplate.syncSend("topic-order-delay", MessageBuilder.withPayload(domain.getId()).build(), 3000, 14);
        }
    }
}
