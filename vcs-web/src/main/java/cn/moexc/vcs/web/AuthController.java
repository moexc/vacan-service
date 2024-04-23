package cn.moexc.vcs.web;

import cn.moexc.vcs.service.AuthService;
import cn.moexc.vcs.service.dto.LoginDTO;
import cn.moexc.vcs.service.dto.RegisterDTO;
import cn.moexc.vcs.web.config.auth.Auth;
import cn.moexc.vcs.web.config.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public R login(@RequestBody @Valid LoginDTO loginDTO){
        return R.success(authService.login(loginDTO));
    }

    @PostMapping("/logout")
    public R logout(@Auth User user){
        authService.logout(user.getToken());
        return R.success("ok");
    }

    @PostMapping("/register")
    public R register(@RequestBody @Valid RegisterDTO registerDTO){
        authService.register(registerDTO);
        return R.success("ok");
    }
}
