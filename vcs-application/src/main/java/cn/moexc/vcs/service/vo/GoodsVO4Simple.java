package cn.moexc.vcs.service.vo;

import cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsSimple;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsVO4Simple {
    private String id;
    private String title;
    private String photo;
    private String subdescr;
    private BigDecimal origPrice;
    private BigDecimal price;
    private BigDecimal quantity;

    public static GoodsVO4Simple gen(GoodsSimple goodsSimple){
        GoodsVO4Simple result = new GoodsVO4Simple();
        result.setId(goodsSimple.getId());
        result.setTitle(goodsSimple.getTitle());
        result.setPhoto(goodsSimple.getPhoto());
        result.setSubdescr(goodsSimple.getSubdescr());
        result.setOrigPrice(goodsSimple.getOrigPrice());
        result.setPrice(goodsSimple.getPrice());
        result.setQuantity(goodsSimple.getQuantity());
        return result;
    }

}