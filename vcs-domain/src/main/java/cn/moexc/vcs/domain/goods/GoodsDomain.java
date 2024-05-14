package cn.moexc.vcs.domain.goods;

import cn.moexc.vcs.domain.Utils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class GoodsDomain {
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
    private String detail;

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

    public void create(CreateGoodsCommand cmd){
        this.id = Utils.genUUID();
        this.title = cmd.getTitle();
        this.photo = cmd.getPhoto();
        this.subdescr = cmd.getSubdescr();
        this.detail = cmd.getDetail();
        this.classify = cmd.getClassify();
        this.origPrice = cmd.getOldPrice();
        this.price = cmd.getPrice();
        this.quantity = cmd.getQuantity();
        this.status = "00";
        this.createTime = new Date();
    }

    public void update(UpdateGoodsCommand cmd){
        this.title = cmd.getTitle();
        this.photo = cmd.getPhoto();
        this.subdescr = cmd.getSubdescr();
        this.detail = cmd.getDetail();
        this.classify = cmd.getClassify();
        this.origPrice = cmd.getOldPrice();
        this.price = cmd.getPrice();
        this.quantity = cmd.getQuantity();
    }

}
