package cn.moexc.vcs.infrasture.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 专场
 */
@Data
@Entity
@Table(name = "trade")
public class TradeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专场ID
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 专场名称
     */
    @Column(name = "name")
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
     * 状态
     */
    @Column(name = "status")
    private String status;

    /**
     * 发送竞价引擎状态
     */
    @Column(name = "send_status")
    private String sendStatus;

    /**
     * 标的数
     */
    @Column(name = "bid_count")
    private Integer bidCount;

}
