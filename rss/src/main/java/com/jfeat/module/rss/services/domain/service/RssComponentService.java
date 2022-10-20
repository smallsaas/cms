package com.jfeat.module.rss.services.domain.service;

import com.jfeat.module.rss.services.gen.crud.service.CRUDRssComponentService;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssComponentService extends CRUDRssComponentService {
    Integer updateRssComponent(RssComponent rssComponent);

    Integer parserComponent(RssItem rssItem,List<String> componentProp);
}