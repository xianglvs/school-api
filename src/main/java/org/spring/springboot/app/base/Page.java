package org.spring.springboot.app.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
@ApiModel
@Data
public class Page {
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
