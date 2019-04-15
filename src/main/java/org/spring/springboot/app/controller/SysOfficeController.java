package org.spring.springboot.app.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.Menu;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.*;
import org.spring.springboot.app.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "机构管理接口", tags = ApiIndex.OFFICE)
@RequestMapping(value = "/api/office")
@RestController
public class SysOfficeController {

    @Autowired
    private SysOfficeService sysOfficeService;

    @ApiOperation(value = "查询用户机构")
    @GetMapping(value = "/user/{userId}")
    public R<List<SysOfficeResVO>> selectByUser(
            @ApiParam(value = "用户id") @PathVariable(value = "userId") String userId,
            @ApiParam(value = "删除标志") @RequestParam(value = "delFlag", required = false) Boolean delFlag,
            @ApiParam(value = "禁用标志") @RequestParam(value = "disableFlag", required = false) Boolean disableFlag
    ) {
        List<SysOfficeResVO> list = sysOfficeService.selectByUserId(userId, delFlag, disableFlag);
        return new R(list);
    }

    @ApiOperation(value = "查询所有机构")
    @GetMapping(value = "/all")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<List<SysOfficeResVO>> selectAll(
            @ApiParam(value = "删除标志") @RequestParam(value = "delFlag", required = false) Boolean delFlag,
            @ApiParam(value = "禁用标志") @RequestParam(value = "disableFlag", required = false) Boolean disableFlag
    ) {
        List<SysOfficeResVO> list = sysOfficeService.selectAll(delFlag, disableFlag);
        return new R(list);
    }


    @ApiOperation(value = "分页查询机构")
    @GetMapping(value = "/page")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<PageInfo<SysOfficeResVO>> selectPage(
            @ApiParam(value = "查询参数") @ModelAttribute SysOfficeSearchReqVO vo
            ) {
        R result = new R();
        List<SysOfficeResVO> list = sysOfficeService.selectPage(vo);
        PageInfo<SysOfficeResVO> pageInfo = new PageInfo<>(list);
        result.setData(pageInfo);
        return result;
    }

    @ApiOperation(value = "查询单个机构")
    @GetMapping(value = "/{officeId}")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R<SysOfficeResVO> selectById(
            @ApiParam(value = "菜单ID") @PathVariable("officeId") String officeId
    ) {
        SysOfficeResVO vo = sysOfficeService.selectById(officeId);
        return new R(vo);
    }

    @ApiOperation(value = "创建机构")
    @PostMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R<Menu> insert(
            @ApiParam(value = "添加参数") @Valid @RequestBody SysOfficeInsertReqVO vo
    ) {
        sysOfficeService.insert(vo);
        return new R();
    }

    @ApiOperation(value = "修改机构")
    @PutMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R<Menu> update(
            @ApiParam(value = "修改参数") @Valid @RequestBody SysOfficeUpdateReqVO vo
    ) {
        sysOfficeService.update(vo);
        return new R();
    }

    @ApiOperation(value = "删除机构")
    @DeleteMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R delete(
            @ApiParam(value = "机构id") @RequestParam(value = "id") String id
    ) {
        sysOfficeService.deleteById(id);
        return new R();
    }

}
