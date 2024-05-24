package cn.moexc.vcs.infrasture.mybatis.entity;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 专场
    */
@Getter
@Setter
@ToString
public class Trade {
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
    private Date startTime;

    /**
    * 结束时间
    */
    private Date endTime;

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
}