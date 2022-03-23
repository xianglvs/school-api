package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.po.IndexImagesPO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.Date;
import java.util.List;

@Repository
public interface IndexImagesMapper extends Mapper<IndexImagesPO> {
    void updateImageArticleIdByPaths(@Param("articleId") String articleId, @Param("paths") List<String> paths);
    void clearArticleId(@Param("id") String id);
}