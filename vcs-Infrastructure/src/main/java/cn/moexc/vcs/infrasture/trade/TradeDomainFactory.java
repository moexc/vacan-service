package cn.moexc.vcs.infrasture.trade;

import cn.moexc.vcs.domain.trade.BidDomain;
import cn.moexc.vcs.domain.trade.TradeDomain;
import cn.moexc.vcs.infrasture.mybatis.entity.Trade;
import cn.moexc.vcs.infrasture.mybatis.entity.TradeBid;

import java.util.List;
import java.util.stream.Collectors;

public class TradeDomainFactory {

    public static TradeDomain genDomain(Trade tradeEntity, List<TradeBid> tradeBidEntities){
        TradeDomain tradeDomain = new TradeDomain();
        tradeDomain.setId(tradeEntity.getId());
        tradeDomain.setName(tradeEntity.getName());
        tradeDomain.setStartTime(tradeEntity.getStartTime());
        tradeDomain.setEndTime(tradeEntity.getEndTime());
        tradeDomain.setStatus(tradeEntity.getStatus());
        tradeDomain.setSendStatus(tradeEntity.getSendStatus());

        List<BidDomain> bidDomains = tradeBidEntities.stream().map(tradeBidEntity -> {
            BidDomain bidDomain = new BidDomain();
            bidDomain.setId(tradeBidEntity.getId());
            bidDomain.setTradeId(tradeBidEntity.getTradeId());
            bidDomain.setName(tradeBidEntity.getName());
            bidDomain.setStartTime(tradeBidEntity.getStartTime());
            bidDomain.setEndTime(tradeBidEntity.getEndTime());
            bidDomain.setCountDown(tradeBidEntity.getCountDown());
            bidDomain.setResetCd(tradeBidEntity.getResetCd());
            bidDomain.setStatus(tradeBidEntity.getStatus());
            bidDomain.setStartPrice(tradeBidEntity.getStartPrice());
            bidDomain.setBidPrice(tradeBidEntity.getBidPrice());
            bidDomain.setPrice(tradeBidEntity.getPrice());
            bidDomain.setBidStatus(tradeBidEntity.getBidStatus());
            bidDomain.setBidUser(tradeBidEntity.getBidUser());
            return bidDomain;
        }).collect(Collectors.toList());

        tradeDomain.setBidDomains(bidDomains);
        return tradeDomain;
    }


    public static void genEntity(TradeDomain tradeDomain, Trade tradeEntity, List<TradeBid> tradeBidEntities){
        tradeEntity.setId(tradeDomain.getId());
        tradeEntity.setName(tradeDomain.getName());
        tradeEntity.setStartTime(tradeDomain.getStartTime());
        tradeEntity.setEndTime(tradeDomain.getEndTime());
        tradeEntity.setStatus(tradeDomain.getStatus());
        tradeEntity.setSendStatus(tradeDomain.getSendStatus());
        tradeEntity.setBidCount(tradeDomain.getBidCount());

        for (int i = 0; i < tradeDomain.getBidDomains().size(); i++) {
            BidDomain bidDomain = tradeDomain.getBidDomains().get(i);
            TradeBid tradeBidEntity = new TradeBid();
            tradeBidEntity.setId(bidDomain.getId());
            tradeBidEntity.setTradeId(bidDomain.getTradeId());
            tradeBidEntity.setNumber(i);
            tradeBidEntity.setName(bidDomain.getName());
            tradeBidEntity.setStartTime(bidDomain.getStartTime());
            tradeBidEntity.setEndTime(bidDomain.getEndTime());
            tradeBidEntity.setCountDown(bidDomain.getCountDown());
            tradeBidEntity.setResetCd(bidDomain.getResetCd());
            tradeBidEntity.setStatus(bidDomain.getStatus());
            tradeBidEntity.setStartPrice(bidDomain.getStartPrice());
            tradeBidEntity.setBidPrice(bidDomain.getBidPrice());
            tradeBidEntity.setPrice(bidDomain.getPrice());
            tradeBidEntity.setBidStatus(bidDomain.getBidStatus());
            tradeBidEntity.setBidUser(bidDomain.getBidUser());
            tradeBidEntities.add(tradeBidEntity);
        }

    }

}
