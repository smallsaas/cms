package com.jfeat.module.rss.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.module.rss.services.domain.service.RssIntermediateControl;
import com.jfeat.module.rss.services.domain.service.RssRulesService;
import com.jfeat.module.rss.services.gen.persistence.dao.RssRulesMapper;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import com.jfeat.module.rss.services.gen.persistence.model.RssRules;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Queue;

@Service
public class RssIntermediateControlImpl implements RssIntermediateControl {

    @Resource
    RssRulesMapper rssRulesMapper;

    @Resource
    RssRulesService rssRulesService;

    @Override
    public void involve(RssItem rssItem,Queue<String> queue) {

        QueryWrapper<RssRules> rssRulesQueryWrapper= new QueryWrapper<>();
        List<RssRules> rssRulesList = rssRulesMapper.selectList(rssRulesQueryWrapper);

        while (!queue.isEmpty()){

            String data = queue.peek();
            if (data==null){
                break;
            }
            String type = "";
            String methode = "common";

            for (RssRules rssRules:rssRulesList){
                if (data.startsWith(rssRules.getSymbol())){
                    if (rssRules.getHandMethod()!=null && !rssRules.getHandMethod().equals("")){
                        methode = rssRules.getHandMethod();
                    }
                }
            }

            if (methode.equals("common")){
                rssRulesService.parseRssByCommon(queue,rssItem,rssRulesList);
            }


        }
    }
}
