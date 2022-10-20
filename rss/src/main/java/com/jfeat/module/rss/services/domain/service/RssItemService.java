package com.jfeat.module.rss.services.domain.service;

import com.jfeat.module.rss.services.gen.crud.service.CRUDRssItemService;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssItemService extends CRUDRssItemService {
    Integer parserRssItem(RssItem rssItem);

    Integer updateRssItem(RssItem rssItem);

    Integer deleteRssComponent(RssItem rssItem);

    Integer deleteRssItem(Long id);
}