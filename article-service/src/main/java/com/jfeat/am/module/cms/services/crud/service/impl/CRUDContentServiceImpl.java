package com.jfeat.am.module.cms.services.crud.service.impl;
            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.cms.services.crud.service.CRUDContentService;
import com.jfeat.am.module.cms.services.persistence.dao.ArticleContentMapper;
import com.jfeat.am.module.cms.services.persistence.model.ArticleContent;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDContentService
 * @author Code Generator
 * @since 2018-07-11
 */

@Service
public class CRUDContentServiceImpl  extends CRUDServiceSlaveImpl<ArticleContent> implements CRUDContentService {







    private static final String masterField = "article_id";

    @Resource
    private ArticleContentMapper contentMapper;

    @Override
    protected BaseMapper<ArticleContent> getSlaveItemMapper() {
        return contentMapper;
    }

    @Override
    protected String masterFieldName() {
        if(true){
           throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }





}


