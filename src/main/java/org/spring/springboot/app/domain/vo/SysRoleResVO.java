package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "返回参数")
@Data
public class SysRoleResVO implements Serializable {

    private static final long serialVersionUID = 5844901892821141989L;
    @ApiModelProperty(value = "角色id")
    private String id;

    @ApiModelProperty(value = "机构id")
    private String officeId;

    @ApiModelProperty(value = "父级id")
    private String parentId;

    @ApiModelProperty(value = "父级索引")
    private String parentIds;

    @ApiModelProperty(value = "角色中文名")
    private String name;

    @ApiModelProperty(value = "角色英文名")
    private String enName;

    @ApiModelProperty(value = "禁用标志,1.false 否, 2.true 是")
    private Boolean disableFlag;

    @ApiModelProperty(value = "备注")
    private String remarks;

}