package org.spring.springboot.app.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.spring.springboot.app.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "sys_area")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysAreaPO extends BaseEntity {

    /**
     * 父级编号
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 所有父级编号
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 名称
     */
    private String name;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 区域编码
     */
    private String code;

    /**
     * 区域类型
     */
    private Integer type;

}