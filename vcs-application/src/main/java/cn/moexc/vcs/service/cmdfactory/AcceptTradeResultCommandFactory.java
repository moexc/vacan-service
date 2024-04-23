package cn.moexc.vcs.service.cmdfactory;

import cn.moexc.vcs.domain.trade.AcceptTradeResultCommand;
import cn.moexc.vcs.service.dto.TradeResultDTO;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AcceptTradeResultCommandFactory {

    public static AcceptTradeResultCommand gen(TradeResultDTO dto){
        AcceptTradeResultCommand cmd = new AcceptTradeResultCommand();
        cmd.setStatus(dto.getStatus());
        cmd.setStartTime(new Date(dto.getStartTime()));
        cmd.setEndTIme(new Date(dto.getEndTIme()));
        Map<String, AcceptTradeResultCommand.Bid> bidMap = new HashMap<>();
        dto.getBidMap().forEach((k,v) -> bidMap.put(k, genBid(v)));
        cmd.setBidMap(bidMap);
        return cmd;
    }

    private static AcceptTradeResultCommand.Bid genBid(TradeResultDTO.Bid bid){
        AcceptTradeResultCommand.Bid result = new AcceptTradeResultCommand.Bid();
        result.setStatus(bid.getStatus());
        result.setStartTime(new Date(bid.getStartTime()));
        result.setEndTIme(new Date(bid.getEndTIme()));
        result.setPrice(bid.getPrice());
        result.setBidStatus(bid.getBidStatus());
        result.setBidUser(bid.getBidUser());
        return result;
    }

}
