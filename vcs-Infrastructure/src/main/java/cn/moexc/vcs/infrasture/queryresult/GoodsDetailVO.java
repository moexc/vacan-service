package cn.moexc.vcs.infrasture.queryresult;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodsDetailVO {
    private String id;
    private String title;
    private String photo;
    private String subdescr;
    private String detail;
    private BigDecimal oldPrice;
    private BigDecimal price;
    private BigDecimal quantity;
    private String status;
    private Long createTime;
    private String classify;
}
