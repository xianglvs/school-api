package org.spring.springboot.app.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "返回参数")
public class UserLoginResVO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "获取token的凭证")
    private String ticket;

    @ApiModelProperty(value = "ticket过期时间,单位毫秒")
    private Long expire;

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "机构id")
    private String sysOfficeId;

    @ApiModelProperty(value = "机构名")
    private String sysOfficeName;

    @ApiModelProperty(value = "区域ID")
    private String sysAreaId;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "编号")
    private String no;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "邮编")
    private String email;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "用户类型 1系统 2个人")
    private String type;

    @ApiModelProperty(value = "头像")
    private String photo;

    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    @ApiModelProperty(value = "登录时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSysOfficeId() {
        return sysOfficeId;
    }

    public void setSysOfficeId(String sysOfficeId) {
        this.sysOfficeId = sysOfficeId == null ? null : sysOfficeId.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getSysOfficeName() {
        return sysOfficeName;
    }

    public void setSysOfficeName(String sysOfficeName) {
        this.sysOfficeName = sysOfficeName == null ? null : sysOfficeName.trim();
    }

    public String getSysAreaId() {
        return sysAreaId;
    }

    public void setSysAreaId(String sysAreaId) {
        this.sysAreaId = sysAreaId == null ? null : sysAreaId.trim();
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket == null ? null : ticket.trim();
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }
}
