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
        if (po.getSort() == null || po.getSort() < 0) {
            po.setSort(0);
        }
        if (po.getSort() > 0) {
            int max = indexArticleMapper.selectMaxSort();
            if (po.getSort() > max) {
                po.setSort(max + 1);
            }
            if (po.getSort() < max + 1) {
                indexArticleMapper.updateAdd(po.getSort());
            }
        }
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

    private void updateSort(IndexArticlePO po) {
        if (po.getSort() < 0) {
            po.setSort(0);
        }
        IndexArticlePO source = indexArticleMapper.selectByPrimaryKey(po.getId());
        int max = indexArticleMapper.selectMaxSortExcludeSelf(po.getId());
        /*如果超过最大值*/
        if ((po.getDisableFlag() == null && !source.getDisableFlag()) ||
                (po.getDisableFlag() != null && !po.getDisableFlag())) {
            if (po.getSort() != null && po.getSort() > max + 1) {
                po.setSort(max + 1);
            }
        }
        /*如果启用*/
        if (po.getDisableFlag() != null && !po.getDisableFlag() && source.getDisableFlag()) {
            if (po.getSort() != null && po.getSort() > 0) {
                indexArticleMapper.updateAdd(po.getSort());
            }
            if (po.getSort() == null && source.getSort() != null && source.getSort() > 0) {
                int sort = source.getSort();
                if (sort > max + 1) {
                    sort = max + 1;
                }
                indexArticleMapper.updateAdd(sort);
            }
            return;
        }
        /*如果禁用*/
        if (po.getDisableFlag() != null && po.getDisableFlag() && !source.getDisableFlag()) {
            if (source.getSort() != null && source.getSort() > 0) {
                indexArticleMapper.updateSub(source.getSort());
            }
            return;
        }
        /*如果一直是禁用状态*/
        if (source.getDisableFlag() && (po.getDisableFlag() == null || po.getDisableFlag())) {
            return;
        }
        /*如果开启排序*/
        if (po.getSort() != null && source.getSort() == 0 && po.getSort() > 0) {
            indexArticleMapper.updateAdd(po.getSort());
            return;
        }
        /*如果取消排序*/
        if (po.getSort() != null && source.getSort() > 0 && po.getSort() == 0) {
            indexArticleMapper.updateSub(source.getSort());
            return;
        }
        /*如果排序号变小*/
        if (po.getSort() != null && po.getSort() < source.getSort()) {
            indexArticleMapper.updateBetweenAdd(po.getSort(), source.getSort());
            return;
        }
        /*如果排序号变大*/
        if (po.getSort() != null && po.getSort() > source.getSort()) {
            indexArticleMapper.updateBetweenSub(source.getSort(), po.getSort());
            return;
        }
    }


    public void update(IndexArticleUpdateReqVO vo) {
        IndexArticlePO po = new IndexArticlePO();
        BeanUtils.copyProperties(vo, po);
        this.updateSort(po);
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

    public void deleteById(String id) {
        IndexArticlePO po = indexArticleMapper.selectByPrimaryKey(id);
        if (po.getSort() != null && po.getSort() != 0) {
            indexArticleMapper.updateSub(po.getSort());
        }
        po.setDelFlag(true);
        indexArticleMapper.updateByPrimaryKeySelective(po);
    }

}
