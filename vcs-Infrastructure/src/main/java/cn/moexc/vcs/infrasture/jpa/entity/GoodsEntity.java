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
 * 商品
 */
@Data
@Entity
@Table(name = "goods")
public class GoodsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 图片
     */
    @Column(name = "photo")
    private String photo;

    /**
     * 简介
     */
    @Column(name = "subdescr")
    private String subdescr;

    /**
     * 详情
     */
    @Column(name = "detail")
    private String detail;

    /**
     * 分类
     */
    @Column(name = "classify")
    private String classify;

    /**
     * 原单价
     */
    @Column(name = "orig_price")
    private BigDecimal origPrice;

    /**
     * 单价
     */
    @Column(name = "price")
    private BigDecimal price;

    /**
     * 数量
     */
    @Column(name = "quantity")
    private Integer quantity;

    /**
     * 状态 00:待审核 01:审核不通过 02:审核通过 03:已上架 04:已下架 05:已删除
     */
    @Column(name = "status")
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

}
