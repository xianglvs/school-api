package org.spring.springboot.app.domain.po;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.spring.springboot.app.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "index_images")
@Data
@EqualsAndHashCode(callSuper = false)
public class IndexImagesPO extends BaseEntity {

    /**
     * 文章唯一标识
     */
    @Column(name = "article_id")
    private String articleId;

    /**
     * 图片名称
     */
    @Column(name = "image_name")
    private String imageName;

    /**
     * 图片存放路径
     */
    @Column(name = "image_path")
    private String imagePath;

    /**
     * 图片访问路径
     */
    @Column(name = "image_access_path")
    private String imageAccessPath;

    /**
     * 图片大小
     */
    @Column(name = "image_size")
    private Long imageSize;

    /**
     * 图片宽
     */
    @Column(name = "image_width")
    private Integer imageWidth;

    /**
     * 图片高
     */
    @Column(name = "image_height")
    private Integer imageHeight;

    /**
     * 备注信息
     */
    @Column(name = "remarks")
    private String remarks;
}