package cn.moexc.vcs.infrasture.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 支付信息
    */
@Getter
@Setter
@ToString
public class PayInfo {
    /**
    * 订单ID
    */
    private String orderId;

    /**
    * 付款方式
    */
    private String mode;

    /**
    * content
    */
    private String content;
}