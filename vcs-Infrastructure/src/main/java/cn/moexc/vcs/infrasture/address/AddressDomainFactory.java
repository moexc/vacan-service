package cn.moexc.vcs.infrasture.address;

import cn.moexc.vcs.domain.address.AddressDomain;
import cn.moexc.vcs.infrasture.jpa.entity.AddressDeliveryEntity;

public class AddressDomainFactory {
    public static AddressDomain genDomain(AddressDeliveryEntity entity){
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

    public static AddressDeliveryEntity genEntity(AddressDomain domain){
        AddressDeliveryEntity entity = new AddressDeliveryEntity();
        entity.setId(domain.getId());
        entity.setCustomerId(domain.getCustomerId());
        entity.setCity(domain.getCity());
        entity.setDetailed(domain.getDetailed());
        entity.setPostCode(domain.getPostCode());
        entity.setIsdefault(domain.getIsdefault());
        return entity;
    }
}
