package com.jfeat.module.rss.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;
import com.jfeat.module.rss.services.gen.persistence.dao.RssComponentMapper;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssComponentService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDRssComponentService
 * @author Code generator
 * @since 2022-09-30
 */

@Service
public class CRUDRssComponentServiceImpl  extends CRUDServiceOnlyImpl<RssComponent> implements CRUDRssComponentService {





        @Resource
        protected RssComponentMapper rssComponentMapper;

        @Override
        protected BaseMapper<RssComponent> getMasterMapper() {
                return rssComponentMapper;
        }







}


