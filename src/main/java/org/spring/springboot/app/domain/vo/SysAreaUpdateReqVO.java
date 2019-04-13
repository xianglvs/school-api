package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysAreaUpdateReqVO {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;

    /**
     * 父级id
     */
    @ApiModelProperty(value="父级id")
    private String parentId;

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
    @ApiModelProperty(value="区域行政编码")
    private String code;

    /**
     * 备注信息
     */
    @ApiModelProperty(value="备注信息")
    private String remarks;

    /**
     * 删除标记
     */
    @ApiModelProperty(value="删除标记")
    private Boolean delFlag;

}