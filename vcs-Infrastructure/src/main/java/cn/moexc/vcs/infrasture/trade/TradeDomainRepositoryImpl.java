package cn.moexc.vcs.infrasture.trade;

import cn.moexc.vcs.domain.trade.TradeDomain;
import cn.moexc.vcs.domain.trade.TradeDomainRepository;
import cn.moexc.vcs.infrasture.jpa.entity.TradeBidEntity;
import cn.moexc.vcs.infrasture.jpa.entity.TradeEntity;
import cn.moexc.vcs.infrasture.jpa.repository.TradeBidEntityRepository;
import cn.moexc.vcs.infrasture.jpa.repository.TradeEntityRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TradeDomainRepositoryImpl implements TradeDomainRepository {

    private final TradeEntityRepository tradeEntityRepository;
    private final TradeBidEntityRepository tradeBidEntityRepository;

    public TradeDomainRepositoryImpl(TradeEntityRepository tradeEntityRepository, TradeBidEntityRepository tradeBidEntityRepository) {
        this.tradeEntityRepository = tradeEntityRepository;
        this.tradeBidEntityRepository = tradeBidEntityRepository;
    }

    @Override
    public TradeDomain byId(String s) {
        Optional<TradeEntity> tradeEntityOptional = tradeEntityRepository.findById(s);
        TradeEntity tradeEntity = tradeEntityOptional.orElseThrow(() -> new RuntimeException("未找到专场信息"));
        List<TradeBidEntity> tradeBidEntities = tradeBidEntityRepository.findAllByTradeIdOrderByNumberAsc(s);
        return TradeDomainFactory.genDomain(tradeEntity, tradeBidEntities);
    }

    @Override
    public void save(TradeDomain domain) {
        TradeEntity tradeEntity = new TradeEntity();
        List<TradeBidEntity> tradeBidEntities = new ArrayList<>();
        TradeDomainFactory.genEntity(domain, tradeEntity, tradeBidEntities);
        tradeEntityRepository.save(tradeEntity);
        tradeBidEntityRepository.saveAll(tradeBidEntities);
    }

    @Override
    public void delete(String tradeId) {
        tradeEntityRepository.deleteById(tradeId);
        tradeBidEntityRepository.deleteByTradeId(tradeId);
    }
}
