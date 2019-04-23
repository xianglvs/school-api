package org.spring.springboot.app.service;

import org.spring.springboot.app.base.Error;
import org.spring.springboot.app.base.ErrorTools;
import org.spring.springboot.app.base.Type;
import org.spring.springboot.app.dao.IndexCategoryMapper;
import org.spring.springboot.app.domain.po.IndexCategoryPO;
import org.spring.springboot.app.domain.vo.*;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndexCategoryService {

    @Autowired
    private IndexCategoryMapper indexCategoryMapper;

    public IndexCategoryResVO selectById(String id) {
        IndexCategoryPO po = indexCategoryMapper.selectByPrimaryKey(id);
        IndexCategoryResVO vo = new IndexCategoryResVO();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    public List<IndexCategoryResVO> selectListByParent(String parentId) {
        List<IndexCategoryResVO> list = indexCategoryMapper.selectListByParent(parentId);
        return list;
    }

    public void insert(IndexCategoryInsertReqVO vo) {
        IndexCategoryPO po = new IndexCategoryPO();
        BeanUtils.copyProperties(vo, po);
        String parentId = po.getParentId();
        if (parentId.equals("0")) {
            po.setParentIds("0,");
        } else {
            IndexCategoryPO parentPO = indexCategoryMapper.selectByPrimaryKey(parentId);
            if (parentPO == null) {
                throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("parentId", "父级不存在")));
            }
            po.setParentIds(parentPO.getParentIds() + parentPO.getId() + ",");
        }
        po.preInsert();
        int i = indexCategoryMapper.insert(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

    private void update(IndexCategoryPO po) {
        po.preUpdate();
        int i = indexCategoryMapper.updateByPrimaryKeySelective(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

    public void update(IndexCategoryUpdateReqVO vo) {
        IndexCategoryPO oldPO = indexCategoryMapper.selectByPrimaryKey(vo.getId());
        if (oldPO == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("id", "id不存在")));
        }
        IndexCategoryPO po = new IndexCategoryPO();
        BeanUtils.copyProperties(vo, po);
        //是否修改了父级,如果修改了父级则需要更新索引字段
        if (vo.getParentId() == null || vo.getParentId().equalsIgnoreCase(oldPO.getParentId())) {
            update(po);
            return;
        }
        //查询父类
        IndexCategoryPO parentPo = indexCategoryMapper.selectByPrimaryKey(vo.getParentId());
        if (parentPo == null) {
            throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("parentId", "父级不存在")));
        }
        //查询所有子类
        List<IndexCategoryPO> children = indexCategoryMapper.selectAllChildren(vo.getId(), Boolean.FALSE, null);
        //更新子类的索引字段
        children.forEach(item -> {
            String parentIds = item.getParentIds();
            item.setParentIds(parentIds.replace(oldPO.getParentIds(), parentPo.getParentIds() + parentPo.getId() + ","));
            int i = indexCategoryMapper.updateByPrimaryKey(item);
            if (i == 0) {
                throw new BusinessException(Type.EXCEPTION_FAIL);
            }
        });
        po.setParentIds(parentPo.getParentIds() + parentPo.getId() + ",");
        //更新记录
        update(po);
    }

    public void deleteById(String id){
        indexCategoryMapper.deleteById(id);
    }

}
