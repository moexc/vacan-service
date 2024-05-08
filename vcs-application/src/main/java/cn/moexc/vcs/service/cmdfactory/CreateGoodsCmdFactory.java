package cn.moexc.vcs.service.cmdfactory;

import cn.moexc.vcs.domain.goods.CreateGoodsCommand;
import cn.moexc.vcs.service.dto.CreateGoodsDTO;

public class CreateGoodsCmdFactory {
    public static CreateGoodsCommand gen(CreateGoodsDTO dto, String photoUrl){
        CreateGoodsCommand command = new CreateGoodsCommand();
        command.setTitle(dto.getTitle());
        command.setPhoto(photoUrl);
        command.setSubdescr(dto.getSubdescr());
        command.setDetail(dto.getDetail());
        command.setOldPrice(dto.getOldPrice());
        command.setPrice(dto.getPrice());
        command.setQuantity(dto.getQuantity());
        command.setClassify(dto.getClassify());
        return command;
    }
}
