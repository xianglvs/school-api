package org.spring.springboot.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.SysAreaInsertReqVO;
import org.spring.springboot.app.domain.vo.SysAreaResVO;
import org.spring.springboot.app.domain.vo.SysAreaUpdateReqVO;
import org.spring.springboot.app.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = ApiIndex.AREA)
@RequestMapping(value = "/api/area")
@RestController
public class SysAreaController {

    @Autowired
    private SysAreaService service;

    @ApiOperation(value = "查询区域列表")
    @GetMapping(value = "/list")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<List<SysAreaResVO>> selectList(
            @ApiParam(value = "父类id") @RequestParam(value = "parentId", required = false) String parentId) {
        List<SysAreaResVO> list = service.selectListByParent(parentId);
        return new R(list);
    }

    @ApiOperation(value = "查询单个区域信息")
    @GetMapping(value = "/{id}")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<SysAreaResVO> selectById(
            @ApiParam(value = "文章id") @PathVariable("id") String id) {
        SysAreaResVO vo = service.selectById(id);
        return new R(vo);
    }

    @ApiOperation(value = "创建新区域")
    @PostMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R insert(
            @ApiParam(value = "参数") @Valid @RequestBody SysAreaInsertReqVO vo) {
        service.insert(vo);
        return new R();
    }

    @ApiOperation(value = "修改区域")
    @PutMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R update(
            @ApiParam(value = "参数") @Valid @RequestBody SysAreaUpdateReqVO vo) {
        service.update(vo);
        return new R();
    }

    @ApiOperation(value = "删除区域")
    @DeleteMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R delete(
            @ApiParam(value = "区域id") @RequestParam(value = "id") String id
    ) {
        service.deleteById(id);
        return new R();
    }
}
