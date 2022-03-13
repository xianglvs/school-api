package org.spring.springboot.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.vo.FileResVO;
import org.spring.springboot.app.domain.vo.UserTokenResVO;
import org.spring.springboot.util.FTPClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.IOException;

@Api(tags = ApiIndex.FILE)
@RequestMapping(value = "/api/file")
@RestController
@Token
public class FileController {

    @Value("${file.path}")
    private String filePath;

    @ApiOperation(value = "上传文件")
    @PostMapping(value = "/upload")
    public R<FileResVO> upload(
            @ApiParam(value = "文件") MultipartFile file,
            @ApiIgnore UserTokenResVO userTokenResVO) throws IOException {
        String originalName = file.getOriginalFilename();
        String fileName = FTPClientUtil.fileNameConvert(file.getOriginalFilename());
        String systemPath = new ApplicationHome(getClass()).getSource().getParentFile().toString();
        String targetPath = filePath
                + "/" + DateFormatUtils.format(userTokenResVO.getCreateDate(), "yyyy/MM/dd")
                + "/" + userTokenResVO.getId();
        FileUtils.writeByteArrayToFile(new File(systemPath + targetPath + "/" + fileName), file.getBytes());
        FileResVO fileResVO = new FileResVO();
        fileResVO.setName(originalName);
        fileResVO.setPath(targetPath + "/" + fileName);
        return new R(fileResVO);
    }

}
