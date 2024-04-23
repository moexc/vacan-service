package cn.moexc.vcs.infrasture.jpa.repository;

import cn.moexc.vcs.infrasture.jpa.entity.TradeBidEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface TradeBidEntityRepository extends JpaRepository<TradeBidEntity, String>, JpaSpecificationExecutor<TradeBidEntity> {
    List<TradeBidEntity> findAllByTradeIdOrderByNumberAsc(String s);
    void deleteByTradeId(String tradeId);
    Integer countByTradeIdIn(Set<String> tradeIds);
}