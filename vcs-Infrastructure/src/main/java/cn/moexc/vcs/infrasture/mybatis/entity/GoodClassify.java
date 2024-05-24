package cn.moexc.vcs.infrasture.mybatis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
    * 商品分类
    */
@Getter
@Setter
@ToString
public class GoodClassify {
    /**
    * 编码
    */
    private String code;

    /**
    * 名称
    */
    private String name;

    /**
    * 排序字段
    */
    private Integer sort;

    /**
    * 父编码
    */
    private String parentCode;
}