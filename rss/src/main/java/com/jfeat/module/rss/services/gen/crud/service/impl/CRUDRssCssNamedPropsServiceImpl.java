package com.jfeat.module.rss.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssNamedProps;
import com.jfeat.module.rss.services.gen.persistence.dao.RssCssNamedPropsMapper;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssCssNamedPropsService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDRssCssNamedPropsService
 * @author Code generator
 * @since 2022-09-30
 */

@Service
public class CRUDRssCssNamedPropsServiceImpl  extends CRUDServiceOnlyImpl<RssCssNamedProps> implements CRUDRssCssNamedPropsService {





        @Resource
        protected RssCssNamedPropsMapper rssCssNamedPropsMapper;

        @Override
        protected BaseMapper<RssCssNamedProps> getMasterMapper() {
                return rssCssNamedPropsMapper;
        }







}


