package cn.moexc.vcs.infrasture.auth;

import cn.moexc.vcs.domain.auth.AuthDomain;
import cn.moexc.vcs.domain.customer.CustomerDomain;

public class AuthDomainFactory {
    public static AuthDomain genDoamin(CustomerDomain customerDomain, Boolean rememberMe){
        AuthDomain authDomain = new AuthDomain();
        authDomain.setUserId(customerDomain.getId());
        authDomain.setNickName(customerDomain.getCustomerName());
        authDomain.setImg(customerDomain.getCustomerPhoto());
        authDomain.setRememberMe(rememberMe);
        return authDomain;
    }
}
