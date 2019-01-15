package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.base.Menu;
import org.spring.springboot.app.domain.po.SysMenuPO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysMenuMapper extends Mapper<SysMenuPO> {
    List<Menu> selectMenuByUserId(@Param("sysUserId") String sysUserId);
}