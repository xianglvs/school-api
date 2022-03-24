package org.spring.springboot.app.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "请求结果")
@Data
public class IndexArticleResVO implements Serializable {
    private static final long serialVersionUID = -6829433111580900201L;
    /**
     * 唯一编码
     */
    @ApiModelProperty(value = "文章id")
    private String id;

    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String title;

    /**
     * 文章概述
     */
    @ApiModelProperty(value = "文章概述")
    private String description;

    /**
     * 列表图片
     */
    @ApiModelProperty(value = "列表图片")
    private String listImage;

    /**
     * 列表样式
     * 0.纯文字
     * 1.左文字右图
     * 2.上文字下图
     * 3.单图
     * 4.3图并排
     */
    @ApiModelProperty(value = "列表样式0.纯文字1.左文字右图2.上文字下图3.单图4.三图并排")
    private String listType;

    /**
     * 排序号
     */
    @ApiModelProperty(value = "排序号")
    private Integer sort;

    /**
     * 创建用户
     */
    @ApiModelProperty(value = "创建用户id")
    private String createBy;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    /**
     * 最后更新用户
     */
    @ApiModelProperty(value = "最后更新用户id")
    private String updateBy;

    /**
     * 最后更新时间
     */
    @ApiModelProperty(value = "最后更新时间")
    private Date updateDate;

    /**
     * 禁用标志 0.否 1.是
     */
    @ApiModelProperty(value = "禁用标志 否.false 是.true")
    private boolean disableFlag;

    /**
     * 逻辑删除标志 0.正常 1.删除
     */
    @ApiModelProperty(value = "逻辑删除标志,否.false 是.true")
    private boolean delFlag;

    /**
     * 文章内容
     */
    @ApiModelProperty(value = "文章内容")
    private String content;

}