package org.spring.springboot.app.controller;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.RedisUtils;
import org.spring.springboot.app.base.Type;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.po.IndexArticlePO;
import org.spring.springboot.app.domain.vo.IndexArticleInsertReqVO;
import org.spring.springboot.app.domain.vo.IndexArticleResVO;
import org.spring.springboot.app.domain.vo.IndexArticleSearchReqVO;
import org.spring.springboot.app.domain.vo.IndexArticleUpdateReqVO;
import org.spring.springboot.app.service.IndexArticleService;
import org.spring.springboot.exception.BusinessException;
import org.spring.springboot.util.Uuid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Api(tags = ApiIndex.ARTICLE)
@RequestMapping(value = "/api/article")
@RestController
public class IndexArticleController {

    @Autowired
    private IndexArticleService service;


    @Autowired
    private RedisUtils redisUtils;

    @ApiOperation(value = "分页查询文章")
    @GetMapping(value = "/list")
    public R<PageInfo<List<IndexArticleResVO>>> selectPage(
            @ApiParam(value = "查询参数") @ModelAttribute IndexArticleSearchReqVO vo) {
        List<IndexArticleResVO> list = service.selectPage(vo);
        PageInfo pageInfo = new PageInfo(list);
        return new R(pageInfo);
    }

    @ApiOperation(value = "查询单个文章信息")
    @GetMapping(value = "/{id}")
    public R<IndexArticleResVO> selectById(
            @ApiParam(value = "文章id") @PathVariable("id") String id) {
        IndexArticleResVO vo = service.selectById(id);
        return new R(vo);
    }

    @ApiOperation(value = "创建文章")
    @PostMapping(value = "")
    @Token
    public R insert(
            @ApiParam(value = "参数") @Valid @RequestBody IndexArticleInsertReqVO vo) {
        return new R(service.insert(vo));
    }

    @ApiOperation(value = "创建预览数据")
    @PostMapping(value = "/preview")
    @Token
    public R insertPreview(
            @ApiParam(value = "参数") @Valid @RequestBody IndexArticleInsertReqVO vo) {
        IndexArticleResVO resVo =new IndexArticleResVO();
        BeanUtils.copyProperties(vo, resVo);
        String key = Uuid.getUUID();
        redisUtils.set(key, resVo, 30, TimeUnit.SECONDS);
        return new R(key);
    }

    @ApiOperation(value = "查询预览数据")
    @GetMapping(value = "/preview/{key}")
    public R<IndexArticleResVO> selectPreview(
            @ApiParam(value = "数据唯一标识") @PathVariable("key") String key) {
        IndexArticleResVO vo = redisUtils.get(key);
        if(vo == null) {
            throw new BusinessException(Type.CACHE_VALIDATE_FAIL);
        }
        return new R(vo);
    }

    @ApiOperation(value = "修改文章")
    @PutMapping(value = "")
    @Token
    public R update(
            @ApiParam(value = "参数") @Valid @RequestBody IndexArticleUpdateReqVO vo) {
        service.update(vo);
        return new R();
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping(value = "")
    @Token
    public R delete(
            @ApiParam(value = "文章id") @RequestParam(value = "id") String id
    ) {
        service.deleteById(id);
        return new R();
    }
}
