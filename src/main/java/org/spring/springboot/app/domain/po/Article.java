package org.spring.springboot.app.domain.po;

import java.util.Date;
import javax.persistence.*;

public class Article {
    /**
     * 唯一编码
     */
    @Id
    private String id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章概述
     */
    private String description;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 创建用户
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 最后更新用户
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 最后更新时间
     */
    @Column(name = "update_date")
    private Date updateDate;

    /**
     * 禁用标志 0.否 1.是
     */
    @Column(name = "disable_flag")
    private Boolean disableFlag;

    /**
     * 逻辑删除标志 0.正常 1.删除
     */
    @Column(name = "del_flag")
    private Boolean delFlag;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 获取唯一编码
     *
     * @return id - 唯一编码
     */
    public String getId() {
        return id;
    }

    /**
     * 设置唯一编码
     *
     * @param id 唯一编码
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取文章标题
     *
     * @return title - 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置文章标题
     *
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取文章概述
     *
     * @return description - 文章概述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置文章概述
     *
     * @param description 文章概述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取排序号
     *
     * @return sort - 排序号
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置排序号
     *
     * @param sort 排序号
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 获取创建用户
     *
     * @return create_by - 创建用户
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * 设置创建用户
     *
     * @param createBy 创建用户
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取最后更新用户
     *
     * @return update_by - 最后更新用户
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * 设置最后更新用户
     *
     * @param updateBy 最后更新用户
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * 获取最后更新时间
     *
     * @return update_date - 最后更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 设置最后更新时间
     *
     * @param updateDate 最后更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 获取禁用标志 0.否 1.是
     *
     * @return disable_flag - 禁用标志 0.否 1.是
     */
    public Boolean getDisableFlag() {
        return disableFlag;
    }

    /**
     * 设置禁用标志 0.否 1.是
     *
     * @param disableFlag 禁用标志 0.否 1.是
     */
    public void setDisableFlag(Boolean disableFlag) {
        this.disableFlag = disableFlag;
    }

    /**
     * 获取逻辑删除标志 0.正常 1.删除
     *
     * @return del_flag - 逻辑删除标志 0.正常 1.删除
     */
    public Boolean getDelFlag() {
        return delFlag;
    }

    /**
     * 设置逻辑删除标志 0.正常 1.删除
     *
     * @param delFlag 逻辑删除标志 0.正常 1.删除
     */
    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取文章内容
     *
     * @return content - 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置文章内容
     *
     * @param content 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}