package cn.moexc.vcs.infrasture.queryresult;

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
}
