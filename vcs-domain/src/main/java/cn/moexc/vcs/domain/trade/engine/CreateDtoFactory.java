package cn.moexc.vcs.domain.trade.engine;

import cn.moexc.vcs.domain.trade.BidDomain;
import cn.moexc.vcs.domain.trade.TradeDomain;

import java.util.stream.Collectors;

public class CreateDtoFactory {
    public static Engine4CreateDTO gen(TradeDomain tradeDomain){
        Engine4CreateDTO createDTO = new Engine4CreateDTO();
        createDTO.setId(tradeDomain.getId());
        createDTO.setName(tradeDomain.getName());
        createDTO.setStartTime(tradeDomain.getStartTime().getTime());
        createDTO.setBids(tradeDomain.getBidDomains().stream().map(CreateDtoFactory::genBid).collect(Collectors.toList()));
        return createDTO;
    }

    public static Engine4CreateDTO.Bid genBid(BidDomain bidDomain){
        Engine4CreateDTO.Bid engineBid = new Engine4CreateDTO.Bid();
        engineBid.setId(bidDomain.getId());
        engineBid.setName(bidDomain.getName());
        engineBid.setCountDown(bidDomain.getCountDown().longValue());
        engineBid.setResetCd(bidDomain.getResetCd().longValue());
        engineBid.setStartPrice(bidDomain.getStartPrice());
        engineBid.setBidPrice(bidDomain.getBidPrice());
        return engineBid;
    }
}
