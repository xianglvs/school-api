package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

@ApiModel(description = "接收参数")
public class UserLoginReqVO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "用户名")
    @NotEmpty(message = "用户名不能为空")
    private String loginName;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 30, message = "密码应该在6-30个字符之间")
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(hidden = true)
    private String loginIp;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
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
