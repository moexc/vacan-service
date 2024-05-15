package cn.moexc.vcs.service.cmdfactory;

import cn.moexc.vcs.domain.auth.CreateTokenCommand;

public class TokenCmdFactory {
    public static CreateTokenCommand createCmd(String userId, String nickName, Boolean reme){
        CreateTokenCommand cmd = new CreateTokenCommand();
        cmd.setUserId(userId);
        cmd.setNickName(nickName);
        cmd.setReme(reme);
        return cmd;
    }
}
