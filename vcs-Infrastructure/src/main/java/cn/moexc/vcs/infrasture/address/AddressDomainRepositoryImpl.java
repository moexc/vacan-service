package cn.moexc.vcs.infrasture.address;

import cn.moexc.vcs.domain.address.AddressDomain;
import cn.moexc.vcs.domain.address.AddressDomainRepository;
import cn.moexc.vcs.infrasture.mybatis.entity.AddressDelivery;
import cn.moexc.vcs.infrasture.mybatis.mapper.AddressDeliveryMapper;
import org.springframework.stereotype.Repository;

@Repository
public class AddressDomainRepositoryImpl implements AddressDomainRepository {

    private final AddressDeliveryMapper addressDeliveryMapper;

    public AddressDomainRepositoryImpl(AddressDeliveryMapper addressDeliveryMapper) {
        this.addressDeliveryMapper = addressDeliveryMapper;
    }


    @Override
    public AddressDomain byId(String s) {
        AddressDelivery entity = addressDeliveryMapper.selectByPrimaryKey(s);
        return AddressDomainFactory.genDomain(entity);
    }

    @Override
    public void save(AddressDomain domain) {
        AddressDelivery newEntity = AddressDomainFactory.genEntity(domain);
        AddressDelivery entity = addressDeliveryMapper.selectByPrimaryKey(domain.getId());
        if (entity == null) addressDeliveryMapper.insert(newEntity);
        else addressDeliveryMapper.updateByPrimaryKey(newEntity);
    }

    @Override
    public AddressDomain findIsDefault() {
        return AddressDomainFactory.genDomain(addressDeliveryMapper.findDefault());
    }

    @Override
    public void delete(String id) {
        addressDeliveryMapper.deleteByPrimaryKey(id);
    }
}
