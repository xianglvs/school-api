package org.spring.springboot.config;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.spring.springboot.app.base.R;
import org.spring.springboot.util.FastExcel;
import org.spring.springboot.util.FastGenerics;
import org.spring.springboot.util.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;

/**
 * Created by 大猫猫
 * Date: 20/7/20
 * Version 1.0.0
 */
@Slf4j
public class BaseController<Biz extends BaseBiz,Entity> {
    @Autowired
    protected HttpServletRequest request;
    @Autowired
    protected Biz baseBiz;

    public Biz getBaseBiz() {
        return baseBiz;
    }

    public BaseController(){
    }

    //换行符
    private static String lineSeparator = System.lineSeparator();

    @RequestMapping(value = "",method = RequestMethod.POST)
    @ResponseBody
    public R add(@RequestBody @Validated Entity Entity){
        baseBiz.insertSelective(Entity);
        return new R();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public R<Entity> get(@PathVariable Object id){
        R<Entity> r = new R<>();
        Object o = baseBiz.selectById(id);
        r.setData((Entity)o);
        return r;
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    @ResponseBody
    public R<Entity> update(@RequestBody @Validated Entity Entity){
        baseBiz.updateSelectiveById(Entity);
        return new R<Entity>();
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public R<Entity> remove(@PathVariable Object id){
        baseBiz.deleteById(id);
        return new R<Entity>();
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    @ResponseBody
    public R<PageInfo<Entity>> all(@RequestParam(required = false) LinkedHashMap<String, Object> params){
        //查询列表数据
        int limit = MapUtils.getInteger(params,"limit",1000);
        Query query = new Query(params);
        query.setLimit(limit == 0 ? 1000 : limit);
        return baseBiz.selectByQuery(query);
    }

    @RequestMapping(value = "/page",method = RequestMethod.GET)
    @ResponseBody
    public R<PageInfo<Entity>> page(@RequestParam(required = false) LinkedHashMap<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        return baseBiz.selectByQueryPage(query);
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    @ResponseBody
    public R count(@RequestParam(required = false) LinkedHashMap<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        return baseBiz.selectCountByQuery(query);
    }

    @RequestMapping(value = "/only_page",method = RequestMethod.GET)
    @ResponseBody
    public R<PageInfo<Entity>> onlyPage(@RequestParam(required = false) LinkedHashMap<String, Object> params){
        //查询列表数据
        Query query = new Query(params);
        return baseBiz.selectByQueryPage(query,false);
    }

    @RequestMapping(value = "/excel",method = RequestMethod.GET)
    public void exportExcel(HttpServletResponse response, @RequestParam(required = false) LinkedHashMap<String, Object> params, String excelName){
        //查询列表数据
        Query query = new Query(params);
        query.setLimit(Integer.MAX_VALUE);
        R<PageInfo> r = this.baseBiz.selectByQueryPage(query);
        FastExcel<Entity> fastExcel = new FastExcel<>(excelName,excelName);
        fastExcel.ListHashMapToExcelStream(response, r.getData().getList(), FastGenerics.getActualTypeArgument(this.getClass(),1));
    }

}
