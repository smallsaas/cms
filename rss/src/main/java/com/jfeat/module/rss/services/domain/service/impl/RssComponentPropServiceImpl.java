package com.jfeat.module.rss.services.domain.service.impl;
import com.jfeat.module.rss.services.domain.service.RssComponentPropService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssComponentPropServiceImpl;
import org.springframework.stereotype.Service;

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

    @Override
    protected String entityName() {
        return "RssComponentProp";
    }


                            }
