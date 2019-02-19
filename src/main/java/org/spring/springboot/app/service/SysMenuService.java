package org.spring.springboot.app.service;

import org.spring.springboot.app.base.Error;
import org.spring.springboot.app.base.*;
import org.spring.springboot.app.dao.SysMenuMapper;
import org.spring.springboot.app.domain.po.SysMenuPO;
import org.spring.springboot.app.domain.vo.SysMenuCreateReqVO;
import org.spring.springboot.app.domain.vo.SysMenuUpdateReqVO;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.BeanUtils;
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

    public Menu selectById(String menuId) {
        SysMenuPO menuPO = sysMenuMapper.selectByPrimaryKey(menuId);
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuPO, menu);
        return menu;
    }

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

    public void insert(SysMenuCreateReqVO vo) {
        SysMenuPO po = new SysMenuPO();
        BeanUtils.copyProperties(vo, po);
        String parentId = po.getParentId();
        if (parentId.equals("0")) {
            po.setParentIds("0,");
        } else {
            SysMenuPO sysMenuPO = sysMenuMapper.selectByPrimaryKey(parentId);
            if (sysMenuPO == null) {
                throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("parentId", "父级菜单不存在")));
            }
            po.setParentIds(sysMenuPO.getParentIds() + sysMenuPO.getId() + ",");
        }
        po.preInsert();
        int i = sysMenuMapper.insert(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

    public void update(SysMenuUpdateReqVO vo) {
        SysMenuPO sysMenuPO = sysMenuMapper.selectByPrimaryKey(vo.getId());
        if (sysMenuPO == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("id", "菜单不存在")));
        }
        SysMenuPO po = new SysMenuPO();
        BeanUtils.copyProperties(vo, po);
        //是否修改了父级,如果修改了父级则需要更新索引字段
        if (vo.getParentId() == null || vo.getParentId().equalsIgnoreCase(sysMenuPO.getParentId())) {
            return;
        }
        //查询父类
        SysMenuPO parentPo = sysMenuMapper.selectByPrimaryKey(vo.getParentId());
        if (parentPo == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("parentId", "父菜单不存在")));
        }
        //查询所有子菜单
        List<SysMenuPO> children = sysMenuMapper.selectAllChildren(vo.getId(), Boolean.FALSE, vo.getHiddenFlag());
        //更新子类的索引字段
        children.forEach(item -> {
            String parentIds = item.getParentIds();
            item.setParentIds(parentIds.replace(sysMenuPO.getParentIds(), parentPo.getParentIds() + parentPo.getId() + ","));
            int i = sysMenuMapper.updateByPrimaryKey(item);
            if (i == 0) {
                throw new BusinessException(Type.EXCEPTION_FAIL);
            }
        });
        po.setParentIds(parentPo.getParentIds() + parentPo.getId() + ",");
        //更新菜单
        po.preUpdate();
        int i = sysMenuMapper.updateByPrimaryKeySelective(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

}
