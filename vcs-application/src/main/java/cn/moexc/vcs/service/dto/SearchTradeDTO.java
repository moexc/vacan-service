package cn.moexc.vcs.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SearchTradeDTO {
    /**
     * 专场名称
     */
    private String tradeName;
    /**
     * 开始时间起
     */
    private Date timeRangeBefore;
    /**
     * 开始时间止
     */
    private Date timeRangeAfter;
    /**
     * 执行状态
     */
    private String tradeStatus;
    /**
     * 发送状态
     */
    private String sendStatus;
}
