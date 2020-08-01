package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "返回参数")
@Data
public class SysOfficeResVO {
    /**
     * 编号
     */
    @ApiModelProperty(value = "机构id")
    private String id;

    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级id")
    private String parentId;

    /**
     * 所有父级编号
     */
    @ApiModelProperty(value = "所有父级id")
    private String parentIds;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称")
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
    private boolean disableFlag;


}