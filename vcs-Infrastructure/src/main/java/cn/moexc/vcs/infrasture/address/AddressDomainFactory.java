package cn.moexc.vcs.infrasture.address;

import cn.moexc.vcs.domain.address.AddressDomain;
import cn.moexc.vcs.infrasture.mybatis.entity.AddressDelivery;

public class AddressDomainFactory {
    public static AddressDomain genDomain(AddressDelivery entity){
        if (entity == null) return null;
        AddressDomain domain = new AddressDomain();
        domain.setId(entity.getId());
        domain.setCustomerId(entity.getCustomerId());
        domain.setCity(entity.getCity());
        domain.setDetailed(entity.getDetailed());
        domain.setPostCode(entity.getPostCode());
        domain.setIsdefault(entity.getIsdefault());
        return domain;
    }

    public static AddressDelivery genEntity(AddressDomain domain){
        AddressDelivery entity = new AddressDelivery();
        entity.setId(domain.getId());
        entity.setCustomerId(domain.getCustomerId());
        entity.setCity(domain.getCity());
        entity.setDetailed(domain.getDetailed());
        entity.setPostCode(domain.getPostCode());
        entity.setIsdefault(domain.getIsdefault());
        return entity;
    }
}
