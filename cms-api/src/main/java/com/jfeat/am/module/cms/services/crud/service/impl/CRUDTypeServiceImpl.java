package com.jfeat.am.module.cms.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;



import com.jfeat.am.module.cms.services.crud.service.CRUDTypeService;
import com.jfeat.am.module.cms.services.persistence.dao.ArticleTypeMapper;
import com.jfeat.am.module.cms.services.persistence.model.ArticleType;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDTypeService
 * @author Code Generator
 * @since 2018-07-11
 */

@Service
public class CRUDTypeServiceImpl  extends CRUDServiceOnlyImpl<ArticleType> implements CRUDTypeService {





        @Resource
        private ArticleTypeMapper typeMapper;

        @Override
        protected BaseMapper<ArticleType> getMasterMapper() {
                return typeMapper;
        }







}


