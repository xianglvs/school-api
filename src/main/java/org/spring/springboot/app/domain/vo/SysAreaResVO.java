package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(description = "请求结果")
@Data
public class SysAreaResVO {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 父级编号
     */
    @ApiModelProperty(value = "父级编号")
    private String parentId;

    /**
     * 所有父级编号
     */
    @ApiModelProperty(value = "所有父级编号")
    private String parentIds;

    /**
     * 名称
     */
    @ApiModelProperty(value="名称")
    private String name;

    /**
     * 排序
     */
    @ApiModelProperty(value="排序")
    private Long sort;

    /**
     * 区域编码
     */
    @ApiModelProperty(value="区域编码")
    private String code;

    /**
     * 区域类型
     */
    @ApiModelProperty(value="区域类型")
    private Integer type;

    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createDate;

    /**
     * 备注信息
     */
    @ApiModelProperty(value="备注信息")
    private String remarks;

}