package cn.moexc.vcs.infrasture.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 专场标的
 */
@Data
@Entity
@Table(name = "trade_bid")
public class TradeBidEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专场标的ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 专场ID
     */
    @Column(name = "trade_id", nullable = false)
    private String tradeId;

    /**
     * 编号 排序使用 0-999
     */
    @Column(name = "number", nullable = false)
    private Integer number;

    /**
     * 专场标的名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 倒计时
     */
    @Column(name = "count_down")
    private Integer countDown;

    /**
     * 恢复计时
     */
    @Column(name = "reset_cd")
    private Integer resetCd;

    /**
     * 状态
     */
    @Column(name = "status", nullable = false)
    private String status;

    /**
     * 底价
     */
    @Column(name = "start_price", nullable = false)
    private BigDecimal startPrice;

    /**
     * 竞价幅度
     */
    @Column(name = "bid_price", nullable = false)
    private BigDecimal bidPrice;

    /**
     * 当前报价
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 成交状态
     */
    @Column(name = "bid_status")
    private String bidStatus;

    /**
     * 成交人
     */
    @Column(name = "bid_user")
    private String bidUser;

}
