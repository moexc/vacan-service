package cn.moexc.vcs.domain.address;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressCommand {

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
