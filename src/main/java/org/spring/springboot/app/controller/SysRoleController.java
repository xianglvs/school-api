package org.spring.springboot.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.Menu;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.*;
import org.spring.springboot.app.service.SysMenuService;
import org.spring.springboot.app.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(description = "角色管理接口", tags = ApiIndex.ROLE)
@RequestMapping(value = "/api/role")
@RestController
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "查询用户角色")
    @GetMapping(value = "/user/{role}")
    public R<List<SysRoleResVO>> selectByUser(
            @ApiParam(value = "用户id") @PathVariable(value = "userId") String userId,
            @ApiParam(value = "删除标志") @RequestParam(value = "delFlag", required = false) Boolean delFlag,
            @ApiParam(value = "禁用标志") @RequestParam(value = "disableFlag", required = false) Boolean disableFlag
    ) {
        List<SysRoleResVO> list = sysRoleService.selectByUserId(userId, delFlag, disableFlag);
        return new R(list);
    }

    @ApiOperation(value = "查询所有角色")
    @GetMapping(value = "/all")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<List<SysRoleResVO>> selectAll(
            @ApiParam(value = "删除标志") @RequestParam(value = "delFlag", required = false) Boolean delFlag,
            @ApiParam(value = "禁用标志") @RequestParam(value = "disableFlag", required = false) Boolean disableFlag
    ) {
        List<SysRoleResVO> list = sysRoleService.selectAll(delFlag, disableFlag);
        return new R(list);
    }


    @ApiOperation(value = "分页查询角色")
    @GetMapping(value = "/page")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<List<SysRoleResVO>> selectPage(
            @ApiParam(value = "查询参数") @ModelAttribute SysRoleSearchReqVO vo
            ) {
        List<SysRoleResVO> list = sysRoleService.selectPage(vo);
        return new R(list);
    }

    @ApiOperation(value = "查询单个角色")
    @GetMapping(value = "/{roleId}")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R<SysRoleResVO> selectById(
            @ApiParam(value = "菜单ID") @PathVariable("roleId") String roleId
    ) {
        SysRoleResVO vo = sysRoleService.selectById(roleId);
        return new R(vo);
    }

    @ApiOperation(value = "创建角色")
    @PostMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R<Menu> insert(
            @ApiParam(value = "添加参数") @Valid @RequestBody SysRoleCreateReqVO vo
    ) {
        sysRoleService.insert(vo);
        return new R();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R<Menu> update(
            @ApiParam(value = "修改参数") @Valid @RequestBody SysRoleUpdateReqVO vo
    ) {
        sysRoleService.update(vo);
        return new R();
    }

}