package org.spring.springboot.app.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.spring.springboot.app.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "index_article")
@Data
@EqualsAndHashCode(callSuper = false)
public class IndexArticlePO extends BaseEntity {

    /**
     * 文章标题
     */
    private String title;

    /**
     * 显示原图
     */
    @Column(name = "original_image")
    private Boolean originalImage;

    /**
     * 文章概述
     */
    private String description;

    /**
     * 排序号
     */
    private Integer sort;

    /**
     * 文章内容
     */
    private String content;


    /**
     * 列表图片
     */
    @Column(name = "list_image")
    private String listImage;

    /**
     * 列表样式
     * 0.纯文字
     * 1.左文字右图
     * 2.上文字下图
     * 3.单图
     * 4.3图并排
     */
    @Column(name = "list_type")
    private String listType;

}