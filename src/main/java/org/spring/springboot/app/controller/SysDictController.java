package org.spring.springboot.app.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.SysDictInsertReqVO;
import org.spring.springboot.app.domain.vo.SysDictResVO;
import org.spring.springboot.app.domain.vo.SysDictSearchVO;
import org.spring.springboot.app.domain.vo.SysDictUpdateReqVO;
import org.spring.springboot.app.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = ApiIndex.DICT)
@RequestMapping(value = "/api/dict")
@RestController
public class SysDictController {

    @Autowired
    private SysDictService service;


    @ApiOperation(value = "分页查询字典")
    @GetMapping(value = "/list")

    @Token
    public R<PageInfo<List<SysDictResVO>>> selectPage(
            @ApiParam(value = "查询参数") @ModelAttribute SysDictSearchVO vo) {
        List<SysDictResVO> list = service.selectPage(vo);
        PageInfo pageInfo = new PageInfo(list);
        return new R(pageInfo);
    }

    @ApiOperation(value = "查询单个字典信息")
    @GetMapping(value = "/{id}")

    @Token
    public R<SysDictResVO> selectById(
            @ApiParam(value = "字典id") @PathVariable("id") String id) {
        SysDictResVO vo = service.selectById(id);
        return new R(vo);
    }

    @ApiOperation(value = "创建字典")
    @PostMapping(value = "")

    @Token
    public R insert(
            @ApiParam(value = "参数") @Valid @RequestBody SysDictInsertReqVO vo) {
        service.insert(vo);
        return new R();
    }

    @ApiOperation(value = "修改字典")
    @PutMapping(value = "")
    @Token
    public R update(
            @ApiParam(value = "参数") @Valid @RequestBody SysDictUpdateReqVO vo) {
        service.update(vo);
        return new R();
    }

    @ApiOperation(value = "删除字典")
    @DeleteMapping(value = "")

    public R delete(
            @ApiParam(value = "字典id") @RequestParam(value = "id") String id
    ) {
        service.deleteById(id);
        return new R();
    }
}
