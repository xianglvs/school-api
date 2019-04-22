package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class SysDictInsertReqVO {

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
     * 排序（升序）
     */
    @ApiModelProperty(value="排序（升序）")
    private Long sort;

}