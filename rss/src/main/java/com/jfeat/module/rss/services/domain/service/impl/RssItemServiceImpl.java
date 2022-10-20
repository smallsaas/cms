package com.jfeat.module.rss.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.module.rss.services.domain.dao.QueryRssComponentDao;
import com.jfeat.module.rss.services.domain.service.ComponentTypeRegexService;
import com.jfeat.module.rss.services.domain.service.RssComponentPropService;
import com.jfeat.module.rss.services.domain.service.RssComponentService;
import com.jfeat.module.rss.services.domain.service.RssItemService;
import com.jfeat.module.rss.services.gen.crud.model.RssItemModel;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssItemServiceImpl;
import com.jfeat.module.rss.services.gen.persistence.dao.RssComponentMapper;
import com.jfeat.module.rss.services.gen.persistence.dao.RssComponentPropMapper;
import com.jfeat.module.rss.services.gen.persistence.dao.RssItemMapper;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssItemService")
public class RssItemServiceImpl extends CRUDRssItemServiceImpl implements RssItemService {

    @Resource
    RssItemMapper rssItemMapper;

    @Resource
    RssComponentMapper rssComponentMapper;

    @Resource
    QueryRssComponentDao queryRssComponentDao;

    @Resource
    RssComponentService rssComponentService;

    @Resource
    RssComponentPropService rssComponentPropService;

    @Resource
    ComponentTypeRegexService componentTypeRegexService;

    @Resource
    RssComponentPropMapper rssComponentPropMapper;


    @Override
    protected String entityName() {
        return "RssItem";
    }


//    @Override
//    public Integer parserRssItem(RssItem rssItem) {
//
//        Integer affect = 0;
//        String text = rssItem.getTitle();
//        String lineStr[] = text.split("/n");
//
//        List<String> componentProp = new ArrayList<>();
//        for (String s : lineStr) {
//            String lineDate[] = s.split(",| ");
//            for (String str : lineDate) {
//                componentProp.add(str);
//            }
//        }
//
//
//        String type = null;
//        Long componentId = null;
//        for (String prop : componentProp) {
//            if (componentTypeRegexService.getKeyByRegex(prop)!=null) {
//                type = componentTypeRegexService.getKeyByRegex(prop);
//                RssComponent rssComponent = new RssComponent();
//                rssComponent.setComponentName(type);
//                rssComponent.setComponentType(type);
//                rssComponent.setRssItemId(rssItem.getId());
//                affect += rssComponentService.createMaster(rssComponent);
//                componentId = rssComponent.getId();
//                continue;
//            }
//            if (type != null && componentId != null) {
//                RssComponentProp rssComponentProp = new RssComponentProp();
//                rssComponentProp.setComponentId(componentId);
//                rssComponentProp.setPropDefaultValue(prop);
//                affect += rssComponentPropService.createMaster(rssComponentProp);
//            }
//        }
//
//
//        return affect;
//    }

    @Override
    @Transactional
    public Integer parserRssItem(RssItem rssItem) {

        Integer affect = 0;
        String text = rssItem.getTitle();

//        获取每一行的数据
        String lineStr[] = text.split("\n");


        List<List<String>> componentPropData = new ArrayList<>();

        for (String s : lineStr) {
            String lineDate[] = s.split(",| ");
            componentPropData.add(Arrays.asList(lineDate));
        }

        for (int i = 0; i < componentPropData.size(); i++) {
            List<String> componentProp = componentPropData.get(i);
            Long componentId = null;

            Map<String, String> regexMap = componentTypeRegexService.getALlRegex();
            for (int j = 0; j < componentProp.size(); j++) {

                Boolean flag = false;

                if (j == 0) {
                    String content = componentProp.get(j);
                    String pattern = "^<(.*)>.*";
                    String type = "title";

                    boolean isMatch = Pattern.matches(pattern, content);
                    if (isMatch) {
                        flag = true;
                        type="css";
                    }else {
                        for (String key : regexMap.keySet()) {
                            if (componentProp.get(j).startsWith(regexMap.get(key))) {
                                flag = true;
                                type = key;
                                break;
                            }
                        }
                    }

                    RssComponent rssComponent = new RssComponent();
                    rssComponent.setComponentName(type);
                    rssComponent.setComponentType(type);
                    rssComponent.setRssItemId(rssItem.getId());
                    if (isMatch){
                        Pattern r = Pattern.compile(pattern);
                        Matcher m = r.matcher(content);
                        if (m.find()){
                            rssComponent.setCssName(m.group(1));
                        }

                    }

                    affect += rssComponentService.createMaster(rssComponent);
                    componentId = rssComponent.getId();
                }

                if (!flag) {
                    RssComponentProp rssComponentProp = new RssComponentProp();
                    rssComponentProp.setComponentId(componentId);
                    rssComponentProp.setPropDefaultValue(componentProp.get(j));
                    affect += rssComponentPropService.createMaster(rssComponentProp);
                }
            }
        }
        return affect;
    }

