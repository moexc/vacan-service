package cn.moexc.vcs.service.cmdfactory;

import cn.moexc.vcs.domain.customer.RegisterCommond;
import cn.moexc.vcs.service.dto.RegisterDTO;

public class RegisterCmdFactory {
    public static RegisterCommond gen(RegisterDTO registerDTO){
        RegisterCommond commond = new RegisterCommond();
        commond.setNickname(registerDTO.getNickname());
        commond.setAccountnum(registerDTO.getUsername());
        commond.setAccountpwd(registerDTO.getPassword());
        return commond;
    }
}
