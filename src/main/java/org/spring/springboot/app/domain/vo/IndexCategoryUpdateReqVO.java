package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "请求参数")
@Data
public class IndexCategoryUpdateReqVO {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private String id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String title;

    /**
     * 上级分类id
     */
    @ApiModelProperty(value = "上级分类id")
    private String parentId;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer sort;

}