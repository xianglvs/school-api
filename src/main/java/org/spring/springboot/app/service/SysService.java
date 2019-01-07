package org.spring.springboot.app.service;


import java.util.List;

import org.spring.springboot.app.base.Result;
import org.spring.springboot.app.dao.ArticleMapper;
import org.spring.springboot.app.domain.po.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 访问请求的Service
 *
 * @author ry
 * @date 2018/9/25
 */
@Service
public class SysService {
    @Autowired
    ArticleMapper articleMapper;

    public Result getResult() {
        Result r = new Result();
        List<Article> list = articleMapper.selectAll();
        r.setData(list);
        return r;
    }


}
