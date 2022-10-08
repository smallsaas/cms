package com.jfeat.module.rss.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;
import com.jfeat.module.rss.services.gen.persistence.dao.RssComponentPropMapper;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssComponentPropService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDRssComponentPropService
 * @author Code generator
 * @since 2022-09-30
 */

@Service
public class CRUDRssComponentPropServiceImpl  extends CRUDServiceOnlyImpl<RssComponentProp> implements CRUDRssComponentPropService {





        @Resource
        protected RssComponentPropMapper rssComponentPropMapper;

        @Override
        protected BaseMapper<RssComponentProp> getMasterMapper() {
                return rssComponentPropMapper;
        }







}


