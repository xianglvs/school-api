package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.spring.springboot.app.base.Page;

@ApiModel(description = "请求参数")
@Data
public class SysRoleSearchReqVO extends Page {

    @ApiModelProperty(value = "机构id")
    private String officeId;

    @ApiModelProperty(value = "父级id")
    private String parentId;

    @ApiModelProperty(value = "角色中文名")
    private String name;

    @ApiModelProperty(value = "角色英文名")
    private String enName;

    @ApiModelProperty(value = "禁用标志,1.false 否, 2.true 是")
    private Boolean disableFlag;

    @ApiModelProperty(value = "删除标志,1.false 否, 2.true 是")
    private Boolean delFlag;

    @ApiModelProperty(value = "备注")
    private String remarks;

}