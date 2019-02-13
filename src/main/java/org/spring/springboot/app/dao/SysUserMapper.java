package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.po.SysUserPO;
import org.spring.springboot.app.domain.vo.SysUserResVO;
import org.spring.springboot.app.domain.vo.UserSearchVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysUserMapper extends Mapper<SysUserPO> {
    List<SysUserResVO> selectBySearch(UserSearchVo userSearchVo);

    int insertUserRole(@Param("userId") String userId, @Param("roleId") String roleId);

    int deleteUserRole(@Param("userId") String userId);
}