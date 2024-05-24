package cn.moexc.vcs.infrasture.mybatis.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 客户
    */
@Getter
@Setter
@ToString
public class Customer {
    /**
    * ID
    */
    private String id;

    /**
    * 用户名称
    */
    private String customerName;

    /**
    * 用户头像
    */
    private String customerPhoto;

    /**
    * 注册时间
    */
    private Date registerTime;

    /**
    * 登录账号
    */
    private String accountNum;

    /**
    * 登录密码
    */
    private String accountPwd;

    /**
    * 绑定手机号
    */
    private String accountMobile;

    /**
    * 绑定邮箱
    */
    private String accountMail;
}