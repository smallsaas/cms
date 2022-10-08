package com.jfeat.module.rss.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssPropOption;
import com.jfeat.module.rss.services.gen.persistence.dao.RssCssPropOptionMapper;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssCssPropOptionService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDRssCssPropOptionService
 * @author Code generator
 * @since 2022-09-30
 */

@Service
public class CRUDRssCssPropOptionServiceImpl  extends CRUDServiceOnlyImpl<RssCssPropOption> implements CRUDRssCssPropOptionService {





        @Resource
        protected RssCssPropOptionMapper rssCssPropOptionMapper;

        @Override
        protected BaseMapper<RssCssPropOption> getMasterMapper() {
                return rssCssPropOptionMapper;
        }







}


