package cn.moexc.vcs.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenDTO {
    private String token;
    private String refToken;
}
