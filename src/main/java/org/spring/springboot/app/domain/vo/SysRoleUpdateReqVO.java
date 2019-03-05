package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(description = "参数")
@Data
public class SysRoleUpdateReqVO {

    /**
     * 角色变号
     */
    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id不能为空")
    private String id;

    /**
     * 归属机构
     */
    @ApiModelProperty(value = "归属机构id")
    private String officeId;

    /**
     * 角色父类
     */
    @ApiModelProperty(value = "角色父类")
    private String parentId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色")
    private String name;

    /**
     * 英文名称
     */
    @ApiModelProperty(value = "英文名称")
    private String enName;

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

}