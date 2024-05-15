package cn.moexc.vcs.domain.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTokenCommand {
    private String userId;
    private String nickName;
    private Boolean reme;
}
