package org.spring.springboot.app.dao;

import org.spring.springboot.app.domain.po.IndexManagePO;
import org.spring.springboot.app.domain.vo.IndexManageResVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface IndexManageMapper extends Mapper<IndexManagePO> {
    List<IndexManageResVO> selectHome();
}