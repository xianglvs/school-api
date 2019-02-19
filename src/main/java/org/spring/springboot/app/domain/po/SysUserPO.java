package org.spring.springboot.app.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.spring.springboot.app.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserPO extends BaseEntity {

    /**
     * 用户类型
1.系统用户
2.网站用户
     */
    private Integer type;

    /**
     * 登录名
     */
    @Column(name = "login_name")
    private String loginName;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 用户头像
     */
    private String photo;

    /**
     * 工号
     */
    private String no;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 所属区域
     */
    @Column(name = "sys_area_id")
    private String sysAreaId;

    /**
     * 所属机构
     */
    @Column(name = "sys_office_id")
    private String sysOfficeId;

    /**
     * 最后登陆IP
     */
    @Column(name = "login_ip")
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @Column(name = "last_login_date")
    private Date lastLoginDate;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * qq号
     */
    private String qq;

    /**
     * 微信号
     */
    private String weixin;

}