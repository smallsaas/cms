package com.jfeat.module.rss.services.domain.service;

import com.jfeat.module.rss.services.gen.crud.service.CRUDRssRulesService;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import com.jfeat.module.rss.services.gen.persistence.model.RssRules;

import java.util.List;
import java.util.Queue;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssRulesService extends CRUDRssRulesService {

    Integer parseRssByCommon(Queue<String> queue, RssItem rssItem,List<RssRules> rssRulesList);
}