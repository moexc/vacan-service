package cn.moexc.vcs.service.vo;

import cn.moexc.vcs.infrasture.jpa.entity.queryresult.GoodsDetail;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsVO4Detail {
    private String id;
    private String title;
    private String photo;
    private String subdescr;
    private String detail;
    private BigDecimal origPrice;
    private BigDecimal price;
    private BigDecimal quantity;
    private String status;
    private Long createTime;

    public static GoodsVO4Detail gen(GoodsDetail goodsDetail){
        GoodsVO4Detail result = new GoodsVO4Detail();
        result.setId(goodsDetail.getId());
        result.setTitle(goodsDetail.getTitle());
        result.setPhoto(goodsDetail.getPhoto());
        result.setSubdescr(goodsDetail.getSubdescr());
        result.setDetail(goodsDetail.getDetail());
        result.setOrigPrice(goodsDetail.getOrigPrice());
        result.setPrice(goodsDetail.getPrice());
        result.setQuantity(goodsDetail.getQuantity());
        result.setStatus(goodsDetail.getStatus());
        result.setCreateTime(goodsDetail.getCreateTime().getTime());
        return result;
    }

}
