package cn.moexc.vcs.service.dto;

import cn.moexc.vcs.infrasture.mybatis.entity.Trade;
import cn.moexc.vcs.infrasture.mybatis.entity.TradeBid;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TradeDTO {
    /**
     * 名称
     */
    @NotBlank(message = "参数不可为空：专场名称")
    private String tradeName;
    /**
     * 开始时间
     */
    @NotBlank(message = "参数不可为空：专场开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
        @NotBlank(message = "参数不可为空：标的名称")
        private String name;
        /**
         * 倒计时
         */
        @NotBlank(message = "参数不可为空：标的倒计时")
        @Min(value = 1, message = "标的倒计时不可小于1")
        private Integer countDown;
        /**
         * 恢复计时
         */
        @NotBlank(message = "参数不可为空：标的恢复计时")
        @Min(value = 1, message = "恢复计时不可小于1")
        private Integer resetCd;
        /**
         * 底价
         */
        @NotBlank(message = "参数不可为空：标的底价")
        @Min(value = 0, message = "标的底价不可小于0")
        private BigDecimal startPrice;
        /**
         * 竞价幅度
         */
        @NotBlank(message = "参数不可为空：标的竞价幅度")
        @Min(value = 0, message = "标的竞价幅度不可小于0")
        private BigDecimal bidPrice;
    }

    public static TradeDTO gen(Trade tradeEntity, List<TradeBid> tradeBidEntities){
        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setTradeName(tradeEntity.getName());
        tradeDTO.setStartTime(tradeEntity.getStartTime());
        List<Bid> bids = new ArrayList<>();
        tradeBidEntities.forEach(tradeBidEntity -> {
            Bid bid = new Bid();
            bid.setName(tradeBidEntity.getName());
            bid.setCountDown(tradeBidEntity.getCountDown());
            bid.setResetCd(tradeBidEntity.getResetCd());
            bid.setStartPrice(tradeBidEntity.getStartPrice());
            bid.setBidPrice(tradeBidEntity.getBidPrice());
            bids.add(bid);
        });
        tradeDTO.setBids(bids);
        return tradeDTO;
    }
}
