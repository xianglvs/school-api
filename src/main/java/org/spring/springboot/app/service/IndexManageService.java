package org.spring.springboot.app.service;

import org.spring.springboot.app.base.Type;
import org.spring.springboot.app.dao.IndexManageMapper;
import org.spring.springboot.app.domain.po.IndexManagePO;
import org.spring.springboot.app.domain.vo.IndexManageResVO;
import org.spring.springboot.app.domain.vo.IndexManageUpdateReqVO;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IndexManageService {

    @Autowired
    private IndexManageMapper indexManageMapper;


    public List<IndexManageResVO> selectHome(){
        List<IndexManageResVO> vo = indexManageMapper.selectHome();
        return vo;
    }


    public void update(IndexManageUpdateReqVO vo) {
        IndexManagePO po = new IndexManagePO();
        BeanUtils.copyProperties(vo, po);
        int i = indexManageMapper.updateByPrimaryKey(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

}
