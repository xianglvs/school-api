package org.spring.springboot.app.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.IndexArticleInsertReqVO;
import org.spring.springboot.app.domain.vo.IndexArticleResVO;
import org.spring.springboot.app.domain.vo.IndexArticleSearchReqVO;
import org.spring.springboot.app.domain.vo.IndexArticleUpdateReqVO;
import org.spring.springboot.app.service.IndexArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "文章管理接口", tags = ApiIndex.ARTICLE)
@RequestMapping(value = "/api/article")
@RestController
public class IndexArticleController {

    @Autowired
    private IndexArticleService service;


    @ApiOperation(value = "分页查询文章")
    @GetMapping(value = "/list")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<PageInfo<List<IndexArticleResVO>>> selectPage(
            @ApiParam(value = "查询参数") @ModelAttribute IndexArticleSearchReqVO vo) {
        List<IndexArticleResVO> list = service.selectPage(vo);
        PageInfo pageInfo = new PageInfo(list);
        return new R(pageInfo);
    }

    @ApiOperation(value = "查询单个文章信息")
    @GetMapping(value = "/{id}")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<IndexArticleResVO> selectById(
            @ApiParam(value = "文章id") @PathVariable("id") String id) {
        IndexArticleResVO vo = service.selectById(id);
        return new R(vo);
    }

    @ApiOperation(value = "创建文章")
    @PostMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R insert(
            @ApiParam(value = "参数") @Valid @RequestBody IndexArticleInsertReqVO vo) {
        service.insert(vo);
        return new R();
    }

    @ApiOperation(value = "修改文章")
    @PutMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R update(
            @ApiParam(value = "参数") @Valid @RequestBody IndexArticleUpdateReqVO vo) {
        service.update(vo);
        return new R();
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R delete(
            @ApiParam(value = "文章id") @RequestParam(value = "id") String id
    ) {
        service.deleteById(id);
        return new R();
    }
}
