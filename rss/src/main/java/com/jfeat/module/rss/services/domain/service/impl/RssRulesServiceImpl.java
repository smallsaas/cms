package com.jfeat.module.rss.services.domain.service.impl;
import com.jfeat.module.rss.services.domain.service.RssComponentPropService;
import com.jfeat.module.rss.services.domain.service.RssComponentService;
import com.jfeat.module.rss.services.domain.service.RssRulesService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssRulesServiceImpl;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import com.jfeat.module.rss.services.gen.persistence.model.RssRules;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssRulesService")
public class RssRulesServiceImpl extends CRUDRssRulesServiceImpl implements RssRulesService {


    @Resource
    RssComponentPropService rssComponentPropService;

    @Resource
    RssComponentService rssComponentService;

    @Override
    protected String entityName() {
        return "RssRules";
    }


    @Override
    public Integer parseRssByCommon(Queue<String> queue, RssItem rssItem,List<RssRules> rssRulesList) {

        Integer affect = 0;
        Long componentId = null;
//        判读是什么类型
        String type = "title";
        String data = queue.poll();
        String decollator = "";
        RssComponent rssComponent = new RssComponent();


        if (data==null){
            return 0;
        }

//        判断Rss类型
        for (RssRules rssRules:rssRulesList){

            String startSymbol = "^".concat("(").concat(rssRules.getSymbol()).concat(").*");
            boolean isSymbol = Pattern.matches(startSymbol, data.strip());

            if (isSymbol){
                Pattern r = Pattern.compile(startSymbol);
                Matcher m = r.matcher(data);
                if (m.find()){
                    String symbol = m.group(1);
                    type = rssRules.getName();
                    decollator = rssRules.getDecollator();
                    if (type!=null && symbol.length()>0){
                        data = data.substring(symbol.length()).strip();
                    }
                }
                break;


            }
        }

//        判断是否有风格
        String pattern = ".*<(.*)>.*";
        boolean isMatch = Pattern.matches(pattern, data);
        if (isMatch){
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()){
                rssComponent.setComponentStyle(m.group(1));
                String componentStyle = "<".concat(m.group(1)).concat(">");
                data = data.substring(componentStyle.length()).strip();
            }
        }



        List<String> componentPropData = new ArrayList<>();
        if (decollator!=null && !decollator.equals("")){
            String lineDate[] = data.split(decollator);
            componentPropData = Arrays.asList(lineDate);
        }else {
            componentPropData.add(data);
        }

        rssComponent.setComponentName(type);
        rssComponent.setComponentType(type);
        rssComponent.setRssItemId(rssItem.getId());
        if (isMatch){
            Pattern r = Pattern.compile(pattern);
            Matcher m = r.matcher(data);
            if (m.find()){
                rssComponent.setComponentStyle(m.group(1));
            }
        }

        affect += rssComponentService.createMaster(rssComponent);
        componentId = rssComponent.getId();


        for (int i=0;i<componentPropData.size();i++){
            RssComponentProp rssComponentProp = new RssComponentProp();
            rssComponentProp.setComponentId(componentId);
            rssComponentProp.setPropDefaultValue(componentPropData.get(i));
            affect += rssComponentPropService.createMaster(rssComponentProp);
        }
//        读取内容
        return affect;
    }
}
