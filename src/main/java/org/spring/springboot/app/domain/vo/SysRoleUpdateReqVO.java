package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@ApiModel(description = "参数")
@Data
public class SysRoleUpdateReqVO {

    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id不能为空")
    private String id;

    @ApiModelProperty(value = "归属机构")
    private String officeId;

    @ApiModelProperty(value = "父级id,不设或者设为0为顶级父类")
    private String parentId;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "英文名称")
    private String enName;

    @ApiModelProperty(value = "禁用标志,1.false 否, 2.true 是")
    private Boolean disableFlag;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    @ApiModelProperty(value = "菜单id列表")
    private List<String> menuIds;


    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}