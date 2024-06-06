package cn.moexc.vcs.service.cmdfactory;

import cn.moexc.vcs.domain.goods.GoodsDomain;
import cn.moexc.vcs.domain.order.CreateOrderCommand;
import cn.moexc.vcs.infrasture.queryresult.Goods4CreateOrder;
import cn.moexc.vcs.service.dto.CreateOrderDTO;

public class CreaterOrderCmdFactory {

    public static CreateOrderCommand gen(CreateOrderDTO createOrderDTO, GoodsDomain goodsDomain, String cid){
        CreateOrderCommand cmd = new CreateOrderCommand();
        cmd.setCustomerId(cid);
        cmd.setQuantity(createOrderDTO.getQuantity());
        cmd.setAddress(createOrderDTO.getAddress());
        if (goodsDomain == null){
            return cmd;
        }
        cmd.setGoodsId(goodsDomain.getId());
        cmd.setTitle(goodsDomain.getTitle());
        cmd.setPhoto(goodsDomain.getPhoto());
        cmd.setPrice(goodsDomain.getPrice());
        return cmd;
    }

}
