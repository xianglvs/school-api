package org.spring.springboot.app.service;

import org.apache.commons.lang3.StringUtils;
import org.spring.springboot.app.base.Type;
import org.spring.springboot.app.dao.IndexImagesMapper;
import org.spring.springboot.app.domain.po.IndexImagesPO;
import org.spring.springboot.app.domain.vo.FileArticleReqVO;
import org.spring.springboot.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class FileService {

    @Autowired
    private IndexImagesMapper mapper;

    public String insert(IndexImagesPO po) {
        po.preInsert();
        int i = mapper.insert(po);
        if (i == 0) {
            throw new BusinessException(Type.EXCEPTION_FAIL);
        }
        return po.getId();
    }

    public void updateImageArticleIdByPaths(FileArticleReqVO vo) {
        if (StringUtils.isNotEmpty(vo.getArticleId())) {
            mapper.clearArticleId(vo.getArticleId());
            if (vo.getImagePaths() != null && !vo.getImagePaths().isEmpty()) {
                mapper.updateImageArticleIdByPaths(vo.getArticleId(), vo.getImagePaths());
            }
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void autoClearImages() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getDefault());
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        Date end = new Date(cal.getTimeInMillis());
        cal.add(Calendar.DATE, -6);
        Date start = new Date(cal.getTimeInMillis());
        Example example = new Example(IndexImagesPO.class);
        example.and()
                .andBetween("createDate", start, end)
                .andIsNull("articleId");
        List<IndexImagesPO> list = mapper.selectByExample(example);
        list.forEach(po -> {
            if (StringUtils.isNotEmpty(po.getImagePath())) {
                List<String> paths = Arrays.asList(po.getImagePath().split("/"));
                paths = new ArrayList<>(paths);
                String fileName = paths.remove(paths.size() - 1);
                String path = paths.stream().collect(Collectors.joining("/"));
                File[] listFile = new File(path).listFiles();
                if(listFile != null){
                    Arrays.stream(listFile).forEach(f -> {
                        if (f.isFile()) {
                            if (f.getName().contains(fileName)) {
                                f.delete();
                            }
                        }
                    });
                }
                mapper.deleteByPrimaryKey(po.getId());
            }
        });
    }
}
