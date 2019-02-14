package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@ApiModel(description = "接收参数")
@Data
public class UserSearchVO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "登录名")
    private String loginName;
    @ApiModelProperty(value = "编号")
    private String no;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "排序字段")
    private String sortFild;
    @ApiModelProperty(value = "排序方式")
    private String sortOrder;
    @ApiModelProperty(value = "开始创建时间")
    private String startCreateDate;
    @ApiModelProperty(value = "结束创建时间")
    private String endCreateDate;
    @ApiModelProperty(value = "用户类型 1.系统用户 2.网站用户", allowableValues = "1,2")
    private String type;
    @ApiModelProperty(value = "页数")
    private Integer pageNum;
    @ApiModelProperty(value = "每页行数")
    private Integer pageSize;

    public Integer getPageNum() {
        if(pageNum == null){
            pageNum = 1;
        }
        return pageNum;
    }
    public Integer getPageSize() {
        if(pageSize == null){
            pageSize = 20;
        }
        if(pageSize > 100){
            pageSize = 100;
        }
        return pageSize;
    }
}
