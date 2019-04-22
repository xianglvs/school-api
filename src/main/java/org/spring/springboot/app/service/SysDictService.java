package org.spring.springboot.app.service;

import org.spring.springboot.app.base.Type;
import org.spring.springboot.app.dao.SysDictMapper;
import org.spring.springboot.app.domain.po.SysDictPO;
import org.spring.springboot.app.domain.vo.SysDictInsertReqVO;
import org.spring.springboot.app.domain.vo.SysDictResVO;
import org.spring.springboot.app.domain.vo.SysDictSearchVO;
import org.spring.springboot.app.domain.vo.SysDictUpdateReqVO;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    public SysDictResVO selectById(String id) {
        SysDictPO po = sysDictMapper.selectByPrimaryKey(id);
        SysDictResVO vo = new SysDictResVO();
        BeanUtils.copyProperties(po, vo);
        return vo;
    }

    public List<SysDictResVO> selectPage(SysDictSearchVO vo) {
        List<SysDictResVO> list = sysDictMapper.selectPage(vo);
        return list;
    }

    public void insert(SysDictInsertReqVO vo) {
        SysDictPO po = new SysDictPO();
        BeanUtils.copyProperties(vo, po);
        po.preInsert();
        int i = sysDictMapper.insert(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }


    public void update(SysDictUpdateReqVO vo) {
        SysDictPO po = new SysDictPO();
        BeanUtils.copyProperties(vo, po);
        po.preUpdate();
        int i = sysDictMapper.updateByPrimaryKeySelective(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
    }

    public void deleteById(String id){
        sysDictMapper.deleteById(id);
    }

}
