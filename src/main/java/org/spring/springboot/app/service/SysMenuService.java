package org.spring.springboot.app.service;

import org.spring.springboot.app.base.*;
import org.spring.springboot.app.base.Error;
import org.spring.springboot.app.dao.SysMenuMapper;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private RedisUtils redisUtils;

    public List<Menu> selectAll(Boolean delFlag, Boolean disableFlag) {
        List<Menu> list = sysMenuMapper.selectAllMenu(delFlag, disableFlag);
        return list;
    }

    public List<Menu> selectByToken(String token) {
        User user = redisUtils.get(token);
        if (user == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("token", "token不存在")));
        }
        List<Menu> list = sysMenuMapper.selectMenuByUserId(user.getId());
        return list;
    }

    public List<Menu> selectByCache(String token) {
        User user = redisUtils.get(token);
        if (user == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("token", "token不存在")));
        }
        return user.getMenus();
    }


}
