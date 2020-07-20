package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.spring.springboot.app.base.Page;

@Data
public class SysDictSearchVO extends Page {

    /**
     * 数据值
     */
    @ApiModelProperty("数据值")
    private String value;

    /**
     * 标签名
     */
    @ApiModelProperty(value="标签名")
    private String label;

    /**
     * 类型
     */
    @ApiModelProperty(value="类型")
    private String type;

    /**
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String description;

    /**
     * 禁用标志
     */
    @ApiModelProperty(value="禁用标志")
    private Boolean disableFlag;

    /**
     * 删除标志
     */
    @ApiModelProperty(value="删除标志")
    private Boolean delFlag;

}