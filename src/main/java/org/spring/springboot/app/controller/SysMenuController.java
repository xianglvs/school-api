package org.spring.springboot.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.Menu;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.SysMenuCreateReqVO;
import org.spring.springboot.app.domain.vo.SysMenuUpdateReqVO;
import org.spring.springboot.app.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "菜单管理接口", tags = ApiIndex.MENU)
@RequestMapping(value = "/api/menu")
@RestController
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "缓存中查询用户菜单")
    @GetMapping(value = "/cache")
    public R<List<Menu>> selectBySessionUser(
            @RequestParam("token") String token
    ) {
        List<Menu> list = sysMenuService.selectByCache(token);
        return new R(list);
    }

    @ApiOperation(value = "查询用户菜单")
    @GetMapping(value = "/user")
    public R<List<Menu>> selectByUser(
            @ApiParam(value = "签名") @RequestParam(value = "token") String token
    ) {
        List<Menu> list = sysMenuService.selectByToken(token);
        return new R(list);
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping(value = "/all")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<List<Menu>> selectAll(
            @ApiParam(value = "删除标志") @RequestParam(value = "delFlag", required = false) Boolean delFlag,
            @ApiParam(value = "禁用标志") @RequestParam(value = "disableFlag", required = false) Boolean disableFlag
    ) {
        List<Menu> list = sysMenuService.selectAll(delFlag, disableFlag);
        return new R(list);
    }

    @ApiOperation(value = "查询单个菜单")
    @GetMapping(value = "/{menuId}")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R<Menu> selectById(
            @ApiParam(value = "菜单ID") @PathVariable("menuId") String menuId
    ) {
        Menu menu = sysMenuService.selectById(menuId);
        return new R(menu);
    }

    @ApiOperation(value = "创建菜单")
    @PostMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R<Menu> insert(
            @ApiParam(value = "添加参数") @Valid @RequestBody SysMenuCreateReqVO vo
    ) {
        sysMenuService.insert(vo);
        return new R();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R<Menu> update(
            @ApiParam(value = "修改参数") @Valid @RequestBody SysMenuUpdateReqVO vo
    ) {
        sysMenuService.update(vo);
        return new R();
    }

}
