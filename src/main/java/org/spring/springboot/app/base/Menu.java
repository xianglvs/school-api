package org.spring.springboot.app.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "返回参数")
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "菜单id")
    private String id;
    @ApiModelProperty(value = "菜单父级")
    private String parentId;
    @ApiModelProperty(value = "菜单所有父级以,号分割")
    private String parentIds;
    @ApiModelProperty(value = "菜单名称")
    private String name;
    @ApiModelProperty(value = "菜单排序")
    private Long sort;
    @ApiModelProperty(value = "菜单访问地址")
    private String href;
    @ApiModelProperty(value = "访问类型 1._blank 新页面打开 2._self 直接跳转")
    private String target;
    @ApiModelProperty(value = "菜单图标")
    private String icon;
    @ApiModelProperty(value = "隐藏标志 0.否 1.是")
    private Boolean hiddenFlag;
    @ApiModelProperty(value = "权限标识")
    private String permission;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds == null ? null : parentIds.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Boolean getHiddenFlag() {
        return hiddenFlag;
    }

    public void setHiddenFlag(Boolean hiddenFlag) {
        this.hiddenFlag = hiddenFlag;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission == null ? null : permission.trim();
    }


}
