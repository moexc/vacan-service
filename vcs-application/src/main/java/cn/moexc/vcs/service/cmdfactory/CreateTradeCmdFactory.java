package cn.moexc.vcs.service.cmdfactory;

import cn.moexc.vcs.domain.trade.CreateTradeCommand;
import cn.moexc.vcs.service.dto.CreateTradeDTO;

import java.util.stream.Collectors;

public class CreateTradeCmdFactory {

    public static CreateTradeCommand gen(CreateTradeDTO dto){
        CreateTradeCommand command = new CreateTradeCommand();
        command.setName(dto.getTradeName());
        command.setStartTime(dto.getStartTime());
        command.setBids(dto.getBids().stream().map(bid -> {
            CreateTradeCommand.Bid cbid = new CreateTradeCommand.Bid();
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
