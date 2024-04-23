package cn.moexc.vcs.infrasture.address;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.domain.address.AddressDomain;
import cn.moexc.vcs.domain.address.AddressDomainRepository;
import cn.moexc.vcs.infrasture.jpa.entity.AddressDeliveryEntity;
import cn.moexc.vcs.infrasture.jpa.repository.AddressDeliveryEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AddressDomainRepositoryImpl implements AddressDomainRepository {

    private final AddressDeliveryEntityRepository addressDeliveryEntityRepository;

    @Autowired
    public AddressDomainRepositoryImpl(AddressDeliveryEntityRepository addressDeliveryEntityRepository) {
        this.addressDeliveryEntityRepository = addressDeliveryEntityRepository;
    }

    @Override
    public AddressDomain byId(String s) {
        Optional<AddressDeliveryEntity> optionalAddressDelivery = addressDeliveryEntityRepository.findById(s);
        AddressDeliveryEntity addressDelivery = optionalAddressDelivery.orElseThrow(() -> new AlterException("获取地址失败"));
        return AddressDomainFactory.genDomain(addressDelivery);
    }

    @Override
    public void save(AddressDomain domain) {
        AddressDeliveryEntity addressDelivery = AddressDomainFactory.genEntity(domain);
        addressDeliveryEntityRepository.save(addressDelivery);
    }

    @Override
    public AddressDomain findIsDefault() {
        AddressDeliveryEntity addressDelivery = addressDeliveryEntityRepository.findByIsdefault("1");
        return AddressDomainFactory.genDomain(addressDelivery);
    }

    @Override
    public void delete(String id) {
        addressDeliveryEntityRepository.deleteById(id);
    }
}
