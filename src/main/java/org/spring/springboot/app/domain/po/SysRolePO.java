package org.spring.springboot.app.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.spring.springboot.app.base.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_role")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRolePO extends BaseEntity {

    /**
     * 归属机构
     */
    @Column(name = "office_id")
    private String officeId;

    /**
     * 角色父类
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 角色父类索引
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 英文名称
     */
    @Column(name = "en_name")
    private String enName;

}