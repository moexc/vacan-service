package cn.moexc.vcs.infrasture.customer;

import cn.moexc.vcs.domain.customer.CustomerDomain;
import cn.moexc.vcs.infrasture.mybatis.entity.Customer;

public class CustomerDomainFactory {
    public static CustomerDomain genDomain(Customer customerEntity){
        CustomerDomain domain = new CustomerDomain();
        domain.setId(customerEntity.getId());
        domain.setCustomerName(customerEntity.getCustomerName());
        domain.setCustomerPhoto(customerEntity.getCustomerPhoto());
        domain.setRegisterTime(customerEntity.getRegisterTime());
        domain.setAccountNum(customerEntity.getAccountNum());
        domain.setAccountPwd(customerEntity.getAccountPwd());
        domain.setAccountMobile(customerEntity.getAccountMobile());
        domain.setAccountMail(customerEntity.getAccountMail());
        return domain;
    }

    public static Customer genEntity(CustomerDomain domain){
        Customer entity = new Customer();
        entity.setId(domain.getId());
        entity.setCustomerName(domain.getCustomerName());
        entity.setCustomerPhoto(domain.getCustomerPhoto());
        entity.setRegisterTime(domain.getRegisterTime());
        entity.setAccountNum(domain.getAccountNum());
        entity.setAccountPwd(domain.getAccountPwd());
        entity.setAccountMobile(domain.getAccountMobile());
        entity.setAccountMail(domain.getAccountMail());
        return entity;
    }
}
