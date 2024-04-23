package cn.moexc.vcs.domain;

public interface DomainRepository<ID, T> {
    T byId(ID id);
    void save(T domain);
}
