package com.jfeat.module.rss.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jfeat.module.album.services.domain.service.LowAutoAlbumOverModelService;
import com.jfeat.module.lc_low_auto_page_style.services.domain.service.LowAutoPageRssStyleService;
import com.jfeat.module.rss.services.domain.model.RssRecord;
import com.jfeat.module.rss.services.domain.service.RssStyleControl;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class RssStyleControlImpl implements RssStyleControl {

    @Resource
    LowAutoPageRssStyleService LowAutoPageRssStyleService;

    @Resource
    LowAutoAlbumOverModelService rssImageNameOverModelService;

    @Override
    public List<RssRecord> andRssStyleValue(List<RssRecord> recordList) {
        if (recordList!=null && recordList.size()>0){
            Map<String, JSONObject> map =  LowAutoPageRssStyleService.getAllStyleJsonMap();

            Map<String, String> imageStyleMap = rssImageNameOverModelService.getAllAlbumToMap();

            for (RssRecord record:recordList){

                List<RssItem> rssItemList = record.getRssItemList();

                if (rssItemList!=null && rssItemList.size()>0){
                    for (RssItem rssItem:rssItemList){

//                        每一项图片容器
                        if (rssItem.getImageContainer()!=null && map.containsKey(rssItem.getImageContainer())){
                            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map.get(rssItem.getImageContainer())));
                            rssItem.setImageContainerJson(jsonObject);
                        }

//                        每一个RssItem image css
                        if (rssItem.getImageStyle()!=null && map.containsKey(rssItem.getImageStyle())){
                            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map.get(rssItem.getImageStyle())));
                            rssItem.setImageStyleJson(jsonObject);
                        }

                        List<RssComponent> rssComponentList = rssItem.getRssComponentList();

                        if (rssComponentList!=null && rssComponentList.size()>0){

                            for (RssComponent rssComponent:rssComponentList){
                                if (rssComponent.getComponentStyle()!=null&&(rssComponent.getComponentType().equals("uploadImage")||rssComponent.getComponentType().equals("showImage"))){

                                    if (imageStyleMap.containsKey(rssComponent.getComponentStyle())){
                                       rssComponent.setComponentStyleValue(imageStyleMap.get(rssComponent.getComponentStyle()));
                                    }

                                }

                                if (map.containsKey(rssComponent.getCssName())){
                                    JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map.get(rssComponent.getCssName())));
                                    rssComponent.setCss(jsonObject);
                                }


                            }




                        }
                    }

                }
            }

        }
        return recordList;
    }
}
