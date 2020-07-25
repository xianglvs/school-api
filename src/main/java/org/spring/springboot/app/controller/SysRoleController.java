package org.spring.springboot.app.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.*;
import org.spring.springboot.app.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = ApiIndex.ROLE)
@RequestMapping(value = "/api/role")
@RestController
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value = "查询用户角色")
    @GetMapping(value = "/user/{userId}")
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
    public R<List<SysRoleResVO>> selectAll(
            @ApiParam(value = "删除标志") @RequestParam(value = "delFlag", required = false) Boolean delFlag,
            @ApiParam(value = "禁用标志") @RequestParam(value = "disableFlag", required = false) Boolean disableFlag
    ) {
        List<SysRoleResVO> list = sysRoleService.selectAll(delFlag, disableFlag);
        return new R(list);
    }


    @ApiOperation(value = "分页查询角色")
    @GetMapping(value = "/page")
    public R<PageInfo<SysRoleResVO>> selectPage(
            @ApiParam(value = "查询参数") @ModelAttribute SysRoleSearchReqVO vo
            ) {
        R result = new R();
        List<SysRoleResVO> list = sysRoleService.selectPage(vo);
        PageInfo<SysRoleResVO> pageInfo = new PageInfo<>(list);
        result.setData(pageInfo);
        return result;
    }

    @ApiOperation(value = "查询单个角色")
    @GetMapping(value = "/{roleId}")
    public R<SysRoleResVO> selectById(
            @ApiParam(value = "菜单ID") @PathVariable("roleId") String roleId
    ) {
        SysRoleResVO vo = sysRoleService.selectById(roleId);
        return new R(vo);
    }

    @ApiOperation(value = "创建角色")
    @PostMapping(value = "")
    @Token
    public R<SysMenuResVO> insert(
            @ApiParam(value = "添加参数") @Valid @RequestBody SysRoleInsertReqVO vo
    ) {
        sysRoleService.insert(vo);
        return new R();
    }

    @ApiOperation(value = "修改角色")
    @PutMapping(value = "")
    @Token
    public R<SysMenuResVO> update(
            @ApiParam(value = "修改参数") @Valid @RequestBody SysRoleUpdateReqVO vo
    ) {
        sysRoleService.update(vo);
        return new R();
    }
    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "")
    @Token
    public R<SysMenuResVO> delete(
            @ApiParam(value = "角色id") @RequestParam(value = "id") String id
    ) {
        sysRoleService.deleteById(id);
        return new R();
    }
}
