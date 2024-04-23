package cn.moexc.vcs.domain.customer;

import cn.moexc.vcs.domain.DomainRepository;

public interface CustomerDomainRepository extends DomainRepository<String, CustomerDomain> {
    CustomerDomain findByNumAndPwd(String num, String pwd);
    Boolean exists(String accountNum);
}
