package org.spring.springboot.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.FileResVO;
import org.spring.springboot.app.domain.vo.UserTokenResVO;
import org.spring.springboot.config.BusinessProperties;
import org.spring.springboot.util.FTPClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;

@Api(tags = ApiIndex.FILE)
@RequestMapping(value = "/api/file")
@RestController
@Token
public class FileController {

    @Autowired
    private BusinessProperties businessProperties;

    @ApiOperation(value = "上传文件")
    @GetMapping(value = "/upload")
    public R<FileResVO> upload(
            @ApiParam(value = "文件") MultipartFile file,
            @ApiIgnore UserTokenResVO userTokenResVO) throws IOException {
        String originalName = file.getOriginalFilename();
        String fileName = FTPClientUtil.fileNameConvert(file.getOriginalFilename());
        String path = businessProperties.getFtpPath()
                + "/" + DateFormatUtils.format(userTokenResVO.getCreateDate(), "yyyy/MM/dd")
                + "/" + userTokenResVO.getId();
        FTPClientUtil.uploadFile(
                businessProperties.getFtpHost(),
                businessProperties.getFtpPort(),
                businessProperties.getFtpUsername(),
                businessProperties.getFtpPassword(),
                path,
                fileName,
                file.getInputStream());
        FileResVO fileResVO = new FileResVO();
        fileResVO.setName(originalName);
        fileResVO.setPath(path + "/" + fileName);
        return new R(fileResVO);
    }

}
