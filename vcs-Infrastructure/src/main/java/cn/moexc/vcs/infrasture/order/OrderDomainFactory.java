package cn.moexc.vcs.infrasture.order;

import cn.moexc.vcs.domain.order.OrderDomain;
import cn.moexc.vcs.infrasture.jpa.entity.IndentEntity;
import org.springframework.beans.BeanUtils;

public class OrderDomainFactory{
    public static OrderDomain genDomain(IndentEntity indentEntity, Integer goodsQuantity) {
        OrderDomain domain = new OrderDomain();
        BeanUtils.copyProperties(indentEntity, domain);
        domain.setGoodsQuantity(goodsQuantity);
        return domain;
    }

    public static IndentEntity genEntity(OrderDomain orderDomain) {
        IndentEntity entity = new IndentEntity();
        BeanUtils.copyProperties(orderDomain, entity);
        return entity;
    }
}
