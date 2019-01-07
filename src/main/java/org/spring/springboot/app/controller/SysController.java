package org.spring.springboot.app.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.spring.springboot.app.base.Result;
import org.spring.springboot.app.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 请求记录
 *
 * @author ry
 * @date 2018/9/25
 */
@Api(description = "请求记录")
@RestController
public class SysController {
    @Autowired
    private SysService sysService;

    @PostMapping("/getJSON")
    @ApiOperation(value = "发送请求")
    public Result getJSON(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return sysService.getResult();
    }
}
