package cn.moexc.vcs.infrasture.trade;

import cn.moexc.vcs.domain.trade.TradeDomain;
import cn.moexc.vcs.domain.trade.TradeDomainRepository;
import cn.moexc.vcs.infrasture.mybatis.entity.Trade;
import cn.moexc.vcs.infrasture.mybatis.entity.TradeBid;
import cn.moexc.vcs.infrasture.mybatis.mapper.TradeBidMapper;
import cn.moexc.vcs.infrasture.mybatis.mapper.TradeMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TradeDomainRepositoryImpl implements TradeDomainRepository {

    private final TradeMapper tradeMapper;
    private final TradeBidMapper tradeBidMapper;

    public TradeDomainRepositoryImpl(TradeMapper tradeMapper, TradeBidMapper tradeBidMapper) {
        this.tradeMapper = tradeMapper;
        this.tradeBidMapper = tradeBidMapper;
    }


    @Override
    public TradeDomain byId(String s) {
        Trade trade = tradeMapper.selectByPrimaryKey(s);
        List<TradeBid> bids = tradeBidMapper.selectAllByTradeIdOrderByNumber(s);
        return TradeDomainFactory.genDomain(trade, bids);
    }

    @Override
    public void save(TradeDomain domain) {
        Trade trade = new Trade();
        List<TradeBid> bids = new ArrayList<>();
        TradeDomainFactory.genEntity(domain, trade, bids);
        if (tradeMapper.selectByPrimaryKey(domain.getId()) != null){
            delete(domain.getId());
        }
        tradeMapper.insert(trade);
        tradeBidMapper.insertList(bids);
    }

    @Override
    public void delete(String tradeId) {
        tradeMapper.deleteByPrimaryKey(tradeId);
        tradeBidMapper.deleteByTradeId(tradeId);
    }
}
