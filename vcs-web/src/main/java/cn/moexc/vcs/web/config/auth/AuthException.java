package cn.moexc.vcs.web.config.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthException extends RuntimeException{
    private int status;
    private String message;
}
