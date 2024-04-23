package cn.moexc.vcs.infrasture.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

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
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 标题
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * 图片
     */
    @Column(name = "photo", nullable = false)
    private String photo;

    /**
     * 简介
     */
    @Column(name = "subdescr")
    private String subdescr;

    /**
     * 详情
     */
    @Column(name = "detail", nullable = false)
    private byte[] detail;

    /**
     * 分类
     */
    @Column(name = "classify", nullable = false)
    private String classify;

    /**
     * 原单价
     */
    @Column(name = "orig_price", nullable = false)
    private BigDecimal origPrice;

    /**
     * 单价
     */
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    /**
     * 数量
     */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * 状态 00:待审核 01:审核不通过 02:审核通过 03:已上架 04:已下架 05:已删除
     */
    @Column(name = "status", nullable = false)
    private String status;

}
