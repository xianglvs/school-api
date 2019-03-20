package org.spring.springboot.app.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.spring.springboot.app.base.BaseEntity;

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

}