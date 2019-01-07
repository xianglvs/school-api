package org.spring.springboot.app.dao;

import org.spring.springboot.app.domain.po.Article;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ArticleMapper extends Mapper<Article> {
}