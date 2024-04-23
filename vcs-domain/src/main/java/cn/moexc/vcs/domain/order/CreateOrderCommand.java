package cn.moexc.vcs.domain.order;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateOrderCommand {
    private String customerId;
    private Integer quantity;
    private String address;

    //商品信息
    private String goodsId;
    private String title;
    private String photo;
    private BigDecimal price;
    private Integer goodsQuantity;
    private String status;
}
