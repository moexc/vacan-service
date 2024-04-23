package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.domain.auth.AuthDomain;
import cn.moexc.vcs.domain.customer.RegisterCommond;
import cn.moexc.vcs.infrasture.auth.AuthDomainFactory;
import cn.moexc.vcs.domain.auth.AuthDomainRepository;
import cn.moexc.vcs.domain.customer.CustomerDomain;
import cn.moexc.vcs.domain.customer.CustomerDomainRepository;
import cn.moexc.vcs.service.cmdfactory.RegisterCmdFactory;
import cn.moexc.vcs.service.dto.LoginDTO;
import cn.moexc.vcs.service.dto.RegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final CustomerDomainRepository customerDomainRepository;
    private final AuthDomainRepository authDomainRepository;

    @Autowired
    public AuthService(CustomerDomainRepository customerDomainRepository, AuthDomainRepository authDomainRepository) {
        this.customerDomainRepository = customerDomainRepository;
        this.authDomainRepository = authDomainRepository;
    }

    public AuthDomain login(LoginDTO loginDTO){
        CustomerDomain customerDomain = customerDomainRepository.findByNumAndPwd(loginDTO.getUsername(), loginDTO.getPassword());
        if (customerDomain == null) throw new AlterException("登录失败：账号或密码错误！");
        AuthDomain authDomain = AuthDomainFactory.genDoamin(customerDomain, loginDTO.getReme());
        authDomainRepository.save(authDomain);
        return authDomain;
    }

    public void logout(String token){
        authDomainRepository.remove(token);
    }

    public void register(RegisterDTO registerDTO){
        RegisterCommond commond = RegisterCmdFactory.gen(registerDTO);
        CustomerDomain customerDomain = new CustomerDomain();
        if (customerDomainRepository.exists(commond.getAccountnum())) throw new AlterException("注册失败：账号已存在！");
        customerDomain.register(commond);
        customerDomainRepository.save(customerDomain);
    }

}
