package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@ApiModel(description = "新增参数")
@Data
public class SysMenuCreateReqVO {

    @ApiModelProperty(value = "父级id,不设或者设为0为顶级父类")
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


    public void setParentId(String parentId) {
        this.parentId = parentId == null && parentId.trim().length() == 0 ? "0" : parentId.trim();
    }

    public String getParentId() {
        if(parentId == null && parentId.trim().length() == 0){
            parentId = "0";
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