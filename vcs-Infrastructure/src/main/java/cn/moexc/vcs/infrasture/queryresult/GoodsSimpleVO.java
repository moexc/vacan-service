package cn.moexc.vcs.infrasture.queryresult;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsSimpleVO {
    private String id;
    private String title;
    private String photo;
    private String subdescr;
    private BigDecimal origPrice;
    private BigDecimal price;
    private Integer quantity;
    private String status;
    private Long createTime;
}
