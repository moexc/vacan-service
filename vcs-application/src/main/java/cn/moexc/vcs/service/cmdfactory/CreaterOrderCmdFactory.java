package cn.moexc.vcs.service.cmdfactory;

import cn.moexc.vcs.domain.order.CreateOrderCommand;
import cn.moexc.vcs.infrasture.jpa.entity.queryresult.Goods4CreateOrder;
import cn.moexc.vcs.service.dto.CreateOrderDTO;

public class CreaterOrderCmdFactory {

    public static CreateOrderCommand gen(CreateOrderDTO createOrderDTO, Goods4CreateOrder goods4CreateOrder, String cid){
        CreateOrderCommand cmd = new CreateOrderCommand();
        cmd.setCustomerId(cid);
        cmd.setQuantity(createOrderDTO.getQuantity());
        cmd.setAddress(createOrderDTO.getAddress());
        if (goods4CreateOrder == null){
            return cmd;
        }
        cmd.setGoodsId(goods4CreateOrder.getGoodsId());
        cmd.setTitle(goods4CreateOrder.getTitle());
        cmd.setPhoto(goods4CreateOrder.getPhoto());
        cmd.setPrice(goods4CreateOrder.getPrice());
        cmd.setGoodsQuantity(goods4CreateOrder.getQuantity());
        cmd.setStatus(goods4CreateOrder.getStatus());
        return cmd;
    }

}
