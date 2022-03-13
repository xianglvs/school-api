package org.spring.springboot.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.IndexManageResVO;
import org.spring.springboot.app.domain.vo.IndexManageUpdateReqVO;
import org.spring.springboot.app.service.IndexManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = ApiIndex.INDEX)
@RequestMapping(value = "/api/home")
@RestController
public class IndexManageController {

    @Autowired
    private IndexManageService service;



    @ApiOperation(value = "查询首页信息")
    @GetMapping(value = "")
    public R<IndexManageResVO> selectHome() {
        List<IndexManageResVO> vo = service.selectHome();
        return new R(vo.get(0));
    }

    @ApiOperation(value = "修改首页信息")
    @PutMapping(value = "")
    @Token
    public R update(
            @ApiParam(value = "参数") @Valid @RequestBody IndexManageUpdateReqVO vo) {
        service.update(vo);
        return new R();
    }

}
