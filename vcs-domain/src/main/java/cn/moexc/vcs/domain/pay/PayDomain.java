package cn.moexc.vcs.domain.pay;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PayDomain {

    /**
     * 订单编号
     */
    private String orderId;

    /**
     * 订单标题
     */
    private String orderTitle;

    /**
     * 商品名称
     */
    private String shopName;

    /**
     * 购买数量
     */
    private Long count;

    /**
     * 支付金额
     */
    private BigDecimal payMoney;

    /**
     * 允许的最晚付款时间，逾期将关闭交易。取值范围：1m～15d。m-分钟，h-小时，d-天，1c-当天（1c-当天的情况下，无论交易何时创建，都在0点关闭）。
     */
    private String timeoutExpress;

    /**
     * 付款方式
     */
    private String payMode;

    /**
     * 付款链接
     */
    private String content;

}
