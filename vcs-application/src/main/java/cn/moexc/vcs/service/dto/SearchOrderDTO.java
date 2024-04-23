package cn.moexc.vcs.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SearchOrderDTO {
    /**
     * 订单名称 - 商品名称
     */
    private String title;
    /**
     * 状态 00:已创建, 01:已付款, 02:已发货, 03:已验收, 04:已取消, 05:超时未付款自动取消
     */
    private String status;
    /**
     * 下单时间起
     */
    private Date createTimeRangeBefore;
    /**
     * 下单时间止
     */
    private Date createTimeRangeAfter;
    /**
     * 付款时间起
     */
    private Date payTimeRangeBefore;
    /**
     * 付款时间止
     */
    private Date payTimeRangeAfter;
}
