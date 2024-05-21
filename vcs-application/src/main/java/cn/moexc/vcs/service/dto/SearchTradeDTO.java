package cn.moexc.vcs.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timeRangeBefore;
    /**
     * 开始时间止
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
