package cn.moexc.vcs.infrasture.jpa.entity.queryresult;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Goods4CreateOrder {
    private String goodsId;
    private String title;
    private String photo;
    private BigDecimal price;
    private Integer quantity;
    private String status;

    public Goods4CreateOrder(String goodsId, String title, String photo, BigDecimal price, Integer quantity, String status) {
        this.goodsId = goodsId;
        this.title = title;
        this.photo = photo;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
    }
}
