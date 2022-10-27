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

    Integer parseRssByImage(Queue<String> queue, RssItem rssItem,List<RssRules> rssRulesList);

    Integer parseRssByTitle(Queue<String> queue, RssItem rssItem,List<RssRules> rssRulesList);

    Integer parseRssByBulleList(Queue<String> queue, RssItem rssItem,List<RssRules> rssRulesList);

    Integer parseRssByLink(Queue<String> queue, RssItem rssItem,List<RssRules> rssRulesList);

    Integer parseRssByRichText(Queue<String> queue, RssItem rssItem,List<RssRules> rssRulesList);

    Integer parseRssByTable(Queue<String> queue, RssItem rssItem,List<RssRules> rssRulesList);

//    Integer parseRssByImageTest(Queue<String> queue, RssItem rssItem,List<RssRules> rssRulesList);
}