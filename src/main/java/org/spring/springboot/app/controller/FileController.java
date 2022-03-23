package org.spring.springboot.app.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.spring.springboot.app.base.ApiIndex;
import org.spring.springboot.app.base.R;
import org.spring.springboot.app.base.annotation.Token;
import org.spring.springboot.app.domain.po.IndexImagesPO;
import org.spring.springboot.app.domain.vo.FileArticleReqVO;
import org.spring.springboot.app.domain.vo.FileResVO;
import org.spring.springboot.app.domain.vo.UserTokenResVO;
import org.spring.springboot.app.service.FileService;
import org.spring.springboot.util.FTPClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@Api(tags = ApiIndex.FILE)
@RequestMapping(value = "/api/file")
@RestController
@Token
public class FileController {

    @Value("${file.path}")
    private String filePath;

    @Autowired
    private FileService fileService;

    @ApiOperation(value = "上传文件")
    @Token
    @PostMapping(value = "/upload")
    public R<FileResVO> upload(
            @ApiParam(value = "文件") MultipartFile file,
            @ApiIgnore UserTokenResVO userTokenResVO) throws IOException {
        String originalName = file.getOriginalFilename();
        String fileName = FTPClientUtil.fileNameConvert(file.getOriginalFilename());
        BufferedImage image = ImageIO.read(file.getInputStream());
        long size = file.getSize();
        int width = image.getWidth();
        int height = image.getHeight();
        String systemPath = new ApplicationHome(getClass()).getSource().getParentFile().toString();
        String targetPath = filePath
                + "/" + DateFormatUtils.format(new Date(), "yyyyMMdd")
                + "/" + userTokenResVO.getId();
        FileUtils.writeByteArrayToFile(new File(systemPath + targetPath + "/" + fileName), file.getBytes());
        IndexImagesPO po = new IndexImagesPO();
        po.setImageName(originalName);
        po.setImagePath(systemPath + targetPath + "/" + fileName);
        po.setImageAccessPath(targetPath + "/" + fileName);
        po.setImageSize(size);
        po.setImageWidth(width);
        po.setImageHeight(height);
        String id = fileService.insert(po);
        FileResVO fileResVO = new FileResVO();
        fileResVO.setId(id);
        fileResVO.setName(originalName);
        fileResVO.setPath(targetPath + "/" + fileName);
        return new R(fileResVO);
    }

    @ApiOperation(value = "设置文章使用的图片")
    @Token
    @PutMapping(value = "/set/paths")
    public R setIds(
            @ApiParam(value = "使用图片的ID列表") @RequestBody FileArticleReqVO vo) {
        fileService.updateImageArticleIdByPaths(vo);
        return new R();
    }

    @ApiOperation(value = "马上清理未使用的图片")
    @Token
    @PostMapping(value = "/clear")
    public R clear() {
        fileService.autoClearImages();
        return new R();
    }

}
