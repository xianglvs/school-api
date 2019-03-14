package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@ApiModel(description = "新增参数")
@Data
public class SysOfficeSearchReqVO {

    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级id,不设或者设为0为顶级父类")
    private String parentId;


    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
    private String name;


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
     * 禁用标志
     */
    @ApiModelProperty(value = "禁用标志,1.false 否, 2.true 是")
    private Boolean disableFlag;

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setName(String name) {
        this.name = name == null ? name : name.trim();
    }

    public void setSysAreaId(String sysAreaId) {
        this.sysAreaId = name == null ? name : name.trim();
    }

    public void setAddress(String address) {
        this.address = name == null ? name : name.trim();
    }

    public void setZipCode(String zipCode) {
        this.zipCode = name == null ? name : name.trim();
    }

    public void setMaster(String master) {
        this.master = name == null ? name : name.trim();
    }

    public void setPhone(String phone) {
        this.phone = name == null ? name : name.trim();
    }

    public void setEmail(String email) {
        this.email = name == null ? name : name.trim();
    }
}