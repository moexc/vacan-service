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
 * 订单
 */
@Data
@Entity
@Table(name = "indent")
public class IndentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 客户ID
     */
    @Column(name = "customer_id", nullable = false)
    private String customerId;

    /**
     * 商品ID
     */
    @Column(name = "goods_id", nullable = false)
    private String goodsId;

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
     * 金额
     */
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    /**
     * 收货地址
     */
    @Column(name = "address", nullable = false)
    private String address;

    /**
     * 状态 00:已创建, 01:已付款, 02:已发货, 03:已验收, 04:已取消, 05:超时未付款自动取消, 06:已删除
     */
    @Column(name = "status", nullable = false)
    private String status;

    /**
     * 创建时间
     */
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    /**
     * 付款时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 发货时间
     */
    @Column(name = "delivery_time")
    private Date deliveryTime;

    /**
     * 验收时间
     */
    @Column(name = "accept_time")
    private Date acceptTime;

    /**
     * 取消时间
     */
    @Column(name = "cancel_time")
    private Date cancelTime;

    /**
     * 超时未付款自动取消时间
     */
    @Column(name = "auto_cancel_time")
    private Date autoCancelTime;

    /**
     * 删除时间
     */
    @Column(name = "delete_time")
    private Date deleteTime;

}
