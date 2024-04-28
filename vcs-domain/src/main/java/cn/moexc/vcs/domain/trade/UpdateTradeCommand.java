package cn.moexc.vcs.domain.trade;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UpdateTradeCommand {
    /**
     * 名称
     */
    private String name;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 标的
     */
    private List<Bid> bids;

    @Getter
    @Setter
    public static class Bid{
        /**
         * 名称
         */
        private String name;
        /**
         * 倒计时
         */
        private Integer countDown;
        /**
         * 恢复计时
         */
        private Integer resetCd;
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
