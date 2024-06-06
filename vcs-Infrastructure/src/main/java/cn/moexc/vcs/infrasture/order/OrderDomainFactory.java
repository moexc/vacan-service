package cn.moexc.vcs.infrasture.order;

import cn.moexc.vcs.domain.order.OrderDomain;
import cn.moexc.vcs.infrasture.mybatis.entity.Indent;
import org.springframework.beans.BeanUtils;

public class OrderDomainFactory{
    public static OrderDomain genDomain(Indent indentEntity) {
        OrderDomain domain = new OrderDomain();
        BeanUtils.copyProperties(indentEntity, domain);
        return domain;
    }

    public static Indent genEntity(OrderDomain orderDomain) {
        Indent entity = new Indent();
        BeanUtils.copyProperties(orderDomain, entity);
        return entity;
    }
}
