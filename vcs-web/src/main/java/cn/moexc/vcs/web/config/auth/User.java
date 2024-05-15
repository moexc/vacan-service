package cn.moexc.vcs.web.config.auth;

import cn.moexc.vcs.domain.auth.AuthDomain;
import lombok.Getter;

@Getter
public class User {
    private String userId;
    private String nickName;

    public User initVal(AuthDomain authDomain){
        this.userId = authDomain.getUserId();
        this.nickName = authDomain.getNickName();
        return this;
    }
}
