package cn.moexc.vcs.infrasture.customer;

import cn.moexc.vcs.domain.customer.CustomerDomain;
import cn.moexc.vcs.infrasture.jpa.entity.CustomerEntity;

public class CustomerDomainFactory {
    public static CustomerDomain genDomain(CustomerEntity customerEntity){
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

    public static CustomerEntity genEntity(CustomerDomain domain){
        CustomerEntity entity = new CustomerEntity();
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
