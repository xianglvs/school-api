package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@ApiModel(description = "新增参数")
@Data
public class SysUserCreateReqVO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "1.系统用户 2.网站用户", allowableValues = "1,2")
    @NotNull(message = "用户类型必须选择")
    private Integer type;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "所属区域ID")
    private String sysAreaId;

    @ApiModelProperty(value = "所属机构ID")
    private String sysOfficeId;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "登录名(必传),1-20个字符")
    @NotNull(message = "登录名不能为空")
    @Length(min = 1, max = 20, message = "登录名为1-20个字符")
    private String loginName;

    @ApiModelProperty(value = "密码(必传),6-50个字符,传入md5加密后的值")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "工号")
    private String no;

    @ApiModelProperty(value = "姓名")
    @NotNull(message = "姓名不能为空")
    @Length(min = 1, max = 50, message = "姓名为1-50个字符之间")
    private String name;

    @ApiModelProperty(value = "邮编")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "qq号")
    private String qq;

    @ApiModelProperty(value = "微信号")
    private String weixin;

    @ApiModelProperty(value = "头像访问地址")
    private String photo;

    @ApiModelProperty(value = "用户所具有的角色ID列表")
    private List<String> roles;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }
}
