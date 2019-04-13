package org.spring.springboot.app.service;

import org.spring.springboot.app.base.Error;
import org.spring.springboot.app.base.ErrorTools;
import org.spring.springboot.app.base.Type;
import org.spring.springboot.app.dao.SysAreaMapper;
import org.spring.springboot.app.domain.po.SysAreaPO;
import org.spring.springboot.app.domain.vo.SysAreaInsertReqVO;
import org.spring.springboot.app.domain.vo.SysAreaResVO;
import org.spring.springboot.app.domain.vo.SysAreaUpdateReqVO;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysAreaService {

    @Autowired
    private SysAreaMapper sysAreaMapper;

    public SysAreaResVO selectById(String id) {
        SysAreaPO po = sysAreaMapper.selectByPrimaryKey(id);
        SysAreaResVO vo = new SysAreaResVO();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    public List<SysAreaResVO> selectListByParent(String parentId) {
        List<SysAreaResVO> list = sysAreaMapper.selectListByParent(parentId);
        return list;
    }

    public void insert(SysAreaInsertReqVO vo) {
        SysAreaPO po = new SysAreaPO();
        BeanUtils.copyProperties(vo, po);
        String parentId = po.getParentId();
        if (parentId.equals("0")) {
            po.setType(1);
            po.setParentIds("0,");
        } else {
            SysAreaPO sysAreaPO = sysAreaMapper.selectByPrimaryKey(parentId);
            if (sysAreaPO == null) {
                throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("parentId", "父级不存在")));
            }
            po.setType(sysAreaPO.getType() + 1);
            po.setParentIds(sysAreaPO.getParentIds() + sysAreaPO.getId() + ",");
        }
        po.preInsert();
        int i = sysAreaMapper.insert(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

    private void updateArea(SysAreaPO po) {
        po.preUpdate();
        int i = sysAreaMapper.updateByPrimaryKeySelective(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

    public void update(SysAreaUpdateReqVO vo) {
        SysAreaPO sysAreaPO = sysAreaMapper.selectByPrimaryKey(vo.getId());
        if (sysAreaPO == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("id", "id不存在")));
        }
        SysAreaPO po = new SysAreaPO();
        BeanUtils.copyProperties(vo, po);
        //是否修改了父级,如果修改了父级则需要更新索引字段
        if (vo.getParentId() == null || vo.getParentId().equalsIgnoreCase(sysAreaPO.getParentId())) {
            updateArea(po);
            return;
        }
        //查询父类
        SysAreaPO parentPo = sysAreaMapper.selectByPrimaryKey(vo.getParentId());
        if (parentPo == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("parentId", "父级不存在")));
        }
        //查询所有子类
        List<SysAreaPO> children = sysAreaMapper.selectAllChildren(vo.getId(), Boolean.FALSE, null);
        //更新子类的索引字段
        children.forEach(item -> {
            String parentIds = item.getParentIds();
            item.setParentIds(parentIds.replace(sysAreaPO.getParentIds(), parentPo.getParentIds() + parentPo.getId() + ","));
            int i = sysAreaMapper.updateByPrimaryKey(item);
            if (i == 0) {
                throw new BusinessException(Type.EXCEPTION_FAIL);
            }
        });
        po.setParentIds(parentPo.getParentIds() + parentPo.getId() + ",");
        //更新记录
        po.preUpdate();
        int i = sysAreaMapper.updateByPrimaryKeySelective(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

}
