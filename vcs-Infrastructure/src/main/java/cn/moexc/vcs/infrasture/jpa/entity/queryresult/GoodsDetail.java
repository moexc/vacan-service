package cn.moexc.vcs.infrasture.jpa.entity.queryresult;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Getter
@Setter
public class GoodsDetail {
    private String id;
    private String title;
    private String photo;
    private String subdescr;
    private String detail;
    private BigDecimal origPrice;
    private BigDecimal price;
    private BigDecimal quantity;
    private String status;
    private Date createTime;

    public GoodsDetail(String id, String title, String photo, String subdescr, String detail, BigDecimal origPrice, BigDecimal price, Integer quantity, String status, Date createTime) {
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.subdescr = subdescr;
        this.detail = detail;
        this.origPrice = origPrice;
        this.price = price;
        this.quantity = new BigDecimal(quantity);
        this.status = status;
        this.createTime = createTime;
    }
}
