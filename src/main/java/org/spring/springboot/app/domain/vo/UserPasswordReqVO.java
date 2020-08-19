package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ApiModel(description = "接收参数")
@Data
public class UserPasswordReqVO implements Serializable {
    private static final long serialVersionUID = -1L;

    @NotEmpty(message = "原密码不能为空")
    @Length(min = 6, max = 30, message = "原密码应该在6-30个字符之间")
    private String oldPassword;
    @NotEmpty(message = "新密码不能为空")
    @Length(min = 6, max = 30, message = "新密码应该在6-30个字符之间")
    @ApiModelProperty(value = "密码", example = "000000")
    private String password;


}
