package cn.moexc.vcs.domain.trade;

import cn.moexc.vcs.domain.Utils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class TradeDomain {
    /**
     * 专场ID
     */
    private String id;
    /**
     * 专场名称
     */
    private String name;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 状态 0: 未启动 1: 运行中 2: 已结束
     */
    private String status;
    /**
     * 发送竞价引擎状态 0: 未发送 1: 成功 2: 失败
     */
    private String sendStatus;

    /**
     * 标的数
     */
    private Integer bidCount;

    private List<BidDomain> bidDomains;

    public String createTrade(CreateTradeCommand command){
        this.id = Utils.genUUID();
        this.name = command.getName();
        this.startTime = command.getStartTime();
        this.status = "0";
        this.sendStatus = "0";

        this.bidDomains = command.getBids().stream().map(bid -> {
            BidDomain bidEntity = new BidDomain();
            bidEntity.setId(Utils.genUUID());
            bidEntity.setTradeId(this.id);
            bidEntity.setName(bid.getName());
            bidEntity.setCountDown(bid.getCountDown());
            bidEntity.setResetCd(bid.getResetCd());
            bidEntity.setStatus("0");
            bidEntity.setStartPrice(bid.getStartPrice());
            bidEntity.setBidPrice(bid.getBidPrice());
            bidEntity.setBidStatus("0");
            return bidEntity;
        }).collect(Collectors.toList());

        this.bidCount = this.bidDomains.size();

        return this.id;
    }

    public void sended(boolean sendIsSuccess){
        this.sendStatus = sendIsSuccess ? "1" : "2";
    }

    public void acceptResult(AcceptTradeResultCommand cmd){
        this.status = cmd.getStatus();
        this.startTime = cmd.getStartTime();
        this.endTime = cmd.getEndTIme();
        Map<String, AcceptTradeResultCommand.Bid> cmdBidMap = cmd.getBidMap();
        this.bidDomains.forEach(bid -> {
            AcceptTradeResultCommand.Bid cmdBid = cmdBidMap.get(bid.getId());
            bid.setStatus(cmdBid.getStatus());
            bid.setStartTime(cmdBid.getStartTime());
            bid.setEndTime(cmdBid.getEndTIme());
            bid.setPrice(cmdBid.getPrice());
            bid.setBidStatus(cmdBid.getBidStatus());
            bid.setBidUser(cmdBid.getBidUser());
        });
    }

}
