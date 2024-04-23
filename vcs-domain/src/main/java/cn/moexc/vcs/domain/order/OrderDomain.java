package cn.moexc.vcs.domain.order;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.domain.Utils;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
public class OrderDomain {
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

    /**
     * 商品库存
     */
    private Integer goodsQuantity;

    /**
     * 生成订单
     * @param cmd
     * @return 订单编号（ID）
     */
    public String create(CreateOrderCommand cmd){
        if (cmd.getGoodsId() == null){
            throw new AlterException("获取商品信息失败");
        }
        if (!("03").equals(cmd.getStatus())){
            throw new AlterException("商品已下架");
        }
        this.goodsQuantity = cmd.getGoodsQuantity() - cmd.getQuantity();
        if (this.goodsQuantity < 0){
            throw new AlterException("购买数量超出库存");
        }

        String orderId = Utils.genUUID();
        this.id = orderId;
        this.customerId = cmd.getCustomerId();
        this.quantity = cmd.getQuantity();
        this.address = cmd.getAddress();
        this.createTime = new Date();
        this.goodsId = cmd.getGoodsId();
        this.title = cmd.getTitle();
        this.photo = cmd.getPhoto();
        this.price = cmd.getPrice();
        this.amount = this.price.multiply(BigDecimal.valueOf(this.quantity)).setScale(2, RoundingMode.HALF_UP);
        this.status = "00";

        return orderId;
    }

    public void deliver(){
        this.status = "02";
        this.deliveryTime = new Date();
    }

    /**
     * 订单超时未付款自动取消
     */
    public void expired(){
        if (!"00".equals(this.status)){
            throw new AlterException("此订单当前状态无法自动取消");
        }
        this.status = "05";
        this.autoCancelTime = new Date();
        this.goodsQuantity = this.goodsQuantity + this.quantity;
    }

    /**
     * 取消订单
     */
    public void cancel(String cid){
        checkAuth(cid);
        if (!"00".equals(this.status)){
            throw new AlterException("此订单当前状态无法取消");
        }
        this.status = "04";
        this.cancelTime = new Date();
        this.goodsQuantity = this.goodsQuantity + this.quantity;
    }

    /**
     * 删除订单-软删
     */
    private static final Set<String> canDeleteStatus = new HashSet<>(Arrays.asList("03", "04", "05"));
    public void delete(String cid){
        checkAuth(cid);
        if (!canDeleteStatus.contains(this.status)
                && !(this.status.equals("00") && System.currentTimeMillis() > this.createTime.getTime() + (15 * 60 * 1000))
        ){
            throw new AlterException("此订单当前状态无法删除");
        }
        this.status = "06";
        this.deleteTime = new Date();
    }

    /**
     * 付款成功
     */
    public void payAfter(){
        if (!"00".equals(this.status)){
            throw new AlterException("此订单当前状态无法付款");
        }
        this.status = "01";
        this.payTime = new Date();
    }

    /**
     * 是否支付成功
     */
    public boolean payIsSuccess(){
        return "01".equals(this.status);
    }

    /**
     * 校验执行操作的用户是否是订单创建者
     */
    private void checkAuth(String cid){
        if (!this.customerId.equals(cid)){
            throw new AlterException("此订单非当前用户创建，无权操作");
        }
    }

    /**
     * 收货
     */
    public void accept() {
        this.status = "03";
        this.acceptTime = new Date();
    }
}
