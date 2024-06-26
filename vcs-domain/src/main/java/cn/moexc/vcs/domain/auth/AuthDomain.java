package cn.moexc.vcs.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class AuthDomain implements Serializable {
    private String userId;
    private String nickName;
    private Boolean reme;
}
