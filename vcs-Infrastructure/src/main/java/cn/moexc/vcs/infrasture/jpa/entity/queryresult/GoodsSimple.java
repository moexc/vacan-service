package cn.moexc.vcs.infrasture.jpa.entity.queryresult;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsSimple {
    private String id;
    private String title;
    private String photo;
    private String subdescr;
    private BigDecimal origPrice;
    private BigDecimal price;
    private BigDecimal quantity;

    public GoodsSimple(String id, String title, String photo, String subdescr, BigDecimal origPrice, BigDecimal price, Integer quantity) {
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.subdescr = subdescr;
        this.origPrice = origPrice;
        this.price = price;
        this.quantity = new BigDecimal(quantity);
    }
}
