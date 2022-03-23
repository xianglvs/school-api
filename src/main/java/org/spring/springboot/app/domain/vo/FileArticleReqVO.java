package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "文章保存图片信息")
@Data
public class FileArticleReqVO {
    /**
     * 文章id
     */
    @ApiModelProperty(value = "文章Id")
    private String articleId;

    /**
     * 文章概述
     */
    @ApiModelProperty(value = "图片访问路径列表")
    private List<String> imagePaths;
}
