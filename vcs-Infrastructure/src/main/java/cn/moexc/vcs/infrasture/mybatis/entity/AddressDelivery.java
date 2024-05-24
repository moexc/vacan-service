package cn.moexc.vcs.infrasture.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 收货地址
    */
@Getter
@Setter
@ToString
public class AddressDelivery {
    /**
    * ID
    */
    private String id;

    /**
    * 客户ID
    */
    private String customerId;

    /**
    * 省,市,区
    */
    private String city;

    /**
    * 详细地址
    */
    private String detailed;

    /**
    * 邮编
    */
    private String postCode;

    /**
    * 是否默认
    */
    private String isdefault;
}