package com.jfeat.module.rss.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.rss.services.gen.persistence.model.RssImageProp;
import com.jfeat.module.rss.services.gen.persistence.dao.RssImagePropMapper;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssImagePropService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDRssImagePropService
 * @author Code generator
 * @since 2022-10-21
 */

@Service
public class CRUDRssImagePropServiceImpl  extends CRUDServiceOnlyImpl<RssImageProp> implements CRUDRssImagePropService {





        @Resource
        protected RssImagePropMapper rssImagePropMapper;

        @Override
        protected BaseMapper<RssImageProp> getMasterMapper() {
                return rssImagePropMapper;
        }







}


