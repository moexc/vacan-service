package cn.moexc.vcs.infrasture.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 专场标的
    */
@Getter
@Setter
@ToString
public class TradeBid {
    /**
    * 专场标的ID
    */
    private String id;

    /**
    * 专场ID
    */
    private String tradeId;

    /**
    * 编号 排序使用 0-999
    */
    private Integer number;

    /**
    * 专场标的名称
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
    * 倒计时
    */
    private Integer countDown;

    /**
    * 恢复计时
    */
    private Integer resetCd;

    /**
    * 状态
    */
    private String status;

    /**
    * 底价
    */
    private BigDecimal startPrice;

    /**
    * 竞价幅度
    */
    private BigDecimal bidPrice;

    /**
    * 当前报价
    */
    private BigDecimal price;

    /**
    * 成交状态
    */
    private String bidStatus;

    /**
    * 成交人
    */
    private String bidUser;
}