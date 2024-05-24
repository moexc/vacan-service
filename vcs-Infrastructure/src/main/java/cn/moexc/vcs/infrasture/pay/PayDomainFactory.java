package cn.moexc.vcs.infrasture.pay;

import cn.moexc.vcs.domain.pay.PayDomain;
import cn.moexc.vcs.infrasture.mybatis.entity.Indent;
import cn.moexc.vcs.infrasture.mybatis.entity.PayInfo;

public class PayDomainFactory {

    public static PayDomain genDomain(Indent indentEntity, PayInfo payInfoEntity, String payTimeout){
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

    public static PayInfo genEntity(PayDomain payDomain){
        PayInfo payInfoEntity = new PayInfo();
        payInfoEntity.setOrderId(payDomain.getOrderId());
        payInfoEntity.setMode(payDomain.getPayMode());
        payInfoEntity.setContent(payDomain.getContent());
        return payInfoEntity;
    }

}
