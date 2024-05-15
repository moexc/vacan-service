package cn.moexc.vcs.service;

import cn.moexc.vcs.domain.AlterException;
import cn.moexc.vcs.domain.auth.AuthDomain;
import cn.moexc.vcs.domain.auth.CreateTokenCommand;
import cn.moexc.vcs.domain.auth.TokenDTO;
import cn.moexc.vcs.domain.customer.RegisterCommond;
import cn.moexc.vcs.domain.auth.AuthDomainRepository;
import cn.moexc.vcs.domain.customer.CustomerDomain;
import cn.moexc.vcs.domain.customer.CustomerDomainRepository;
import cn.moexc.vcs.service.cmdfactory.TokenCmdFactory;
import cn.moexc.vcs.service.cmdfactory.RegisterCmdFactory;
import cn.moexc.vcs.service.dto.LoginDTO;
import cn.moexc.vcs.service.dto.RegisterDTO;
import cn.moexc.vcs.service.vo.LoginVO;
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

    public LoginVO login(LoginDTO loginDTO){
        CustomerDomain customerDomain = customerDomainRepository.findByNumAndPwd(loginDTO.getUsername(), loginDTO.getPassword());
        if (customerDomain == null) throw new AlterException("登录失败：账号或密码错误！");
        CreateTokenCommand cmd = TokenCmdFactory.createCmd(customerDomain.getId(), customerDomain.getCustomerName(), loginDTO.getReme());
        TokenDTO tokens = authDomainRepository.genToken(cmd);
        return new LoginVO(customerDomain.getId(), customerDomain.getCustomerName(), customerDomain.getCustomerPhoto(), tokens.getToken(), tokens.getRefToken());
    }

    public void register(RegisterDTO registerDTO){
        RegisterCommond commond = RegisterCmdFactory.gen(registerDTO);
        CustomerDomain customerDomain = new CustomerDomain();
        if (customerDomainRepository.exists(commond.getAccountnum())) throw new AlterException("注册失败：账号已存在！");
        customerDomain.register(commond);
        customerDomainRepository.save(customerDomain);
    }

    public TokenDTO flushToken(String reftoken) {
        AuthDomain ref = authDomainRepository.byId(reftoken);
        if (ref == null) return null;
        CreateTokenCommand cmd = TokenCmdFactory.createCmd(ref.getUserId(), ref.getNickName(), ref.getReme());
        return authDomainRepository.genToken(cmd);
    }
}
