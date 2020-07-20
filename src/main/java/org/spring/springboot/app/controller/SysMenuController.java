package org.spring.springboot.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.domain.vo.SysMenuResVO;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.SysMenuInsertReqVO;
import org.spring.springboot.app.domain.vo.SysMenuUpdateReqVO;
import org.spring.springboot.app.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@Api(tags = ApiIndex.MENU)
@RequestMapping(value = "/api/menu")
@RestController
@Token
public class SysMenuController {

    @Autowired
    private SysMenuService sysMenuService;

    @ApiOperation(value = "缓存中查询登录用户菜单")
    @GetMapping(value = "/cache")
    public R<List<SysMenuResVO>> selectBySessionUser(
            @RequestParam("token") String token
    ) {
        List<SysMenuResVO> list = sysMenuService.selectByCache(token);
        return new R(list);
    }

    @ApiOperation(value = "查询登录用户菜单")
    @GetMapping(value = "/user")
    public R<List<SysMenuResVO>> selectByUser(
           @ApiIgnore String token
    ) {
        List<SysMenuResVO> list = sysMenuService.selectByToken(token);
        return new R(list);
    }

    @ApiOperation(value = "查询角色菜单")
    @GetMapping(value = "/role/{roleId}")
    @Token
    public R<List<SysMenuResVO>> selectByRole(
            @ApiParam(value = "角色id", required = true) @PathVariable(value = "roleId") String roleId,
            @ApiParam(value = "删除标志") @RequestParam(value = "delFlag", required = false) Boolean delFlag,
            @ApiParam(value = "禁用标志") @RequestParam(value = "disableFlag", required = false) Boolean disableFlag
    ) {
        List<SysMenuResVO> list = sysMenuService.selectByRoleId(roleId, delFlag, disableFlag);
        return new R(list);
    }

    @ApiOperation(value = "查询用户菜单")
    @GetMapping(value = "/user/{userId}")
    @Token
    public R<List<SysMenuResVO>> selectByUser(
            @ApiParam(value = "用户id",required = true) @PathVariable(value = "userId") String userId,
            @ApiParam(value = "删除标志") @RequestParam(value = "delFlag", required = false) Boolean delFlag,
            @ApiParam(value = "禁用标志") @RequestParam(value = "disableFlag", required = false) Boolean disableFlag
    ) {
        List<SysMenuResVO> list = sysMenuService.selectByUserId(userId, delFlag, disableFlag);
        return new R(list);
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping(value = "/all")

    @Token
    public R<List<SysMenuResVO>> selectAll(
            @ApiParam(value = "删除标志") @RequestParam(value = "delFlag", required = false) Boolean delFlag,
            @ApiParam(value = "禁用标志") @RequestParam(value = "disableFlag", required = false) Boolean disableFlag
    ) {
        List<SysMenuResVO> list = sysMenuService.selectAll(delFlag, disableFlag);
        return new R(list);
    }

    @ApiOperation(value = "查询单个菜单")
    @GetMapping(value = "/{menuId}")

    @Token
    public R<SysMenuResVO> selectById(
            @ApiParam(value = "菜单ID") @PathVariable("menuId") String menuId
    ) {
        SysMenuResVO sysMenuResVO = sysMenuService.selectById(menuId);
        return new R(sysMenuResVO);
    }

    @ApiOperation(value = "创建菜单")
    @PostMapping(value = "")

    @Token
    public R insert(
            @ApiParam(value = "添加参数") @Valid @RequestBody SysMenuInsertReqVO vo
    ) {
        sysMenuService.insert(vo);
        return new R();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping(value = "")

    @Token
    public R update(
            @ApiParam(value = "修改参数") @Valid @RequestBody SysMenuUpdateReqVO vo
    ) {
        sysMenuService.update(vo);
        return new R();
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping(value = "")

    public R delete(
            @ApiParam(value = "菜单id") @RequestParam(value = "id") String id
    ) {
        sysMenuService.deleteById(id);
        return new R();
    }

}
