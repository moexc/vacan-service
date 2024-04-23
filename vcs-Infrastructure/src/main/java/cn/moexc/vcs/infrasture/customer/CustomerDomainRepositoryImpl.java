package cn.moexc.vcs.infrasture.customer;

import cn.moexc.vcs.domain.customer.CustomerDomain;
import cn.moexc.vcs.domain.customer.CustomerDomainRepository;
import cn.moexc.vcs.infrasture.jpa.entity.CustomerEntity;
import cn.moexc.vcs.infrasture.jpa.repository.CustomerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CustomerDomainRepositoryImpl implements CustomerDomainRepository {

    private final CustomerEntityRepository customerEntityRepository;

    @Autowired
    public CustomerDomainRepositoryImpl(CustomerEntityRepository customerEntityRepository) {
        this.customerEntityRepository = customerEntityRepository;
    }

    @Override
    public CustomerDomain byId(String s) {
        Optional<CustomerEntity> customerEntityOptional = customerEntityRepository.findById(s);
        CustomerEntity customerEntity = customerEntityOptional.orElseThrow(() -> new RuntimeException("获取用户信息失败"));
        return CustomerDomainFactory.genDomain(customerEntity);
    }

    @Override
    public Boolean exists(String accountNum){
        return customerEntityRepository.existsByAccountNum(accountNum);
    }

    @Override
    public void save(CustomerDomain domain) {
        CustomerEntity customerEntity = CustomerDomainFactory.genEntity(domain);
        customerEntityRepository.save(customerEntity);
    }

    @Override
    public CustomerDomain findByNumAndPwd(String num, String pwd) {
        Optional<CustomerEntity> customerEntityOptional = customerEntityRepository.findByAccountNumAndAccountPwd(num, pwd);
        if (!customerEntityOptional.isPresent()){
            return null;
        }
        CustomerEntity customerEntity = customerEntityOptional.get();
        return CustomerDomainFactory.genDomain(customerEntity);
    }
}
