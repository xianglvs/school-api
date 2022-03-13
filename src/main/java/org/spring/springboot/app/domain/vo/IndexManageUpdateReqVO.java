package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@Data
@EqualsAndHashCode(callSuper = false)
public class IndexManageUpdateReqVO {

    /**
     * 唯一主键
     */
    @ApiModelProperty(value = "唯一主键")
    @NotEmpty(message = "唯一主键不能为空")
    private String id;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @NotEmpty(message = "标题不能为空")
    private String title;

    /**
     * 电话
     */
    @ApiModelProperty(value = "电话")
    private String mobile;

}