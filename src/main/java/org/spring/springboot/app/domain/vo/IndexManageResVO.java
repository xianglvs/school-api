package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "请求结果")
@Data
public class IndexManageResVO implements Serializable {

    private static final long serialVersionUID = -4239305216369120463L;
    /**
     * 主键ID
     */
    @ApiModelProperty(value="主键ID")
    private String id;

    /**
     * 标题
     */
    @ApiModelProperty(value="标题")
    private String title;

    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private String mobile;

}