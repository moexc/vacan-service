package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.address.AddressDomain;
import cn.moexc.vcs.domain.address.AddressDomainRepository;
import cn.moexc.vcs.domain.address.CreateAddressCommand;
import cn.moexc.vcs.service.cmdfactory.CreateAddressCmdFacroty;
import cn.moexc.vcs.service.dto.CreateAddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AddressService {

    private final AddressDomainRepository addressDomainRepository;

    @Autowired
    public AddressService(AddressDomainRepository addressDomainRepository) {
        this.addressDomainRepository = addressDomainRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(CreateAddressDTO dto, String cid){
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
