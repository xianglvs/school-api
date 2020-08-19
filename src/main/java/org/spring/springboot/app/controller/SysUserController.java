package org.spring.springboot.app.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.Type;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.*;
import org.spring.springboot.app.service.SysUserService;
import org.spring.springboot.exception.BusinessException;
import org.spring.springboot.util.IpUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Api(tags = ApiIndex.USER)
@RequestMapping(value = "/api/user")
@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;


    @ApiOperation(value = "用户登录", notes = "登录后取到ticket凭证,调用\"创建token\"接口得到token")
    @PostMapping(value = "/login")
    public R<UserLoginResVO> login(
            HttpServletRequest request,
            @ApiParam(value = "查询参数") @RequestBody UserLoginReqVO userLoginReqVO) {
        userLoginReqVO.setLoginIp(IpUtil.getIpAddr(request));
        UserLoginResVO userLoginResVO = sysUserService.login(userLoginReqVO);
        return new R(userLoginResVO);
    }

    @ApiOperation(value = "修改密码接口")
    @PutMapping(value = "/password")
    @Token
    public R<UserLoginResVO> password(
            HttpServletRequest request,
            @ApiParam(value = "参数") @RequestBody UserPasswordReqVO userPasswordReqVO) {
        sysUserService.password(userPasswordReqVO);
        return new R();
    }


    @ApiOperation(value = "创建或者刷新token")
    @PostMapping(value = "/token")
    @ResponseBody
    public R<UserTokenResVO> token(
            @ApiParam(value = "参数") @RequestBody UserTicketReqVO userTicketReqVO) {
        UserTokenResVO vo = sysUserService.token(userTicketReqVO.getTicket());
        return new R(vo);
    }

    @ApiOperation(value = "查询当前用户信息")
    @GetMapping(value = "/info")
    public R<UserTokenResVO> getUser(
            @ApiIgnore UserTokenResVO userSession
    ) {
        if (userSession == null || userSession.getId() == null) {
            throw new BusinessException(Type.POWER_VALIDATE_FAIL);
        }
        return new R(userSession);
    }

    @ApiOperation(value = "查询用户信息列表")
    @GetMapping(value = "/list")
    @Token
    public R<PageInfo<List<SysUserResVO>>> selectBySearch(
            @ApiParam(value = "查询参数") @ModelAttribute UserSearchVO vo) {
        List<SysUserResVO> list = sysUserService.selectBySearch(vo);
        PageInfo pageInfo = new PageInfo(list);
        return new R(pageInfo);
    }

    @ApiOperation(value = "查询单个用户信息")
    @GetMapping(value = "/{userId}")
    @Token
    public R<SysUserResVO> selectBySearch(
            @PathVariable("userId") String userId) {
        SysUserResVO vo = sysUserService.selectById(userId);
        return new R(vo);
    }

    @ApiOperation(value = "创建用户")
    @PostMapping(value = "")
    @Token
    public R create(
            @ApiParam(value = "添加参数") @Valid @RequestBody SysUserCreateReqVO sysUserCreateReqVO) {
        sysUserService.create(sysUserCreateReqVO);
        return new R();
    }


    @ApiOperation(value = "修改用户")
    @PutMapping(value = "")
    @Token
    public R update(
            @ApiParam(value = "修改参数") @Valid @RequestBody SysUserUpdateReqVO sysUserUpdateReqVO) {
        sysUserService.update(sysUserUpdateReqVO);
        return new R();
    }

    @ApiOperation(value = "修改当前登录用户")
    @PutMapping(value = "/current")
    @Token
    public R updateCurrent(
            @ApiIgnore UserTokenResVO userSesson,
            @ApiParam(value = "修改参数") @Valid @RequestBody SysUserUpdateSessionReqVO sysUserUpdateSessionReqVO) {
        sysUserUpdateSessionReqVO.setId(userSesson.getId());
        SysUserUpdateReqVO vo = new SysUserUpdateReqVO();
        BeanUtils.copyProperties(sysUserUpdateSessionReqVO, vo);
        sysUserService.update(vo);
        return new R();
    }

    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "")
    @Token
    public R delete(
            @ApiParam(value = "用户id") @RequestParam(value = "id") String id
    ) {
        sysUserService.deleteById(id);
        return new R();
    }
}
