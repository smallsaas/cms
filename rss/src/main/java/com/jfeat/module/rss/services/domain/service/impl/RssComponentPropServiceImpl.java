package com.jfeat.module.rss.services.domain.service.impl;
import com.jfeat.module.rss.services.domain.dao.QueryRssComponentPropDao;
import com.jfeat.module.rss.services.domain.service.RssComponentPropService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssComponentPropServiceImpl;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssComponentPropService")
public class RssComponentPropServiceImpl extends CRUDRssComponentPropServiceImpl implements RssComponentPropService {


    @Resource
    QueryRssComponentPropDao queryRssComponentPropDao;

    @Override
    protected String entityName() {
        return "RssComponentProp";
    }


    @Override
    @Transactional
    public Integer batchRssComponentProp(List<RssComponentProp> rssComponentPropList) {
        Integer affect = 0;
        if (rssComponentPropList!=null && rssComponentPropList.size()>0){
            affect+= queryRssComponentPropDao.batchAdd(rssComponentPropList);
        }
        return affect;
    }
}