    @Override
    @Transactional
    public Integer updateRssItem(RssItem rssItem) {
        Integer affect = 0;
        QueryWrapper<RssComponent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RssComponent.RSS_ITEM_ID, rssItem.getId());
        List<RssComponent> rssComponentList = rssComponentMapper.selectList(queryWrapper);

        if (rssItem.getRssComponentList() != null && rssItem.getRssComponentList().size() > 0) {

            if (rssComponentList != null && rssComponentList.size() > 0) {
                List<RssComponent> rssComponentList1 = rssItem.getRssComponentList().stream().filter(e -> e.getId() != null).collect(Collectors.toList());
                List<RssComponent> backItem = rssItem.getRssComponentList().stream().filter(e -> e.getId() == null).collect(Collectors.toList());

                List<Long> newIds = null;
                if (rssComponentList1 != null) {
                    newIds = rssComponentList1.stream().map(RssComponent::getId).collect(Collectors.toList());
                    for (RssComponent rssComponent : rssComponentList1) {
                        affect += rssComponentMapper.updateById(rssComponent);
                    }
                }

//                数据库id
                List<Long> oldIds = rssComponentList.stream().map(RssComponent::getId).collect(Collectors.toList());

//                需要删除的id
                List<Long> deleteIDs = new ArrayList<>();

                if (newIds != null && oldIds != null) {
                    for (Long id : oldIds) {
                        if (!newIds.contains(id)) {
                            deleteIDs.add(id);
                        }
                    }
                }

                if (backItem != null && backItem.size() > 0) {
                    affect += queryRssComponentDao.batchAdd(backItem);
                }

                if (deleteIDs != null && deleteIDs.size() > 0) {
                    QueryWrapper<RssComponent> itemQueryWrapper = new QueryWrapper<>();
                    itemQueryWrapper.in(RssComponent.ID, deleteIDs);
                    affect += rssComponentMapper.delete(itemQueryWrapper);
                }

            } else {
                affect += queryRssComponentDao.batchAdd(rssItem.getRssComponentList());
            }

        } else if (rssComponentList != null && rssComponentList.size() > 0) {
            affect += rssComponentMapper.delete(queryWrapper);
        }

        return affect;
    }

    @Override
    public Integer deleteRssComponent(RssItem rssItem) {
        Integer affected = 0;
        QueryWrapper<RssComponent> rssComponentQueryWrapper = new QueryWrapper<>();
        rssComponentQueryWrapper.eq(RssComponent.RSS_ITEM_ID, rssItem.getId());
        List<RssComponent> rssComponentList = rssComponentMapper.selectList(rssComponentQueryWrapper);

        if (rssComponentList != null && rssComponentList.size() > 0) {

            List<Long> rssComponentIds = rssComponentList.stream().map(RssComponent::getId).collect(Collectors.toList());

            if (rssComponentIds != null && rssComponentIds.size() > 0) {
                QueryWrapper<RssComponentProp> rssComponentPropQueryWrapper = new QueryWrapper<>();
                rssComponentPropQueryWrapper.in(RssComponentProp.COMPONENT_ID, rssComponentIds);
                affected += rssComponentPropMapper.delete(rssComponentPropQueryWrapper);
            }

            affected += rssComponentMapper.delete(rssComponentQueryWrapper);

        }
        return affected;
    }

    @Override
    @Transactional
    public Integer deleteRssItem(Long id) {
        Integer affected = 0;
        affected+=deleteMaster(id);
        QueryWrapper<RssComponent> rssComponentQueryWrapper = new QueryWrapper<>();
        rssComponentQueryWrapper.eq(RssComponent.RSS_ITEM_ID, id);
        List<RssComponent> rssComponentList = rssComponentMapper.selectList(rssComponentQueryWrapper);

        if (rssComponentList != null && rssComponentList.size() > 0) {

            List<Long> rssComponentIds = rssComponentList.stream().map(RssComponent::getId).collect(Collectors.toList());

            if (rssComponentIds != null && rssComponentIds.size() > 0) {
                QueryWrapper<RssComponentProp> rssComponentPropQueryWrapper = new QueryWrapper<>();
                rssComponentPropQueryWrapper.in(RssComponentProp.COMPONENT_ID, rssComponentIds);
                affected += rssComponentPropMapper.delete(rssComponentPropQueryWrapper);
            }

            affected += rssComponentMapper.delete(rssComponentQueryWrapper);

        }
        return affected;
    }

}
