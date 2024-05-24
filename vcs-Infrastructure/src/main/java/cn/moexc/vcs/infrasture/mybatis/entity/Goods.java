package cn.moexc.vcs.infrasture.mybatis.entity;

import java.math.BigDecimal;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 商品
    */
@Getter
@Setter
@ToString
public class Goods {
    /**
    * ID
    */
    private String id;

    /**
    * 标题
    */
    private String title;

    /**
    * 图片
    */
    private String photo;

    /**
    * 简介
    */
    private String subdescr;

    /**
    * 详情
    */
    private byte[] detail;

    /**
    * 分类
    */
    private String classify;

    /**
    * 原单价
    */
    private BigDecimal origPrice;

    /**
    * 单价
    */
    private BigDecimal price;

    /**
    * 数量
    */
    private Integer quantity;

    /**
    * 状态 00:待审核 01:审核不通过 02:审核通过 03:已上架 04:已下架 05:已删除
    */
    private String status;

    /**
    * 创建时间
    */
    private Date createTime;
}