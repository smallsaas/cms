package com.jfeat.module.rss.services.domain.service;

import com.jfeat.module.rss.services.gen.crud.service.CRUDRssComponentService;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssComponentService extends CRUDRssComponentService{
    Integer updateRssComponent(RssComponent rssComponent);
    }