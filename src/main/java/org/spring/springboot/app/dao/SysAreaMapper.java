package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.po.SysAreaPO;
import org.spring.springboot.app.domain.vo.SysAreaResVO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysAreaMapper extends Mapper<SysAreaPO> {

    List<SysAreaResVO> selectListByParent(@Param("parentId") String parentId);

    List<SysAreaPO> selectAllChildren(@Param("id") String id, @Param("delFlag") Boolean delFlag, @Param("disableFlag") String disableFlag);

    void deleteById(@Param("id") String id);
}