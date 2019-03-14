package org.spring.springboot.app.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.app.domain.po.SysOfficePO;
import org.spring.springboot.app.domain.vo.SysOfficeResVO;
import org.spring.springboot.app.domain.vo.SysOfficeSearchReqVO;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SysOfficeMapper extends Mapper<SysOfficePO> {
    List<SysOfficeResVO> selectAllOffice(@Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    List<SysOfficeResVO> selectPageOffice(SysOfficeSearchReqVO vo);

    List<SysOfficeResVO> selectOfficeByUserId(@Param("userId") String userId, @Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

    List<SysOfficeResVO> selectAllChildren(@Param("id") String id, @Param("delFlag") Boolean delFlag, @Param("disableFlag") Boolean disableFlag);

}