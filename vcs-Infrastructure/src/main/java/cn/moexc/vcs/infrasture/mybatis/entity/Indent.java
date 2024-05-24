package cn.moexc.vcs.infrasture.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 订单
    */
@Getter
@Setter
@ToString
public class Indent {
    /**
    * ID
    */
    private String id;

    /**
    * 客户ID
    */
    private String customerId;

    /**
    * 商品ID
    */
    private String goodsId;

    /**
    * 标题
    */
    private String title;

    /**
    * 图片
    */
    private String photo;

    /**
    * 单价
    */
    private BigDecimal price;

    /**
    * 数量
    */
    private Integer quantity;

    /**
    * 金额
    */
    private BigDecimal amount;

    /**
    * 收货地址
    */
    private String address;

    /**
    * 状态 00:已创建, 01:已付款, 02:已发货, 03:已验收, 04:已取消, 05:超时未付款自动取消, 06:已删除
    */
    private String status;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 付款时间
    */
    private Date payTime;

    /**
    * 发货时间
    */
    private Date deliveryTime;

    /**
    * 验收时间
    */
    private Date acceptTime;

    /**
    * 取消时间
    */
    private Date cancelTime;

    /**
    * 超时未付款自动取消时间
    */
    private Date autoCancelTime;

    /**
    * 删除时间
    */
    private Date deleteTime;
}