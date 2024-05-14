package cn.moexc.vcs.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsDTO {
    private String title;
    private String photo;
    private String subdescr;
    private String detail;
    private BigDecimal oldPrice;
    private BigDecimal price;
    private Integer quantity;
    private String classify;
}
