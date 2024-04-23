package cn.moexc.vcs.domain.trade.engine;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class Engine4CreateDTO {
    /**
     * 结果推送地址
     */
    private String resultPushAddress;
    /**
     * id
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 开始时间
     */
    private Long startTime;
    /**
     * 标的
     */
    private List<Bid> bids;

    @Getter
    @Setter
    public static class Bid {
        /**
         * id
         */
        private String id;
        /**
         * 名称
         */
        private String name;
        /**
         * 倒计时
         */
        private Long countDown;
        /**
         * 恢复计时
         */
        private Long resetCd;
        /**
         * 底价
         */
        private BigDecimal startPrice;
        /**
         * 竞价幅度
         */
        private BigDecimal bidPrice;
    }
}
