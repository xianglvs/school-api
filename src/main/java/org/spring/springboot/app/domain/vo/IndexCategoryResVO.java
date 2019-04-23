package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.spring.springboot.app.base.BaseEntity;

import javax.persistence.Column;
import java.util.Date;

@ApiModel(description = "请求结果")
@Data
public class IndexCategoryResVO {

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
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createDate;
}