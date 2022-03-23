package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "文件信息")
@Data
public class FileResVO {

    @ApiModelProperty(value = "保存图片ID")
    private String id;
    /**
     * 文章标题
     */
    @ApiModelProperty(value = "原文件名")
    private String name;

    /**
     * 文章概述
     */
    @ApiModelProperty(value = "文件路径")
    private String path;
}
