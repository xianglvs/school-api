package org.spring.springboot.app.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.*;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.*;
import org.spring.springboot.app.service.SysUserService;
import org.spring.springboot.util.IpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api(description = "用户管理接口", tags = ApiIndex.USER)
@RequestMapping(value = "/api/user")
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "用户登录", notes = "登录后取到ticket凭证,调用\"创建token\"接口得到token\n(admin/670b14728ad9902aecba32e22fa4f6bd)")
    @PostMapping(value = "/login")
    public R<UserLoginResVO> login(
            HttpServletRequest request,
            @ApiParam(value = "查询参数") @RequestBody UserLoginReqVO userLoginReqVO) {
        userLoginReqVO.setLoginIp(IpUtil.getIpAddr(request));
        UserLoginResVO userLoginResVO = sysUserService.login(userLoginReqVO);
        return new R(userLoginResVO);
    }


    @ApiOperation(value = "创建token")
    @PostMapping(value = "/token")
    @ResponseBody
    public R<UserTokenResVO> token(
            @ApiParam(value = "参数") @RequestBody UserTicketReqVO userTicketReqVO) {
        UserTokenResVO vo = sysUserService.token(userTicketReqVO.getTicket());
        return new R(vo);
    }


    @ApiOperation(value = "刷新token")
    @PutMapping(value = "/token")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    public R<UserTokenResVO> token(
            @RequestBody UserTokenReqVO userTokenReqVO
    ) {
        UserTokenResVO vo = sysUserService.refreshToken(userTokenReqVO.getToken());
        return new R(vo);
    }

    @ApiOperation(value = "查询用户信息列表")
    @GetMapping(value = "/list")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<PageInfo<List<SysUserResVO>>> selectBySearch(
            @ApiParam(value = "查询参数") @ModelAttribute UserSearchVO vo) {
        List<SysUserResVO> list = sysUserService.selectBySearch(vo);
        PageInfo pageInfo = new PageInfo(list);
        return new R(pageInfo);
    }

    @ApiOperation(value = "查询单个用户信息")
    @GetMapping(value = "/{userId}")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R<SysUserResVO> selectBySearch(
            @PathVariable("userId") String userId) {
        SysUserResVO vo = sysUserService.selectById(userId);
        return new R(vo);
    }

    @ApiOperation(value = "创建用户")
    @PostMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R create(
            @ApiParam(value = "添加参数") @Valid @RequestBody SysUserCreateReqVO sysUserCreateReqVO) {
        sysUserService.create(sysUserCreateReqVO);
        return new R();
    }


    @ApiOperation(value = "修改用户")
    @PutMapping(value = "")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R update(
            @ApiParam(value = "修改参数") @Valid @RequestBody SysUserUpdateReqVO sysUserUpdateReqVO) {
        sysUserService.update(sysUserUpdateReqVO);
        return new R();
    }

    @ApiOperation(value = "修改当前登录用户")
    @PutMapping(value = "/current")
    @ApiImplicitParam(name = "token", value = "签名", paramType = "query", dataType = "String")
    @Token
    public R updateCurrent(
            @ApiIgnore User user,
            @ApiParam(value = "修改参数") @Valid @RequestBody SysUserUpdateSessionReqVO sysUserUpdateSessionReqVO) {
        sysUserUpdateSessionReqVO.setId(user.getId());
        SysUserUpdateReqVO vo = new SysUserUpdateReqVO();
        BeanUtils.copyProperties(sysUserUpdateSessionReqVO,vo);
        sysUserService.update(vo);
        return new R();
    }

}
