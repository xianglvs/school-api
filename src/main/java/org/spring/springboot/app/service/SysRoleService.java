package org.spring.springboot.app.service;

import org.spring.springboot.app.base.Error;
import org.spring.springboot.app.base.ErrorTools;
import org.spring.springboot.app.base.Type;
import org.spring.springboot.app.dao.SysRoleMapper;
import org.spring.springboot.app.domain.po.SysRolePO;
import org.spring.springboot.app.domain.vo.SysRoleInsertReqVO;
import org.spring.springboot.app.domain.vo.SysRoleResVO;
import org.spring.springboot.app.domain.vo.SysRoleUpdateReqVO;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;


    public SysRoleResVO selectById(String roleId) {
        SysRolePO sysRolePO = sysRoleMapper.selectByPrimaryKey(roleId);
        SysRoleResVO sysRoleResVO = new SysRoleResVO();
        BeanUtils.copyProperties(sysRolePO, sysRoleResVO);
        return sysRoleResVO;
    }

    public List<SysRoleResVO> selectAll(Boolean delFlag, Boolean disableFlag) {
        List<SysRoleResVO> list = sysRoleMapper.selectAllRole(delFlag, disableFlag);
        return list;
    }

    public List<SysRoleResVO> selectByUserId(String userId, Boolean delFlag, Boolean disableFlag) {
        List<SysRoleResVO> list = sysRoleMapper.selectByUserId(userId, delFlag, disableFlag);
        return list;
    }


    public void insert(SysRoleInsertReqVO vo) {
        SysRolePO po = new SysRolePO();
        BeanUtils.copyProperties(vo, po);
        String parentId = po.getParentId();
        if (parentId.equals("0")) {
            po.setParentIds("0,");
        } else {
            SysRolePO sysRolePO = sysRoleMapper.selectByPrimaryKey(parentId);
            if (sysRolePO == null) {
                throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("parentId", "父级角色不存在")));
            }
            po.setParentIds(sysRolePO.getParentIds() + sysRolePO.getId() + ",");
        }
        po.preInsert();
        int i = sysRoleMapper.insert(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

    public void update(SysRoleUpdateReqVO vo) {
        SysRolePO sysRolePO = sysRoleMapper.selectByPrimaryKey(vo.getId());
        if (sysRolePO == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("id", "角色不存在")));
        }
        SysRolePO po = new SysRolePO();
        BeanUtils.copyProperties(vo, po);
        //是否修改了父级,如果修改了父级则需要更新索引字段
        if (vo.getParentId() == null || vo.getParentId().equalsIgnoreCase(sysRolePO.getParentId())) {
            return;
        }
        //查询父类
        SysRolePO parentPo = sysRoleMapper.selectByPrimaryKey(vo.getParentId());
        if (parentPo == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("parentId", "父菜单不存在")));
        }
        //查询所有孩子
        List<SysRolePO> children = sysRoleMapper.selectAllChildren(vo.getId(), Boolean.FALSE, Boolean.FALSE);
        //更新子类的索引字段
        children.forEach(item -> {
            String parentIds = item.getParentIds();
            item.setParentIds(parentIds.replace(sysRolePO.getParentIds(), parentPo.getParentIds() + parentPo.getId() + ","));
            int i = sysRoleMapper.updateByPrimaryKey(item);
            if (i == 0) {
                throw new BusinessException(Type.EXCEPTION_FAIL);
            }
        });
        po.setParentIds(parentPo.getParentIds() + parentPo.getId() + ",");
        //更新
        po.preUpdate();
        int i = sysRoleMapper.updateByPrimaryKeySelective(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

}
