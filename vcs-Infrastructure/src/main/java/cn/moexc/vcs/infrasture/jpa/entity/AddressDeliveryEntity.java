package cn.moexc.vcs.infrasture.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 收货地址
 */
@Data
@Entity
@Table(name = "address_delivery")
public class AddressDeliveryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 客户ID
     */
    @Column(name = "customer_id", nullable = false)
    private String customerId;

    /**
     * 省,市,区
     */
    @Column(name = "city", nullable = false)
    private String city;

    /**
     * 详细地址
     */
    @Column(name = "detailed", nullable = false)
    private String detailed;

    /**
     * 邮编
     */
    @Column(name = "post_code", nullable = false)
    private String postCode;

    /**
     * 是否默认
     */
    @Column(name = "isdefault", nullable = false)
    private String isdefault;

}
