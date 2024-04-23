package cn.moexc.vcs.infrasture.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 商品分类
 */
@Data
@Entity
@Table(name = "good_classify")
public class GoodClassifyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @Id
    @Column(name = "code", nullable = false)
    private String code;

    /**
     * 名称
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 排序字段
     */
    @Column(name = "sort", nullable = false)
    private Integer sort;

    /**
     * 父编码
     */
    @Column(name = "parent_code", nullable = false)
    private String parentCode;

}
