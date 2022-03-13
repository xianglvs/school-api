package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.po.IndexArticlePO;
import org.spring.springboot.app.domain.vo.IndexArticleResVO;
import org.spring.springboot.app.domain.vo.IndexArticleSearchReqVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface IndexArticleMapper extends Mapper<IndexArticlePO> {

    int insertIndexArticleCategory(@Param("articleId") String articleId, @Param("categoryId") String categoryId);

    int deleteIndexArticleCategory(@Param("articleId") String articleId);

    List<IndexArticleResVO> selectPage(IndexArticleSearchReqVO vo);

    int deleteById(@Param("id") String id);

}