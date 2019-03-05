package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "返回结果")
@Data
public class SysRoleResVO {

    /**
     * 编号
     */
    @ApiModelProperty(value = "唯一id")
    private String id;

    /**
     * 归属机构
     */
    @ApiModelProperty(value = "归属机构")
    private String officeId;

    /**
     * 角色父类
     */
    @ApiModelProperty(value = "角色父类")
    private String parentId;

    /**
     * 角色父类索引
     */
    @ApiModelProperty(value = "角色父类索引")
    private String parentIds;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;

    /**
     * 英文名称
     */
    @ApiModelProperty(value = "英文名称")
    private String enName;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    private Date updateDate;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    /**
     * 禁用标志
     */
    @ApiModelProperty(value = "禁用标志,false:否,true:是")
    private Boolean disableFlag;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记,false:否,true:是")
    private Boolean delFlag;





}