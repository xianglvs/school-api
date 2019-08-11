package org.spring.springboot.app.service;

import org.apache.commons.lang3.StringUtils;
import org.spring.springboot.app.base.Error;
import org.spring.springboot.app.base.*;
import org.spring.springboot.app.dao.*;
import org.spring.springboot.app.domain.po.SysAreaPO;
import org.spring.springboot.app.domain.po.SysOfficePO;
import org.spring.springboot.app.domain.po.SysUserPO;
import org.spring.springboot.app.domain.vo.*;
import org.spring.springboot.exception.BusinessException;
import org.spring.springboot.util.Uuid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private SysAreaMapper sysAreaMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysOfficeMapper sysOfficeMapper;
    @Autowired
    private RedisUtils redisUtils;


    public List<SysUserResVO> selectBySearch(UserSearchVO vo) {
        List<SysUserResVO> list = sysUserMapper.selectBySearch(vo);
        return list;
    }

    public SysUserResVO selectById(String userId) {
        SysUserPO po = sysUserMapper.selectByPrimaryKey(userId);
        SysUserResVO vo = new SysUserResVO();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }


    public UserLoginResVO login(UserLoginReqVO userLoginReqVO) {
        Example example = new Example(SysUserPO.class);
        SysUserPO sysUserQuery = new SysUserPO();
        BeanUtils.copyProperties(userLoginReqVO, sysUserQuery);
        sysUserQuery.setLoginIp(null);
        example.and().andEqualTo(sysUserQuery);
        SysUserPO sysUserPO = sysUserMapper.selectOneByExample(example);
        if (sysUserPO == null) {
            throw new BusinessException(Type.PARAM_VALIDATE_FAIL, ErrorTools.ErrorAsArrayList(new Error("loginName", "用户不存在")));
        }
        if (sysUserPO.isDelFlag()) {
            throw new BusinessException(Type.PARAM_VALIDATE_FAIL, ErrorTools.ErrorAsArrayList(new Error("loginName", "用户被删除")));
        }
        if (sysUserPO.isDisableFlag()) {
            throw new BusinessException(Type.PARAM_VALIDATE_FAIL, ErrorTools.ErrorAsArrayList(new Error("loginName", "用户已被禁用")));
        }
        if (!userLoginReqVO.getPassword().equalsIgnoreCase(sysUserPO.getPassword())) {
            throw new BusinessException(Type.PARAM_VALIDATE_FAIL, ErrorTools.ErrorAsArrayList(new Error("password", "密码错误")));
        }
        //修改登录时间和ip
        sysUserPO.preUpdate();
        sysUserPO.setLoginIp(userLoginReqVO.getLoginIp());
        sysUserMapper.updateByPrimaryKeySelective(sysUserPO);
        UserLoginResVO vo = new UserLoginResVO();
        vo.setTicket(Uuid.getUUID());
        long effective_millisecond = 24 * 60 * 60 * 1000;//1天
        long expire = System.currentTimeMillis() + effective_millisecond;
        vo.setExpire(expire);
        vo.setExpireTime(new Date(expire));
        vo.setEffectiveMillisecond(effective_millisecond);
        redisUtils.set(vo.getTicket(), sysUserPO.getId(), effective_millisecond, TimeUnit.MILLISECONDS);
        return vo;
    }

    public UserTokenResVO token(String ticket) {
        if (!redisUtils.hasKey(ticket)) {
            throw new BusinessException(Type.PARAM_VALIDATE_FAIL, ErrorTools.ErrorAsArrayList(new Error("ticket", "凭证不正确,请重新申请")));
        }
        String userId = redisUtils.get(ticket);
        SysUserPO sysUserPO = sysUserMapper.selectByPrimaryKey(userId);
        if (sysUserPO == null) {
            throw new BusinessException(Type.PARAM_VALIDATE_FAIL, ErrorTools.ErrorAsArrayList(new Error("token", "用户不存在")));
        }
        if (sysUserPO.isDelFlag()) {
            throw new BusinessException(Type.PARAM_VALIDATE_FAIL, ErrorTools.ErrorAsArrayList(new Error("token", "用户被删除")));
        }
        if (sysUserPO.isDisableFlag()) {
            throw new BusinessException(Type.PARAM_VALIDATE_FAIL, ErrorTools.ErrorAsArrayList(new Error("token", "用户已被禁用")));
        }
        String token = Uuid.getUUID();
        UserSesson userSessonSession = new UserSesson();
        BeanUtils.copyProperties(sysUserPO, userSessonSession);
        UserTokenResVO tokenResVO = new UserTokenResVO();
        BeanUtils.copyProperties(sysUserPO,tokenResVO);
        tokenResVO.setToken(token);
        if(StringUtils.isNotBlank(sysUserPO.getSysAreaId())){
           SysAreaPO areaPO =  sysAreaMapper.selectByPrimaryKey(sysUserPO.getSysAreaId());
           tokenResVO.setSysAreaName(areaPO.getName());
           userSessonSession.setSysAreaName(areaPO.getName());
        }
        if(StringUtils.isNotBlank(sysUserPO.getSysOfficeId())){
            SysOfficePO sysOfficePO = sysOfficeMapper.selectByPrimaryKey(sysUserPO.getSysOfficeId());
            tokenResVO.setSysOfficeName(sysOfficePO.getName());
        }
        List<SysRoleResVO> roles =sysRoleMapper.selectRoleByUserId(sysUserPO.getId(),Boolean.FALSE,Boolean.FALSE);
        userSessonSession.setRoles(roles);
        long effective_millisecond = 60 * 60 * 1000;//60分钟
        long expire = System.currentTimeMillis() + effective_millisecond;
        tokenResVO.setExpireTime(new Date(expire));
        tokenResVO.setEffectiveMillisecond(effective_millisecond);
        String old = (String) ThreadLocalUtil.get(Constants.TOKEN_PARAM_NAME);
        if (old != null && redisUtils.hasKey(old)) {
            redisUtils.delete(old);
        }
        ThreadLocalUtil.put(Constants.TOKEN_PARAM_NAME, token);
        ThreadLocalUtil.put(Constants.TOKEN_SESSION_NAME, userSessonSession);
        redisUtils.set(token, userSessonSession, effective_millisecond, TimeUnit.MILLISECONDS);
        return tokenResVO;
    }

    public void create(SysUserCreateReqVO vo) {
        SysUserPO po = new SysUserPO();
        BeanUtils.copyProperties(vo, po);
        Example example = new Example(SysUserPO.class);
        example.and().andEqualTo("loginName", po.getLoginName());
        List<SysUserPO> list = sysUserMapper.selectByExample(example);
        if (!list.isEmpty()) {
            throw new BusinessException(Type.EXIST_ERROR, ErrorTools.ErrorAsArrayList(new Error("loginName", "用户名已存在")));
        }
        po.preInsert();
        try {
            int i = sysUserMapper.insert(po);
            if (i == 0) {
                new BusinessException(Type.EXCEPTION_FAIL);
            }
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(
                    new Error("sysOfficeId", "机构或者区域不存在"),
                    new Error("sysAreaId", "机构或者区域不存在")
            ));
        }
    }

    public void update(SysUserUpdateReqVO vo) {
        SysUserPO po = new SysUserPO();
        BeanUtils.copyProperties(vo, po);
        SysUserPO sysUserPO = sysUserMapper.selectByPrimaryKey(po.getId());
        if (sysUserPO == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("id", "用户不存在")));
        }
        po.preUpdate();
        try {
            int i = sysUserMapper.updateByPrimaryKeySelective(po);
            if (i == 0) {
                throw new BusinessException(Type.EXCEPTION_FAIL);
            }
        } catch (DataIntegrityViolationException e) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(
                    new Error("sysOfficeId", "机构或者区域不存在"),
                    new Error("sysAreaId", "机构或者区域不存在")
            ));
        }
        if (vo.getRoles() == null || vo.getRoles().isEmpty()) {
            return;
        }
        sysUserMapper.deleteUserRole(po.getId());
        vo.getRoles().forEach(roleId -> {
            try {
                sysUserMapper.insertUserRole(po.getId(), roleId);
            } catch (DataIntegrityViolationException e) {
                throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("roles", "角色不存在,角色ID为:" + roleId)));
            }
        });
    }

    public void deleteById(String id) {
        sysUserMapper.deleteById(id);
    }


}
