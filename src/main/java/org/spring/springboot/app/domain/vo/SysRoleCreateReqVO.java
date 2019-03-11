package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.spring.springboot.app.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@ApiModel(description = "新增参数")
@Data
public class SysRoleCreateReqVO {

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

}