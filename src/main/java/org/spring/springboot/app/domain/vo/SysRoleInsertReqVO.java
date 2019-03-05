package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

@ApiModel(description = "参数")
@Data
public class SysRoleInsertReqVO {

    /**
     * 归属机构
     */
    @ApiModelProperty(value = "归属机构id")
    @NotBlank(message = "归属机构id不能为空")
    private String officeId;

    /**
     * 角色父类
     */
    @ApiModelProperty(value = "角色父类id,不传或者传0为顶级父类")
    private String parentId;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    @NotBlank(message = "角色名称不能空")
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

    public Boolean getDisableFlag() {
        if(disableFlag == null){
            disableFlag = false;
        }
        return disableFlag;
    }
}