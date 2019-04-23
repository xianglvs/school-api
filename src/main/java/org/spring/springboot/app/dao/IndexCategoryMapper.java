package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.po.IndexCategoryPO;
import org.spring.springboot.app.domain.vo.IndexCategoryResVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IndexCategoryMapper extends Mapper<IndexCategoryPO> {

    List<IndexCategoryResVO> selectListByParent(@Param("parentId") String parentId);

    List<IndexCategoryPO> selectAllChildren(@Param("id") String id, @Param("delFlag") Boolean delFlag, @Param("disableFlag") String disableFlag);

    void deleteById(@Param("id") String id);
}