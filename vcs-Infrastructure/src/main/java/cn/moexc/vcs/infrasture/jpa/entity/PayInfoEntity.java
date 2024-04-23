package cn.moexc.vcs.infrasture.jpa.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 支付信息
 */
@Data
@Entity
@Table(name = "pay_info")
@IdClass(PayInfoEntityKey.class)
public class PayInfoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单ID
     */
    @Id
    @Column(name = "order_id", nullable = false)
    private String orderId;

    /**
     * 付款方式
     */
    @Id
    @Column(name = "mode", nullable = false)
    private String mode;

    /**
     * content
     */
    @Column(name = "content", nullable = false)
    private String content;

}
