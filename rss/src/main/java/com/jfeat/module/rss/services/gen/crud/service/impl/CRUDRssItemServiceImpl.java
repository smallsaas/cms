package com.jfeat.module.rss.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import com.jfeat.module.rss.services.gen.persistence.dao.RssItemMapper;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssItemService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDRssItemService
 * @author Code generator
 * @since 2022-09-26
 */

@Service
public class CRUDRssItemServiceImpl  extends CRUDServiceOnlyImpl<RssItem> implements CRUDRssItemService {





        @Resource
        protected RssItemMapper rssItemMapper;

        @Override
        protected BaseMapper<RssItem> getMasterMapper() {
                return rssItemMapper;
        }







}


