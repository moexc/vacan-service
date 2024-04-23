package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.domain.address.AddressDomain;
import cn.moexc.vcs.domain.address.AddressDomainRepository;
import cn.moexc.vcs.domain.address.CreateAddressCommand;
import cn.moexc.vcs.infrasture.jpa.entity.AddressDeliveryEntity;
import cn.moexc.vcs.infrasture.jpa.repository.AddressDeliveryEntityRepository;
import cn.moexc.vcs.service.cmdfactory.CreateAddressCmdFacroty;
import cn.moexc.vcs.service.dto.CreateAddressDTO;
import cn.moexc.vcs.service.vo.AddressVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    private final AddressDeliveryEntityRepository addressDeliveryEntityRepository;
    private final AddressDomainRepository addressDomainRepository;

    @Autowired
    public AddressService(AddressDeliveryEntityRepository addressDeliveryEntityRepository, AddressDomainRepository addressDomainRepository) {
        this.addressDeliveryEntityRepository = addressDeliveryEntityRepository;
        this.addressDomainRepository = addressDomainRepository;
    }

    public List<AddressVO> list(String cid){
        List<AddressDeliveryEntity> addressDeliveryEntities = addressDeliveryEntityRepository.findByCustomerIdOrderByIsdefaultDescCityAsc(cid);
        return addressDeliveryEntities.stream().map(AddressVO::gen).collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(CreateAddressDTO dto, String cid){
        int count = addressDeliveryEntityRepository.countByCustomerId(cid);
        if (count >= 12){
            throw new AlterException("最多添加12条收货地址");
        }
        CreateAddressCommand cmd = CreateAddressCmdFacroty.gen(dto, cid);
        AddressDomain addressDomain = new AddressDomain();
        addressDomain.create(cmd);
        if (addressDomain.isDefault()){
            AddressDomain beforeDefault = addressDomainRepository.findIsDefault();
            if (beforeDefault != null){
                beforeDefault.unDefault();
                addressDomainRepository.save(beforeDefault);
            }
        }
        addressDomainRepository.save(addressDomain);
    }

    @Transactional(rollbackFor = Exception.class)
    public void setDefault(String id){
        AddressDomain beforeDefault = addressDomainRepository.findIsDefault();
        beforeDefault.unDefault();
        addressDomainRepository.save(beforeDefault);

        AddressDomain afterDefault = addressDomainRepository.byId(id);
        afterDefault.setDefault();
        addressDomainRepository.save(afterDefault);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String id){
        addressDomainRepository.delete(id);
    }


}
