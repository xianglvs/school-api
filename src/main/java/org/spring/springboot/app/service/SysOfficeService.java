package org.spring.springboot.app.service;

import org.spring.springboot.app.base.Error;
import org.spring.springboot.app.base.ErrorTools;
import org.spring.springboot.app.base.Type;
import org.spring.springboot.app.dao.SysOfficeMapper;
import org.spring.springboot.app.domain.po.SysOfficePO;
import org.spring.springboot.app.domain.vo.SysOfficeInsertReqVO;
import org.spring.springboot.app.domain.vo.SysOfficeResVO;
import org.spring.springboot.app.domain.vo.SysOfficeSearchReqVO;
import org.spring.springboot.app.domain.vo.SysOfficeUpdateReqVO;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysOfficeService {

    @Autowired
    private SysOfficeMapper sysOfficeMapper;


    public SysOfficeResVO selectById(String officeId) {
        SysOfficePO sysOfficePO = sysOfficeMapper.selectByPrimaryKey(officeId);
        SysOfficeResVO vo = new SysOfficeResVO();
        BeanUtils.copyProperties(sysOfficePO, vo);
        return vo;
    }

    public List<SysOfficeResVO> selectByUserId(String userId, Boolean delFlag, Boolean disableFlag) {
        List<SysOfficeResVO> list = sysOfficeMapper.selectOfficeByUserId(userId, delFlag, disableFlag);
        return list;
    }

    public List<SysOfficeResVO> selectAll(Boolean delFlag, Boolean disableFlag) {
        List<SysOfficeResVO> list = sysOfficeMapper.selectAllOffice(delFlag, disableFlag);
        return list;
    }

    public List<SysOfficeResVO> selectPage(SysOfficeSearchReqVO vo) {
        List<SysOfficeResVO> list = sysOfficeMapper.selectPageOffice(vo);
        return list;
    }

    public void insert(SysOfficeInsertReqVO vo) {
        SysOfficePO po = new SysOfficePO();
        BeanUtils.copyProperties(vo, po);
        String parentId = po.getParentId();
        if (parentId.equals("0")) {
            po.setParentIds("0,");
        } else {
            SysOfficePO sysOfficePO = sysOfficeMapper.selectByPrimaryKey(parentId);
            if (sysOfficePO == null) {
                throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("parentId", "父级机构不存在")));
            }
            po.setParentIds(sysOfficePO.getParentIds() + sysOfficePO.getId() + ",");
        }
        po.preInsert();
        int i = sysOfficeMapper.insert(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

    private void updateOffice(SysOfficePO po) {
        po.preUpdate();
        int i = sysOfficeMapper.updateByPrimaryKeySelective(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

    public void update(SysOfficeUpdateReqVO vo) {
        SysOfficePO sysOfficePO = sysOfficeMapper.selectByPrimaryKey(vo.getId());
        if (sysOfficePO == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("id", "机构不存在")));
        }
        SysOfficePO po = new SysOfficePO();
        BeanUtils.copyProperties(vo, po);
        //是否修改了父级,如果修改了父级则需要更新索引字段
        if (vo.getParentId() == null || vo.getParentId().equalsIgnoreCase(sysOfficePO.getParentId())) {
            updateOffice(po);
            return;
        }
        //查询父类
        SysOfficePO parentPo = sysOfficeMapper.selectByPrimaryKey(vo.getParentId());
        if (parentPo == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("parentId", "父机构不存在")));
        }
        //查询所有子类
        List<SysOfficePO> children = sysOfficeMapper.selectAllChildren(vo.getId(), Boolean.FALSE, null);
        //更新子类的索引字段
        children.forEach(item -> {
            String parentIds = item.getParentIds();
            item.setParentIds(parentIds.replace(sysOfficePO.getParentIds(), parentPo.getParentIds() + parentPo.getId() + ","));
            int i = sysOfficeMapper.updateByPrimaryKey(item);
            if (i == 0) {
                throw new BusinessException(Type.EXCEPTION_FAIL);
            }
        });
        po.setParentIds(parentPo.getParentIds() + parentPo.getId() + ",");
        //更新记录
        po.preUpdate();
        int i = sysOfficeMapper.updateByPrimaryKeySelective(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

    public void deleteById(String id){
        sysOfficeMapper.deleteById(id);
    }

}
