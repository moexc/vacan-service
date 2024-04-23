package cn.moexc.vcs.service.cmdfactory;

import cn.moexc.vcs.domain.address.CreateAddressCommand;
import cn.moexc.vcs.service.dto.CreateAddressDTO;

public class CreateAddressCmdFacroty {
    public static CreateAddressCommand gen(CreateAddressDTO dto, String cid){
        CreateAddressCommand cmd = new CreateAddressCommand();
        cmd.setCustomerId(cid);
        cmd.setCity(dto.getCity());
        cmd.setDetailed(dto.getDetailed());
        cmd.setPostCode(dto.getPostCode());
        cmd.setIsdefault(dto.getIsdefault());
        return cmd;
    }
}
