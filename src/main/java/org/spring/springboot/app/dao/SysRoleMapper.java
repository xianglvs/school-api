package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.po.SysRolePO;
import org.spring.springboot.app.domain.vo.SysRoleResVO;
import org.spring.springboot.app.domain.vo.SysRoleSearchReqVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysRoleMapper extends Mapper<SysRolePO> {

    List<SysRoleResVO> selectAllRole(@Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    List<SysRoleResVO> selectPageRole(SysRoleSearchReqVO vo);

    List<SysRoleResVO> selectRoleByUserId(@Param("userId") String userId, @Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    List<SysRolePO> selectAllChildren(@Param("id") String id, @Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    int deleteRoleMenus(@Param("roleId") String roleId);

    int insertRoleMenu(@Param("roleId") String roleId, @Param("menuId") String menuId);
}