package cn.moexc.vcs.infrasture.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 客户
 */
@Data
@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * 用户名称
     */
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    /**
     * 用户头像
     */
    @Column(name = "customer_photo", nullable = false)
    private String customerPhoto;

    /**
     * 注册时间
     */
    @Column(name = "register_time", nullable = false)
    private Date registerTime;

    /**
     * 登录账号
     */
    @Column(name = "account_num", nullable = false)
    private String accountNum;

    /**
     * 登录密码
     */
    @Column(name = "account_pwd", nullable = false)
    private String accountPwd;

    /**
     * 绑定手机号
     */
    @Column(name = "account_mobile")
    private String accountMobile;

    /**
     * 绑定邮箱
     */
    @Column(name = "account_mail")
    private String accountMail;

}
