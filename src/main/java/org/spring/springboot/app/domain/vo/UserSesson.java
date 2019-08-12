package org.spring.springboot.app.domain.vo;

import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;
import java.util.List;

@ApiIgnore
public class UserSesson implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String loginName;
    private String officeId;
    private String officeName;
    private String sysAreaId;
    private String sysAreaName;
    private List<SysRoleResVO> roles;
    private List<SysMenuResVO> menus;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeId() {
        return officeId;
    }

    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    public List<SysMenuResVO> getMenus() {
        return menus;
    }

    public void setMenus(List<SysMenuResVO> menus) {
        this.menus = menus;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getSysAreaId() {
        return sysAreaId;
    }

    public void setSysAreaId(String sysAreaId) {
        this.sysAreaId = sysAreaId;
    }

    public String getSysAreaName() {
        return sysAreaName;
    }

    public void setSysAreaName(String sysAreaName) {
        this.sysAreaName = sysAreaName;
    }

    public List<SysRoleResVO> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRoleResVO> roles) {
        this.roles = roles;
    }
}
