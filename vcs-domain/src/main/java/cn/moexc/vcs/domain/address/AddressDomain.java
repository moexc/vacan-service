package cn.moexc.vcs.domain.address;

import cn.moexc.vcs.domain.Utils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDomain {
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

    public void create(CreateAddressCommand cmd){
        this.id = Utils.genUUID();
        this.customerId = cmd.getCustomerId();
        this.city = cmd.getCity();
        this.detailed = cmd.getDetailed();
        this.postCode = cmd.getPostCode();
        this.isdefault = cmd.getIsdefault();
    }

    public boolean isDefault(){
        return "1".equals(this.isdefault);
    }

    /**
     * 设为默认
     */
    public void setDefault(){
        this.isdefault = "1";
    }

    /**
     * 取消默认
     */
    public void unDefault(){
        this.isdefault = "0";
    }
}
