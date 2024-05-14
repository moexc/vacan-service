package cn.moexc.vcs.service.cmdfactory;

import cn.moexc.vcs.domain.goods.CreateGoodsCommand;
import cn.moexc.vcs.domain.goods.UpdateGoodsCommand;
import cn.moexc.vcs.service.dto.GoodsDTO;

public class GoodsCmdFactory {
    public static CreateGoodsCommand createCmd(GoodsDTO dto){
        CreateGoodsCommand command = new CreateGoodsCommand();
        command.setTitle(dto.getTitle());
        command.setPhoto(dto.getPhoto());
        command.setSubdescr(dto.getSubdescr());
        command.setDetail(dto.getDetail());
        command.setOldPrice(dto.getOldPrice());
        command.setPrice(dto.getPrice());
        command.setQuantity(dto.getQuantity());
        command.setClassify(dto.getClassify());
        return command;
    }

    public static UpdateGoodsCommand updateCmd(GoodsDTO dto){
        UpdateGoodsCommand command = new UpdateGoodsCommand();
        command.setTitle(dto.getTitle());
        command.setPhoto(dto.getPhoto());
        command.setSubdescr(dto.getSubdescr());
        command.setDetail(dto.getDetail());
        command.setOldPrice(dto.getOldPrice());
        command.setPrice(dto.getPrice());
        command.setQuantity(dto.getQuantity());
        command.setClassify(dto.getClassify());
        return command;
    }
}
