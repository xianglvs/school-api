package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.po.SysRolePO;
import org.spring.springboot.app.domain.vo.SysRoleResVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRolePO> {

    List<SysRoleResVO> selectByUserId(@Param("sysUserId") String sysUserId, @Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    List<SysRoleResVO> selectAllRole(@Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    List<SysRolePO> selectAllChildren(@Param("id") String id, @Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);
}