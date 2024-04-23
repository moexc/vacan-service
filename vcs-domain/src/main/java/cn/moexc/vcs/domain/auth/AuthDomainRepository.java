package cn.moexc.vcs.domain.auth;

import cn.moexc.vcs.domain.DomainRepository;

public interface AuthDomainRepository extends DomainRepository<String, AuthDomain> {
    void expire(String token);
    void remove(String token);
}
