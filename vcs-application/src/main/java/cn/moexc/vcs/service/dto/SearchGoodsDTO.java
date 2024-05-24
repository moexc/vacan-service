package cn.moexc.vcs.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchGoodsDTO {
    /**
     * 商品名称
     */
    private String name;
    /**
     * 创建时间起
     */
    private String timeRangeBefore;
    /**
     * 创建时间止
     */
    private String timeRangeAfter;
    /**
     * 状态
     */
    private String status;
}
