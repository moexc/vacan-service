package cn.moexc.vcs.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class TradeResultDTO {
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 状态 0：等待 1：运行中 2：结束
     */
    private String status;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 结束时间
     */
    private Long endTIme;

    private Map<String, Bid> bidMap;

    @Getter
    @Setter
    public static class Bid{
        /**
         * id
         */
        private String id;
        /**
         * 名称
         */
        private String name;
        /**
         * 状态 0：等待 1：运行中 2：结束
         */
        private String status;
        /**
         * 开始时间
         */
        private Long startTime;
        /**
         * 结束时间
         */
        private Long endTIme;
        /**
         * 当前价
         */
        private BigDecimal price;
        /**
         * 成交状态 0：流拍 1：成交
         */
        private String bidStatus;
        /**
         * 成交人
         */
        private String bidUser;
    }

}
