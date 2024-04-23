package cn.moexc.vcs.infrasture.pay.alipay;

import cn.moexc.vcs.domain.pay.PayDomain;

public class RequestBizContent {

    private String outTradeNo;

    private String subject;

    private String body;

    private String totalAmount;

    private String timeoutExpress;

    public String cvt(PayDomain payDomain){
        this.outTradeNo = payDomain.getOrderId();
        this.subject = payDomain.getOrderTitle();
        this.body = "购买"+ payDomain.getShopName() +" * "+ payDomain.getCount();
        this.totalAmount = payDomain.getPayMoney().toString();
        this.timeoutExpress = payDomain.getTimeoutExpress();
        return this.jsonString();
    }

    private String jsonString() {
        return "{"
                + "\"out_trade_no\":\""
                + outTradeNo + '\"'
                + ",\"subject\":\""
                + subject + '\"'
                + ",\"body\":\""
                + body + '\"'
                + ",\"total_amount\":\""
                + totalAmount + '\"'
                + ",\"timeout_express\":\""
                + timeoutExpress + '\"'
                + "}";

    }

}
