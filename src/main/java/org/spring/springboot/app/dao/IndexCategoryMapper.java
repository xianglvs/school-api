package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.po.IndexCategoryPO;
import org.spring.springboot.app.domain.vo.IndexCategoryResVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface IndexCategoryMapper extends Mapper<IndexCategoryPO> {

    List<IndexCategoryResVO> selectListByParent(@Param("parentId") String parentId);

    List<IndexCategoryPO> selectAllChildren(@Param("id") String id, @Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    List<IndexCategoryResVO> queryAll(@Param("disableFlag") Boolean disableFlag, @Param("delFlag") Boolean delFlag);

    void deleteById(@Param("id") String id);
}