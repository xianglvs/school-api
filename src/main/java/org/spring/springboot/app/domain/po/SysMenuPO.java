package org.spring.springboot.app.domain.po;

import lombok.Data;
import org.spring.springboot.app.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sys_menu")
@Data
public class SysMenuPO extends BaseEntity{

    /**
     * 唯一编号
     */
    @Id
    private String id;
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
     * 链接
     */
    private String href;

    /**
     * 目标
     */
    private String target;

    /**
     * 图标
     */
    private String icon;

    /**
     * 隐藏标志
0.否
1.是
     */
    @Column(name = "hidden_flag")
    private Boolean hiddenFlag;

    /**
     * 权限标识
     */
    private String permission;


}