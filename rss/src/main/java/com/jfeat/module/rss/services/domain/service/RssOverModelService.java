package com.jfeat.module.rss.services.domain.service;

import com.jfeat.module.rss.services.domain.model.RssRecord;
import com.jfeat.module.rss.services.gen.crud.model.RssItemModel;
import com.jfeat.module.rss.services.gen.crud.model.RssModel;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssOverModelService;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssOverModelService extends CRUDRssOverModelService {

    Integer createRss(RssModel rss, List<RssItem> rssItemList);

    Integer deleteRss(Long id);

    Integer updateRss(RssRecord rssRecord);

    Integer updateRssRecord(RssRecord rssRecord);

    Integer updateAndParseRss(RssRecord rssRecord);

}