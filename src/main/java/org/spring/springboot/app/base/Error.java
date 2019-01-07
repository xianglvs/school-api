package org.spring.springboot.app.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("具体错误")
public class Error {
    @ApiModelProperty(value = "字段名称")
    private String field;
    @ApiModelProperty(value = "具体错误")
    private String error;

    public Error() {
    }

    public Error(String field, String error) {
        this.field = field;
        this.error = error;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
