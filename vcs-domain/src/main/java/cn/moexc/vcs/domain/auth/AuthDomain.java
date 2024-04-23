package cn.moexc.vcs.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthDomain implements Serializable {
    private String token;
    private String userId;
    private String nickName;
    private String img;
    private Boolean rememberMe;

    public AuthDomain(String token, String userId, String nickName) {
        this.token = token;
        this.userId = userId;
        this.nickName = nickName;
    }
}
