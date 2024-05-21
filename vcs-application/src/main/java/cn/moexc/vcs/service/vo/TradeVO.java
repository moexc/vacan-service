package cn.moexc.vcs.service.vo;

import cn.moexc.vcs.infrasture.jpa.entity.TradeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeVO {
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
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 发送竞价引擎状态
     */
    private String sendStatus;

    /**
     * 标的数
     */
    private Integer bidCount;

    public static TradeVO gen(TradeEntity entity){
        TradeVO vo = new TradeVO();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setStartTime(entity.getStartTime().getTime());
        vo.setEndTime(entity.getEndTime() == null ? null : entity.getEndTime().getTime());
        vo.setStatus(entity.getStatus());
        vo.setSendStatus(entity.getSendStatus());
        vo.setBidCount(entity.getBidCount());
        return vo;
    }

}