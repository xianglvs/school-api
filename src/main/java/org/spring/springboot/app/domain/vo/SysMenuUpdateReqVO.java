package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;

@ApiModel(description = "新增参数")
@Data
public class SysMenuUpdateReqVO {

    @ApiModelProperty(value = "菜单id")
    @NotNull(message = "菜单id不能为空")
    private String id;

    @ApiModelProperty(value = "父级编号,设为0为顶级父类")
    private String parentId;

    @ApiModelProperty(value = "名称")
    @NotNull(message = "菜单名称不能为空")
    private String name;

    @ApiModelProperty(value = "排序")
    private Long sort;

    @ApiModelProperty(value = "链接")
    private String href;

    @ApiModelProperty(value = "目标,1._blank 新标签页打开 2._self 当前页面跳转", allowableValues = "_blank,_self")
    private String target;

    @ApiModelProperty(value = "图标样式")
    private String icon;

    @ApiModelProperty(value = "隐藏标志,0.否 1.是")
    private Boolean hiddenFlag;

    @ApiModelProperty(value = "权限标识")
    private String permission;

    @ApiModelProperty(value = "备注信息")
    private String remarks;

    public String getParentId() {
        if(StringUtils.isBlank(parentId)){
            parentId = null;
        }
        return parentId;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public void setHref(String href) {
        this.href = href == null ? null : href.trim();
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Boolean getHiddenFlag() {
        if (hiddenFlag == null) {
            return false;
        }
        return hiddenFlag;
    }

}