package org.spring.springboot.app.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.spring.springboot.app.base.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "index_category")
@Data
@EqualsAndHashCode(callSuper = false)
public class IndexCategoryPO extends BaseEntity {

    /**
     * 名称
     */
    private String title;

    /**
     * 上级分类id
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 上级分类索引用,号分割
     */
    @Column(name = "parent_ids")
    private String parentIds;

    /**
     * 排序号
     */
    private Integer sort;
}