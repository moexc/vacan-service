package cn.moexc.vcs.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RegisterDTO {
    @NotBlank(message = "户名不可为空")
    private String nickname;
    @NotBlank(message = "账号不可为空")
    private String username;
    @NotBlank(message = "密码不可为空")
    private String password;
}
