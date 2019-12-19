package com.jfeat.module.fs.service.impl;
import com.jfeat.module.fs.dao.ImgDao;
import com.jfeat.module.fs.model.ImgServiceConfig;
import com.jfeat.module.fs.model.InitServiceConfig;
import com.jfeat.module.fs.service.ImgService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ImgServiceImpl implements ImgService {

    @Resource
    ImgDao imgDao;

    @Override
    public InitServiceConfig initImgService() {
        InitServiceConfig initServiceConfig = new InitServiceConfig();

        List<ImgServiceConfig> imgServiceConfigs = imgDao.initImgService();
        for (ImgServiceConfig imgServiceConfig : imgServiceConfigs) {
            if(imgServiceConfig.getField()!=null&&imgServiceConfig.getField().equals("file_upload_path")){
                   initServiceConfig.setFileUploadPath(imgServiceConfig.getValue());
            }
            if(imgServiceConfig.getField()!=null&&imgServiceConfig.getField().equals("file_host")){
                   initServiceConfig.setFileHost(imgServiceConfig.getValue());
            }
        }

        return initServiceConfig;
    }
}
