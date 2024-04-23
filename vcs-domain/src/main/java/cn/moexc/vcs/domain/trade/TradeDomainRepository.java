package cn.moexc.vcs.domain.trade;

import cn.moexc.vcs.domain.DomainRepository;

public interface TradeDomainRepository extends DomainRepository<String, TradeDomain> {
    void delete(String tradeId);
}
