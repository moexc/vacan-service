package cn.moexc.vcs.infrasture.pay;

import cn.moexc.vcs.domain.pay.PayDomain;
import cn.moexc.vcs.infrasture.jpa.entity.IndentEntity;
import cn.moexc.vcs.infrasture.jpa.entity.PayInfoEntity;

public class PayDomainFactory {

    public static PayDomain genDomain(IndentEntity indentEntity, PayInfoEntity payInfoEntity, String payTimeout){
        PayDomain payDomain = new PayDomain();
        payDomain.setOrderId(indentEntity.getId());
        payDomain.setOrderTitle(indentEntity.getTitle());
        payDomain.setShopName(indentEntity.getTitle());
        payDomain.setCount(indentEntity.getQuantity().longValue());
        payDomain.setPayMoney(indentEntity.getAmount());
        payDomain.setTimeoutExpress(payTimeout);
        if (payInfoEntity != null){
            payDomain.setPayMode(payInfoEntity.getMode());
            payDomain.setContent(payInfoEntity.getContent());
        }
        return payDomain;
    }

    public static PayInfoEntity genEntity(PayDomain payDomain){
        PayInfoEntity payInfoEntity = new PayInfoEntity();
        payInfoEntity.setOrderId(payDomain.getOrderId());
        payInfoEntity.setMode(payDomain.getPayMode());
        payInfoEntity.setContent(payDomain.getContent());
        return payInfoEntity;
    }

}
