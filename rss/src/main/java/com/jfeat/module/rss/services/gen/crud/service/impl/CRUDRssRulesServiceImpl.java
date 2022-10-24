package com.jfeat.module.rss.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.rss.services.gen.persistence.model.RssRules;
import com.jfeat.module.rss.services.gen.persistence.dao.RssRulesMapper;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssRulesService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDRssRulesService
 * @author Code generator
 * @since 2022-10-22
 */

@Service
public class CRUDRssRulesServiceImpl  extends CRUDServiceOnlyImpl<RssRules> implements CRUDRssRulesService {





        @Resource
        protected RssRulesMapper rssRulesMapper;

        @Override
        protected BaseMapper<RssRules> getMasterMapper() {
                return rssRulesMapper;
        }







}


