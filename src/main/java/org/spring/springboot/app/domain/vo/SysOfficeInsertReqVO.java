package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(description = "新增参数")
@Data
public class SysOfficeInsertReqVO {

    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级id,不设或者设为0为顶级父类")
    private String parentId;


    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    @NotBlank(message = "机构名称不能空")
    private String name;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Long sort;

    /**
     * 所属区域
     */
    @ApiModelProperty(value = "所属区域id")
    private String sysAreaId;

    /**
     * 联系地址
     */
    @ApiModelProperty(value = "联系地址")
    private String address;

    /**
     * 邮政编码
     */
    @ApiModelProperty(value = "邮政编码")
    private String zipCode;

    /**
     * 负责人
     */
    @ApiModelProperty(value = "负责人")
    private String master;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remarks;

    /**
     * 禁用标志
     */
    @ApiModelProperty(value = "禁用标志,1.false 否, 2.true 是")
    private Boolean disableFlag;

    public Boolean getDisableFlag() {
        if(disableFlag == null){
            disableFlag = false;
        }
        return disableFlag;
    }

}