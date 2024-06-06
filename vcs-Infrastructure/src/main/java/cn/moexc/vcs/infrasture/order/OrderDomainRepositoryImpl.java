package cn.moexc.vcs.infrasture.order;

import cn.moexc.vcs.domain.order.OrderDomain;
import cn.moexc.vcs.domain.order.OrderDomainRepository;
import cn.moexc.vcs.infrasture.mybatis.entity.Indent;
import cn.moexc.vcs.infrasture.mybatis.mapper.IndentMapper;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDomainRepositoryImpl implements OrderDomainRepository {

    private final IndentMapper indentMapper;
    private final RocketMQTemplate rocketMQTemplate;

    public OrderDomainRepositoryImpl(IndentMapper indentMapper, RocketMQTemplate rocketMQTemplate) {
        this.indentMapper = indentMapper;
        this.rocketMQTemplate = rocketMQTemplate;
    }

    @Override
    public OrderDomain byId(String id) {
        Indent indent = indentMapper.selectByPrimaryKey(id);
        return OrderDomainFactory.genDomain(indent);
    }

    @Override
    public void save(OrderDomain domain) {
        Indent indent = indentMapper.selectByPrimaryKey(domain.getId());
        final boolean orderExists = indent != null;
        final Indent indentNew = OrderDomainFactory.genEntity(domain);

        if (orderExists){
            indentMapper.updateByPrimaryKey(OrderDomainFactory.genEntity(domain));
        }else {
            indentMapper.insert(indentNew);
            rocketMQTemplate.syncSend("topic-order-delay", MessageBuilder.withPayload(domain.getId()).build(), 3000, 14);
        }

    }
}
