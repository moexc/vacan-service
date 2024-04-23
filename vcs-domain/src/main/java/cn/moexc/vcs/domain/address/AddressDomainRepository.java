package cn.moexc.vcs.domain.address;

import cn.moexc.vcs.domain.DomainRepository;

public interface AddressDomainRepository extends DomainRepository<String, AddressDomain> {
    AddressDomain findIsDefault();
    void delete(String id);
}
