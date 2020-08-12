package org.spring.springboot.app.service;

import org.spring.springboot.app.base.Error;
import org.spring.springboot.app.base.ErrorTools;
import org.spring.springboot.app.base.Type;
import org.spring.springboot.app.dao.IndexArticleMapper;
import org.spring.springboot.app.domain.po.IndexArticlePO;
import org.spring.springboot.app.domain.vo.IndexArticleInsertReqVO;
import org.spring.springboot.app.domain.vo.IndexArticleResVO;
import org.spring.springboot.app.domain.vo.IndexArticleSearchReqVO;
import org.spring.springboot.app.domain.vo.IndexArticleUpdateReqVO;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndexArticleService {

    @Autowired
    private IndexArticleMapper indexArticleMapper;

    public IndexArticleResVO selectById(String id) {
        IndexArticlePO po = indexArticleMapper.selectByPrimaryKey(id);
        IndexArticleResVO vo = new IndexArticleResVO();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    public List<IndexArticleResVO> selectPage(IndexArticleSearchReqVO vo) {
        List<IndexArticleResVO> list = indexArticleMapper.selectPage(vo);
        return list;
    }

    public String insert(IndexArticleInsertReqVO vo) {
        IndexArticlePO po = new IndexArticlePO();
        BeanUtils.copyProperties(vo, po);
        po.preInsert();
        int i = indexArticleMapper.insert(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
        if (vo.getIndexCategoryIds() == null || vo.getIndexCategoryIds().isEmpty()) {
            return po.getId();
        }
        vo.getIndexCategoryIds().forEach((id) -> {
            try {
                indexArticleMapper.insertIndexArticleCategory(po.getId(), id);
            } catch (DataIntegrityViolationException e) {
                throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("roles", "分类Id不存在,分类id为:" + id)));
            }
        });
        return po.getId();
    }


    public void update(IndexArticleUpdateReqVO vo) {
        IndexArticlePO po = new IndexArticlePO();
        BeanUtils.copyProperties(vo, po);
        po.preUpdate();
        int i = indexArticleMapper.updateByPrimaryKeySelective(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
        if (vo.getIndexCategoryIds() == null || vo.getIndexCategoryIds().isEmpty()) {
            return;
        }
        indexArticleMapper.deleteIndexArticleCategory(vo.getId());
        vo.getIndexCategoryIds().forEach((id) -> {
            try {
                indexArticleMapper.insertIndexArticleCategory(po.getId(), id);
            } catch (DataIntegrityViolationException e) {
                throw new BusinessException(Type.NOT_FOUND_ERROR, ErrorTools.ErrorAsArrayList(new Error("roles", "分类Id不存在,分类id为:" + id)));
            }
        });

    }

    public void deleteById(String id){
        indexArticleMapper.deleteById(id);
    }

}
