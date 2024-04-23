package cn.moexc.vcs.domain.customer;

import cn.moexc.vcs.domain.Utils;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CustomerDomain {
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

    public void register(RegisterCommond commond){
        this.id = Utils.genUUID();
        this.customerName = commond.getNickname();
        this.registerTime = new Date();
        this.accountNum = commond.getAccountnum();
        this.accountPwd = commond.getAccountpwd();
        this.customerPhoto = "";
    }
}
