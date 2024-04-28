package cn.moexc.vcs.service.cmdfactory;

import cn.moexc.vcs.domain.trade.UpdateTradeCommand;
import cn.moexc.vcs.service.dto.TradeDTO;

import java.util.stream.Collectors;

public class UpdateTradeCmdFactory {
    public static UpdateTradeCommand gen(TradeDTO dto){
        UpdateTradeCommand command = new UpdateTradeCommand();
        command.setName(dto.getTradeName());
        command.setStartTime(dto.getStartTime());
        command.setBids(dto.getBids().stream().map(bid -> {
            UpdateTradeCommand.Bid cbid = new UpdateTradeCommand.Bid();
            cbid.setName(bid.getName());
            cbid.setCountDown(bid.getCountDown());
            cbid.setResetCd(bid.getResetCd());
            cbid.setStartPrice(bid.getStartPrice());
            cbid.setBidPrice(bid.getBidPrice());
            return cbid;
        }).collect(Collectors.toList()));
        return command;
    }
}
