package cn.moexc.vcs.domain.goods;

import cn.moexc.vcs.domain.AlterException;
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

    /**
     * 新增商品
     * @param cmd
     */
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

    /**
     * 修改商品
     * @param cmd
     */
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

    /**
     * 上架
     */
    public void up(){
        this.status = "03";
    }

    /**
     * 下架
     */
    public void down(){
        this.status = "04";
    }

    /**
     * 删除
     */
    public void delete(){
        this.status = "05";
    }

    /**
     * 创建订单减库存
     * @param count
     */
    public void createOrder(Integer count){
        if (!"03".equals(this.status)) throw new AlterException("商品暂不支持购买");
        this.quantity -= count;
        if (this.quantity < 0) throw new AlterException("购买数量超出库存");
    }

    /**
     * 取消订单加库存
     * @param count
     */
    public void cancelOrder(Integer count){
        this.quantity += count;
    }

}
