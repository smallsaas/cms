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
import java.util.regex.Pattern;

@Service
public class RssIntermediateControlImpl implements RssIntermediateControl {

    @Resource
    RssRulesMapper rssRulesMapper;

    @Resource
    RssRulesService rssRulesService;

    @Override
    public void involve(RssItem rssItem,Queue<String> queue) {

        QueryWrapper<RssRules> rssRulesQueryWrapper= new QueryWrapper<>();
        rssRulesQueryWrapper.orderByDesc(RssRules.PRIORITY);
        List<RssRules> rssRulesList = rssRulesMapper.selectList(rssRulesQueryWrapper);

        while (!queue.isEmpty()){

            String data = queue.peek();

//            注释
            if (data.startsWith("<!>")){
                queue.poll();
                continue;
            }

            if (data==null){
                break;
            }
            String type = "";
            String methode = "common";
//            判断处理方法是哪个 默认是通用
            for (RssRules rssRules:rssRulesList){

                String startSymbol = "^".concat("(").concat(rssRules.getSymbol()).concat(").*");
                boolean isSymbol = Pattern.matches(startSymbol, data.strip());

                if (isSymbol){
                    if (rssRules.getHandMethod()!=null && !rssRules.getHandMethod().equals("")){
                        methode = rssRules.getHandMethod();

                    }
                    break;
                }
            }
            if (methode.equals("image")){
                rssRulesService.parseRssByImage(queue,rssItem,rssRulesList);
            }else if (methode.equals("title")){
                rssRulesService.parseRssByTitle(queue,rssItem,rssRulesList);
            }else if (methode.equals("bulleLis")){
                rssRulesService.parseRssByBulleList(queue,rssItem,rssRulesList);
            }else if (methode.equals("link")){
                rssRulesService.parseRssByLink(queue,rssItem,rssRulesList);
            }else if (methode.equals("richText")){
                rssRulesService.parseRssByRichText(queue,rssItem,rssRulesList);
            }else if (methode.equals("table")){
                rssRulesService.parseRssByTable(queue,rssItem,rssRulesList);
            } else{
                rssRulesService.parseRssByCommon(queue,rssItem,rssRulesList);
            }
        }
    }
}
