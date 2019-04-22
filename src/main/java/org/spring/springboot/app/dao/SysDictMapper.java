package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.po.SysDictPO;
import org.spring.springboot.app.domain.vo.SysDictResVO;
import org.spring.springboot.app.domain.vo.SysDictSearchVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysDictMapper extends Mapper<SysDictPO> {

    List<SysDictResVO> selectPage(SysDictSearchVO vo);

    int deleteById(@Param("id") String id);
}