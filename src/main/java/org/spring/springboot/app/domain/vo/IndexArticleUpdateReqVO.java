package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@ApiModel(description = "参数")
@Data
public class IndexArticleUpdateReqVO {

    /**
     * 文章id
     */
    @NotEmpty(message = "文章id不能为空")
    private String id;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    @Length(min = 1, max = 200, message = "文章标题应该在1到200个字符之间")
    private String title;

    /**
     * 文章概述
     */
    @ApiModelProperty(value = "文章概述")
    private String description;

    /**
     * 列表图片
     */
    @ApiModelProperty(value = "列表图片")
    private String listImage;

    /**
     * 列表样式
     * 0.纯文字
     * 1.左文字右图
     * 2.上文字下图
     * 3.单图
     * 4.3图并排
     */
    @ApiModelProperty(value = "列表样式0.纯文字1.左文字右图2.上文字下图3.单图4.三图并排")
    private String listType;

    /**
     * 文章所属分类
     */
    @ApiModelProperty(value = "文章所属分类id列表")
    private List<String> indexCategoryIds;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer sort;


    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容")
    private String content;

    /**
     * 显示原图
     */
    @ApiModelProperty(value = "显示原图,true.是 ,false.否")
    private Boolean originalImage;


    /**
     * 禁用标志
     */
    @ApiModelProperty(value = "禁用标志,1.false 否, 2.true 是")
    private Boolean disableFlag;

}