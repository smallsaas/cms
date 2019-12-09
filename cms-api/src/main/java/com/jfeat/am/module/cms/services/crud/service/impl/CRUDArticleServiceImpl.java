package com.jfeat.am.module.cms.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;


import com.jfeat.am.module.cms.services.crud.service.CRUDArticleService;
import com.jfeat.am.module.cms.services.persistence.dao.ArticleImageMapper;
import com.jfeat.am.module.cms.services.persistence.dao.ArticleMapper;
import com.jfeat.am.module.cms.services.persistence.model.Article;
import com.jfeat.am.module.cms.services.persistence.model.ArticleImage;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.jfeat.am.module.cms.services.domain.model.ArticleModel;

/**
 * <p>
 * implementation
 * </p>
 * CRUDArticleService
 *
 * @author Code Generator
 * @since 2018-07-11
 */

@Service
public class CRUDArticleServiceImpl extends CRUDServiceOverModelImpl<Article, ArticleModel> implements CRUDArticleService {


    @Resource
    private ArticleMapper articleMapper;


    @Override
    protected BaseMapper<Article> getMasterMapper() {
        return articleMapper;
    }

    @Override
    protected Class<Article> masterClassName() {
        return Article.class;
    }

    @Override
    protected Class<ArticleModel> modelClassName() {
        return ArticleModel.class;
    }


    @Resource
    private ArticleImageMapper imageMapper;

    @Deprecated
    private final static String imageFieldName = "article_id";

    private final static String imageKeyName = "images";


    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                imageKeyName

        };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {
        if (field.compareTo(imageKeyName) == 0) {
            FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(imageFieldName);
            _field.setItemClassName(ArticleImage.class);
            _field.setItemMapper(imageMapper);
            return _field;
        }
        throw new BusinessException(BusinessCode.BadRequest);
    }


    @Override
    protected String[] childFieldNames() {
        return new String[]{
        };
    }

    @Override
    protected FIELD onChildFieldItem(String field) {

        throw new BusinessException(BusinessCode.BadRequest);
    }


}


