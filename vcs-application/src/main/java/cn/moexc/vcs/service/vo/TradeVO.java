package cn.moexc.vcs.service.vo;

import cn.moexc.vcs.infrasture.jpa.entity.TradeEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradeVO {
    /**
     * 专场ID
     */
    private String id;

    /**
     * 专场名称
     */
    private String name;

    /**
     * 开始时间
     */
    private Long startTime;

    /**
     * 结束时间
     */
    private Long endTime;

    /**
     * 状态
     */
    private String status;

    /**
     * 发送竞价引擎状态
     */
    private String sendStatus;

    /**
     * 标的数
     */
    private Integer bidCount;

    public static TradeVO gen(TradeEntity entity){
        TradeVO vo = new TradeVO();
        vo.setId(entity.getId());
        vo.setName(entity.getName());
        vo.setStartTime(entity.getStartTime().getTime());
        vo.setEndTime(entity.getEndTime() == null ? null : entity.getEndTime().getTime());
        if ("0".equals(entity.getStatus())){
            if (System.currentTimeMillis() >= entity.getStartTime().getTime()){
                vo.setStatus("运行中");
            }else {
                vo.setStatus("未启动");
            }
        }else if ("1".equals(entity.getStatus())){
            vo.setStatus("运行中");
        }else if ("2".equals(entity.getStatus())){
            vo.setStatus("已结束");
        }else {
            vo.setStatus("未知");
        }

        if ("0".equals(entity.getSendStatus())){
            vo.setSendStatus("未发送");
        }else if ("1".equals(entity.getSendStatus())){
            vo.setSendStatus("成功");
        }else if ("2".equals(entity.getSendStatus())){
            vo.setSendStatus("失败");
        }else{
            vo.setSendStatus("未知");
        }
        vo.setBidCount(entity.getBidCount());
        return vo;
    }

}