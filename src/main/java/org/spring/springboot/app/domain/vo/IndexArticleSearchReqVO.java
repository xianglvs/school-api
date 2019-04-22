package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.spring.springboot.app.base.Page;

@ApiModel(description = "参数")
@Data
public class IndexArticleSearchReqVO extends Page {

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String title;

    /**
     * 文章概述
     */
    @ApiModelProperty(value = "文章概述")
    private String description;

    /**
     * 文章所属分类
     */
    @ApiModelProperty(value = "文章所属分类id")
    private String indexCategoryId;

    /**
     * 禁用标志
     */
    @ApiModelProperty(value = "禁用标志,1.false 否, 2.true 是,默认false")
    private Boolean disableFlag;

    /**
     * 删除标志
     */
    @ApiModelProperty(value = "删除标志,1.false 否, 2.true 是,默认false")
    private Boolean delFlag;

    public String getTitle() {
        return title == null ? null : title.trim();
    }

    public String getDescription() {
        return description == null ? null : description.trim();
    }

    public Boolean getDisableFlag() {
        if (disableFlag == null) {
            disableFlag = Boolean.FALSE;
        }
        return disableFlag;
    }

    public Boolean getDelFlag() {
        if (delFlag == null) {
            delFlag = Boolean.FALSE;
        }
        return delFlag;
    }
}