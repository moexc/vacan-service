package cn.moexc.vcs.infrasture.customer;

import cn.moexc.vcs.domain.customer.CustomerDomain;
import cn.moexc.vcs.domain.customer.CustomerDomainRepository;
import cn.moexc.vcs.infrasture.mybatis.entity.Customer;
import cn.moexc.vcs.infrasture.mybatis.mapper.CustomerMapper;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerDomainRepositoryImpl implements CustomerDomainRepository {

    private final CustomerMapper customerMapper;

    public CustomerDomainRepositoryImpl(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDomain byId(String s) {
        return CustomerDomainFactory.genDomain(customerMapper.selectByPrimaryKey(s));
    }

    @Override
    public Boolean exists(String accountNum){
        return customerMapper.selectOneByAccountNum(accountNum) != null;
    }

    @Override
    public void save(CustomerDomain domain) {
        Customer newEntity = CustomerDomainFactory.genEntity(domain);
        Customer entity = customerMapper.selectByPrimaryKey(domain.getId());
        if (entity == null) customerMapper.insert(newEntity);
        else customerMapper.updateByPrimaryKey(newEntity);
    }

    @Override
    public CustomerDomain findByNumAndPwd(String num, String pwd) {
        Customer entity = customerMapper.selectOneByAccountNumAndAccountPwd(num, pwd);
        if (entity == null) return null;
        return CustomerDomainFactory.genDomain(entity);
    }
}
