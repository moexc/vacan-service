package cn.moexc.vcs.service.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginVO {
    private String userId;
    private String nickName;
    private String photo;
    private String token;
    private String reftoken;
}
