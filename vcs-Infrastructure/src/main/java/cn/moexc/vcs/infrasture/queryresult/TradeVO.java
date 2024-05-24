package cn.moexc.vcs.infrasture.queryresult;

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
}