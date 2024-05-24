package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.trade.CreateTradeCommand;
import cn.moexc.vcs.domain.trade.TradeDomain;
import cn.moexc.vcs.domain.trade.TradeDomainRepository;
import cn.moexc.vcs.domain.trade.UpdateTradeCommand;
import cn.moexc.vcs.domain.trade.engine.BidEnginePort;
import cn.moexc.vcs.domain.trade.engine.CreateDtoFactory;
import cn.moexc.vcs.domain.trade.engine.Engine4CreateDTO;
import cn.moexc.vcs.service.cmdfactory.AcceptTradeResultCommandFactory;
import cn.moexc.vcs.service.cmdfactory.CreateTradeCmdFactory;
import cn.moexc.vcs.service.cmdfactory.UpdateTradeCmdFactory;
import cn.moexc.vcs.service.dto.TradeDTO;
import cn.moexc.vcs.service.dto.TradeResultDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TradeService {

    private final TradeDomainRepository tradeDomainRepository;
    private final BidEnginePort bidEnginePort;

    public TradeService(TradeDomainRepository tradeDomainRepository,
                        @Qualifier("bidEnginePortImpl") BidEnginePort bidEnginePort) {
        this.tradeDomainRepository = tradeDomainRepository;
        this.bidEnginePort = bidEnginePort;
    }

    @Transactional(rollbackFor = Exception.class)
    public String create(TradeDTO tradeDTO){
        CreateTradeCommand command = CreateTradeCmdFactory.gen(tradeDTO);
        TradeDomain tradeDomain = new TradeDomain();
        String tradeId = tradeDomain.createTrade(command);
        tradeDomainRepository.save(tradeDomain);
        return tradeId;
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(String tradeId, TradeDTO tradeDTO) {
        TradeDomain tradeDomain = tradeDomainRepository.byId(tradeId);
        UpdateTradeCommand command = UpdateTradeCmdFactory.gen(tradeDTO);
        tradeDomain.updateTrade(command);
        tradeDomainRepository.save(tradeDomain);
    }

    @Transactional(rollbackFor = Exception.class)
    public void delete(String tradeId){
        tradeDomainRepository.delete(tradeId);
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean send2Engine(String tradeId){
        TradeDomain tradeDomain = tradeDomainRepository.byId(tradeId);
        Engine4CreateDTO createDTO = CreateDtoFactory.gen(tradeDomain);
        boolean isSuccess = bidEnginePort.send(createDTO);
        tradeDomain.sended(isSuccess);
        tradeDomainRepository.save(tradeDomain);
        return isSuccess;
    }

    @Transactional(rollbackFor = Exception.class)
    public void acceptResult(TradeResultDTO tradeResultDTO) {
        TradeDomain tradeDomain = tradeDomainRepository.byId(tradeResultDTO.getId());
        tradeDomain.acceptResult(AcceptTradeResultCommandFactory.gen(tradeResultDTO));
        tradeDomainRepository.save(tradeDomain);
    }
}
