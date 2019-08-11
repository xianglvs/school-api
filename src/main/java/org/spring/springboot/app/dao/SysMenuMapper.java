package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.vo.SysMenuResVO;
import org.spring.springboot.app.domain.po.SysMenuPO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysMenuMapper extends Mapper<SysMenuPO> {

    List<SysMenuResVO> selectMenuByUserId(@Param("sysUserId") String sysUserId, @Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    List<SysMenuResVO> selectMenuByRoleId(@Param("sysRoleId") String sysRoleId, @Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    List<SysMenuResVO> selectAllMenu(@Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    List<SysMenuPO> selectAllChildren(@Param("id") String id, @Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    int deleteById(@Param("id") String id);

    int deleteRoleMenuByMenuId(@Param("id") String id);
}