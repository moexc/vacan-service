package cn.moexc.vcs.web;

import cn.moexc.vcs.service.AuthService;
import cn.moexc.vcs.service.dto.LoginDTO;
import cn.moexc.vcs.service.dto.RegisterDTO;
import cn.moexc.vcs.web.config.auth.Auth;
import cn.moexc.vcs.web.config.auth.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/flushToken")
    public R refresh(@RequestParam("reftoken") String reftoken){
        return R.success(authService.flushToken(reftoken));
    }

    @PostMapping("/register")
    public R register(@RequestBody @Valid RegisterDTO registerDTO){
        authService.register(registerDTO);
        return R.success("ok");
    }
}
