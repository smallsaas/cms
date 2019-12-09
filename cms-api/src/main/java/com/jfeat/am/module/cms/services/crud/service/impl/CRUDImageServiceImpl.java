package com.jfeat.am.module.cms.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;


import com.jfeat.am.module.cms.services.crud.service.CRUDImageService;
import com.jfeat.am.module.cms.services.persistence.dao.ArticleImageMapper;
import com.jfeat.am.module.cms.services.persistence.model.ArticleImage;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDImageService
 * @author Code Generator
 * @since 2018-07-11
 */

@Service
public class CRUDImageServiceImpl  extends CRUDServiceSlaveImpl<ArticleImage> implements CRUDImageService {







    private static final String masterField = "article_id";

    @Resource
    private ArticleImageMapper imageMapper;

    @Override
    protected BaseMapper<ArticleImage> getSlaveItemMapper() {
        return imageMapper;
    }

    @Override
    protected String masterFieldName() {
        if(true){
           throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }





}


