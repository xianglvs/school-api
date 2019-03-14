package org.spring.springboot.app.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.spring.springboot.app.base.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_office")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysOfficePO extends BaseEntity {

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
     * 机构名称
     */
    private String name;

    /**
     * 排序号
     */
    private Long sort;

    /**
     * 所属区域
     */
    @Column(name = "sys_area_id")
    private String sysAreaId;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 邮政编码
     */
    @Column(name = "zip_code")
    private String zipCode;

    /**
     * 负责人
     */
    private String master;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

}