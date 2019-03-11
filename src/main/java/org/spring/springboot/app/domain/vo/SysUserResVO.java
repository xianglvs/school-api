package org.spring.springboot.app.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: eric
 * Date: 2017-11-09
 * Time: 19:37
 */
@ApiModel(description = "返回参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserResVO implements Serializable {
    private static final long serialVersionUID = -1L;

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户类型 1系统 2个人")
    private Integer type;

    @ApiModelProperty(value = "登录名")
    private String loginName;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "手机")
    private String mobile;

    @ApiModelProperty(value = "头像")
    private String photo;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "编号")
    private String no;

    @ApiModelProperty(value = "邮编")
    private String email;

    @ApiModelProperty(value = "qq号")
    private String qq;

    @ApiModelProperty(value = "微信号")
    private String weixin;

    @ApiModelProperty(value = "区域")
    private String sysAreaId;

    @ApiModelProperty(value = "登录ip")
    private String loginIp;

    @ApiModelProperty(value = "登录时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date loginDate;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "纬度")
    private Double latitude;

    @ApiModelProperty(value = "创建者", hidden = true)
    private String createBy;

    @ApiModelProperty(value = "创建时间", hidden = true)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    @ApiModelProperty(value = "创建时间", hidden = true)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date create_date;

    @ApiModelProperty(value = "更新者", hidden = true)
    private String updateBy;

    @ApiModelProperty(value = "更新时间", hidden = true)
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateDate;

    @ApiModelProperty(value = "备注信息", hidden = true)
    private String remarks;

    @ApiModelProperty(value = "显示手机号码")
    private String showMobile;

    public String getShowMobile() {
        if (StringUtils.isNotBlank(this.phone) && this.phone.length() > 7) {
            showMobile = this.phone.substring(0, 3) + "****" + this.phone.substring(7, this.phone.length());
        }
        if (StringUtils.isNotBlank(this.mobile) && this.mobile.length() > 7) {
            showMobile = this.mobile.substring(0, 3) + "****" + this.mobile.substring(7, this.mobile.length());
        }
        return showMobile;
    }

}
