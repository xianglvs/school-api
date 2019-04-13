package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysAreaInsertReqVO {

    /**
     * 父级编号
     */
    @ApiModelProperty(value="父级编号")
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
    @ApiModelProperty(value="区域编码")
    private String code;

    /**
     * 备注信息
     */
    @ApiModelProperty(value="备注信息")
    private String remarks;

}