package org.spring.springboot.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.*;
import org.spring.springboot.app.domain.vo.IndexCategoryResVO;
import org.spring.springboot.app.service.IndexCategoryService;
import org.spring.springboot.app.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "文章分类管理接口", tags = ApiIndex.CATEGORY)
@RequestMapping(value = "/api/cate")
@RestController
public class IndexCategoryController {

    @Autowired
    private IndexCategoryService service;

    @ApiOperation(value = "查询分类列表,只查一级")
    @GetMapping(value = "/list")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<List<IndexCategoryResVO>> selectList(
            @ApiParam(value = "父类id") @RequestParam(value = "parentId", required = false) String parentId) {
        List<IndexCategoryResVO> list = service.selectListByParent(parentId);
        return new R(list);
    }

    @ApiOperation(value = "查询单个分类信息")
    @GetMapping(value = "/{id}")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<IndexCategoryResVO> selectById(
            @ApiParam(value = "分类id") @PathVariable("id") String id) {
        IndexCategoryResVO vo = service.selectById(id);
        return new R(vo);
    }

    @ApiOperation(value = "创建分类")
    @PostMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R insert(
            @ApiParam(value = "参数") @Valid @RequestBody IndexCategoryInsertReqVO vo) {
        service.insert(vo);
        return new R();
    }

    @ApiOperation(value = "修改分类")
    @PutMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R update(
            @ApiParam(value = "参数") @Valid @RequestBody IndexCategoryUpdateReqVO vo) {
        service.update(vo);
        return new R();
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R delete(
            @ApiParam(value = "区域id") @RequestParam(value = "id") String id
    ) {
        service.deleteById(id);
        return new R();
    }
}
