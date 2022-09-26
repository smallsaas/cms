package com.jfeat.module.rss.services.domain.service.impl;
import com.jfeat.module.rss.services.domain.service.RssItemService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssItemServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssItemService")
public class RssItemServiceImpl extends CRUDRssItemServiceImpl implements RssItemService {

    @Override
    protected String entityName() {
        return "RssItem";
    }


                            }
