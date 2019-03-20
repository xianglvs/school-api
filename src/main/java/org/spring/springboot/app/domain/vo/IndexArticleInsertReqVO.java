package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

@ApiModel(description = "参数")
@Data
public class IndexArticleInsertReqVO {

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    @NotBlank(message = "文章标题不能为空")
    @Length(min = 1, max = 200, message = "文章标题应该在1到200个字符之间")
    private String title;

    /**
     * 文章概述
     */
    @ApiModelProperty(value = "文章概述")
    private String description;

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
    @NotBlank(message = "文章内容不能为空")
    private String content;

    /**
     * 禁用标志
     */
    @ApiModelProperty(value = "禁用标志,1.false 否, 2.true 是")
    private Boolean disableFlag;

    public Boolean getDisableFlag() {
        if(disableFlag == null){
            disableFlag = false;
        }
        return disableFlag;
    }
}