package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ApiModel(description = "接收参数")
public class UserLoginReqVO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "用户名",example = "admin")
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 30, message = "密码应该在6-30个字符之间")
    @ApiModelProperty(value = "密码",example = "000000")
    private String password;
    @ApiModelProperty(hidden = true)
    private String loginIp;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
