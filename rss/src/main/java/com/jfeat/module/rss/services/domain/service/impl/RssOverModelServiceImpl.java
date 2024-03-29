package com.jfeat.module.rss.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.module.lc_low_auto_page_style.services.domain.service.LowAutoPageRssStyleService;
import com.jfeat.module.rss.services.domain.dao.QueryRssComponentPropDao;
import com.jfeat.module.rss.services.domain.dao.QueryRssDao;
import com.jfeat.module.rss.services.domain.dao.QueryRssItemDao;
import com.jfeat.module.rss.services.domain.model.RssRecord;
import com.jfeat.module.rss.services.domain.service.ImageRegexService;
import com.jfeat.module.rss.services.domain.service.RssComponentService;
import com.jfeat.module.rss.services.domain.service.RssItemService;
import com.jfeat.module.rss.services.domain.service.RssOverModelService;
import com.jfeat.module.rss.services.gen.crud.model.RssModel;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssOverModelServiceImpl;
import com.jfeat.module.rss.services.gen.persistence.dao.RssComponentMapper;
import com.jfeat.module.rss.services.gen.persistence.dao.RssComponentPropMapper;
import com.jfeat.module.rss.services.gen.persistence.dao.RssItemMapper;
import com.jfeat.module.rss.services.gen.persistence.dao.RssMapper;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import org.apache.commons.io.IOUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssService")
public class RssOverModelServiceImpl extends CRUDRssOverModelServiceImpl implements RssOverModelService {

    @Resource
    RssOverModelService rssOverModelService;

    @Resource
    QueryRssDao queryRssDao;

    @Resource
    QueryRssItemDao queryRssItemDao;

    @Resource
    RssItemMapper rssItemMapper;

    @Resource
    RssMapper rssMapper;

    @Resource
    RssItemService rssItemService;

    @Resource
    RssComponentService rssComponentService;


    @Resource
    LowAutoPageRssStyleService lowAutoPageRssStyleService;

    @Resource
    ImageRegexService imageRegexService;

    @Resource
    RssComponentMapper rssComponentMapper;

    @Resource
    RssComponentPropMapper rssComponentPropMapper;


    @Resource
    QueryRssComponentPropDao queryRssComponentPropDao;

    @Override
    protected String entityName() {
        return "Rss";
    }


    @Override
    @Transactional
    public Integer createRss(RssModel rss, List<RssItem> rssItemList) {
        Integer affected = 0;
        try {
            rss.setItems(null);
            DefaultFilterResult filterResult = new DefaultFilterResult();
            affected = rssOverModelService.createMaster(rss, null, null, null);
            if (affected > 0) {
                Long pid = rss.getId();
                if (pid == null) {
                    throw new BusinessException(BusinessCode.DatabaseInsertError, "未找到pid");
                }


                for (RssItem rssItem : rssItemList) {
                    rssItem.setPid(pid);
//                    解析image
                    if (rssItem.getImageExpression() != null && rssItem.getImageExpression().equals("")) {
                        List<String> stringList = imageRegexService.parseImageExpression(rssItem.getImageExpression());
                        if (stringList != null && stringList.size() > 0) {
                            rssItem.setImageSort(stringList.get(0));
                            if (stringList.size() > 1) {
                                rssItem.setImageRatio(stringList.get(1));
                            }
                        }
                    }

                }

//                批量插入
                affected += queryRssItemDao.batchAddRssItem(rssItemList);


                QueryWrapper<RssItem> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(RssItem.PID, pid);
                List<RssItem> rssItems = rssItemMapper.selectList(queryWrapper);

//                解析数据
                for (RssItem rssItem : rssItems) {
                    affected += rssItemService.parserRssItem(rssItem);
                }
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        return affected;
    }

    @Override
    public Integer deleteRss(Long id) {

        Integer affect = 0;
        affect += rssMapper.deleteById(id);

        QueryWrapper<RssItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RssItem.PID, id);
        affect += rssItemMapper.delete(queryWrapper);

        return affect;
    }


    @Override
    @Transactional
    public Integer updateRss(RssRecord rssRecord) {

        Integer affect = 0;

        QueryWrapper<RssItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RssItem.PID, rssRecord.getId());
        List<RssItem> rssItemList = rssItemMapper.selectList(queryWrapper);

        if (rssRecord.getRssItemList() != null && rssRecord.getRssItemList().size() > 0) {

            if (rssItemList != null && rssItemList.size() > 0) {
                List<RssItem> rssItemModelList = rssRecord.getRssItemList().stream().filter(e -> e.getId() != null).collect(Collectors.toList());
                List<RssItem> backItem = rssRecord.getRssItemList().stream().filter(e -> e.getId() == null).collect(Collectors.toList());

                List<Long> newIds = null;
                if (rssItemModelList != null) {
                    newIds = rssItemModelList.stream().map(RssItem::getId).collect(Collectors.toList());
                    for (RssItem rssItemModel : rssItemModelList) {
                        affect += rssItemMapper.updateById(rssItemModel);
                    }
                }

//                数据库id
                List<Long> oldIds = rssItemList.stream().map(RssItem::getId).collect(Collectors.toList());

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
                    affect += queryRssItemDao.batchAddRssItem(backItem);
                }

                if (deleteIDs != null && deleteIDs.size() > 0) {
                    QueryWrapper<RssItem> itemQueryWrapper = new QueryWrapper<>();
                    itemQueryWrapper.in(RssItem.ID, deleteIDs);
                    affect += rssItemMapper.delete(itemQueryWrapper);
                }


            } else {
                affect += queryRssItemDao.batchAddRssItem(rssRecord.getRssItemList());
            }

        } else if (rssItemList != null && rssItemList.size() > 0) {
            affect += rssItemMapper.delete(queryWrapper);
        }

        return affect;
    }

    @Override
    @Transactional
    public Integer updateRssRecord(RssRecord rssRecord) {
        Integer affect = 0;
        if (rssRecord == null) {
            return affect;
        }

        if (rssRecord.getRssItemList() == null || rssRecord.getRssItemList().size() <= 0) {
            rssMapper.deleteById(rssRecord.getId());
        }

        affect += updateRss(rssRecord);
        for (RssItem rssItem : rssRecord.getRssItemList()) {
            affect += rssItemService.updateRssItem(rssItem);
//            修改组件
            if (rssItem.getRssComponentList() != null && rssItem.getRssComponentList().size() > 0) {
                for (RssComponent rssComponent : rssItem.getRssComponentList()) {
                    affect += rssComponentService.updateRssComponent(rssComponent);
                }
            }

        }
        return affect;
    }

    @Override
    public Integer updateAndParseRss(RssRecord rssRecord) {
        Integer affected = 0;
        if (rssRecord.getRssItemList() != null && rssRecord.getRssItemList().size() > 0) {

            QueryWrapper<RssItem> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(RssItem.PID, rssRecord.getId());
            affected += rssItemMapper.delete(queryWrapper);
//            删除子项
            for (RssItem rssItem : rssRecord.getRssItemList()) {

//                解析image
                if (rssItem.getImageExpression() != null && !rssItem.getImageExpression().equals("")) {
                    List<String> stringList = imageRegexService.parseImageExpression(rssItem.getImageExpression());
                    if (stringList != null && stringList.size() > 0) {
                        rssItem.setImageSort(stringList.get(0));
                        if (stringList.size() > 1) {
                            rssItem.setImageRatio(stringList.get(1));
                        }
                    }
                }

                if (rssItem.getId() != null) {
                    rssItemService.deleteRssItem(rssItem.getId());
                    affected += rssItemService.deleteRssComponent(rssItem);
                    rssItem.setId(null);
                }
            }

            for (RssItem rssItem : rssRecord.getRssItemList()) {
                if (rssItem.getId() == null) {
                    try {
                        rssItem.setPid(rssRecord.getId());
                        affected += rssItemService.createMaster(rssItem);
                    } catch (DuplicateKeyException e) {
                        throw new BusinessException(BusinessCode.DuplicateKey);
                    }
                } else {
                    affected += rssItemMapper.updateById(rssItem);
                }
                affected += rssItemService.parserRssItem(rssItem);
            }

        }
        return affected;
    }


    @Override
    public List<RssRecord> andCss(List<RssRecord> recordList) {

        if (recordList != null && recordList.size() > 0) {
            Map<String, JSONObject> map = lowAutoPageRssStyleService.getAllStyleJsonMap();
            for (RssRecord record : recordList) {


                List<RssItem> rssItemList = record.getRssItemList();

                if (rssItemList != null && rssItemList.size() > 0) {
                    for (RssItem rssItem : rssItemList) {

                        if (rssItem.getImageContainer() != null && map.containsKey(rssItem.getImageContainer())) {
                            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map.get(rssItem.getImageContainer())));
                            rssItem.setImageContainerJson(jsonObject);
                        }

                        if (rssItem.getImageStyle() != null && map.containsKey(rssItem.getImageStyle())) {
                            JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map.get(rssItem.getImageStyle())));
                            rssItem.setImageStyleJson(jsonObject);
                        }

                        List<RssComponent> rssComponentList = rssItem.getRssComponentList();

                        if (rssComponentList != null && rssComponentList.size() > 0) {

                            for (RssComponent rssComponent : rssComponentList) {
                                if (rssComponent.getCssName() != null && rssComponent.getComponentType().equals("css")) {

                                    if (map.containsKey(rssComponent.getCssName())) {
                                        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map.get(rssComponent.getCssName())));
                                        rssComponent.setCss(jsonObject);
                                    }
                                }
                            }

                        }
                    }

                }
            }

        }
        return recordList;
    }

    @Override
    public String uploadRssFile(MultipartFile file) {
        BufferedReader reader = null;
        try {
            //用流读取文件
            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            StringBuffer content = new StringBuffer();
            // 读取想定文件
            while ((line = reader.readLine()) != null) {
                content.append(line + "\n");
            }
            return content.toString();
        } catch (IOException e) {

        } finally {
            if (reader != null) {
                IOUtils.closeQuietly(reader);
            }
        }

        return null;

    }

    @Override
    @Transactional
    public Integer copyRss(Long id) {

        Integer affect = 0;

        Rss oldRss = rssMapper.selectById(id);
        if (oldRss == null) {
            return affect;
        }

//        复制rss
        oldRss.setId(null);
        oldRss.setEdit(Rss.EDIT_NOT);
        oldRss.setName(UUID.randomUUID().toString());
        oldRss.setCopyWriting(Rss.COPY_WRITING_YES);
        rssOverModelService.createMaster(oldRss);


        QueryWrapper<RssItem> rssItemQueryWrapper = new QueryWrapper<>();
        rssItemQueryWrapper.eq(RssItem.PID, id);
        List<RssItem> rssItemList = rssItemMapper.selectList(rssItemQueryWrapper);

        List<Long> rssItemIds = new ArrayList<>();
        if (rssItemList != null && rssItemList.size() > 0) {

            for (RssItem rssItem : rssItemList) {

                QueryWrapper<RssComponent> rssComponentQueryWrapper = new QueryWrapper<>();
                rssComponentQueryWrapper.eq(RssComponent.RSS_ITEM_ID, rssItem.getId());
                List<RssComponent> rssComponentList = rssComponentMapper.selectList(rssComponentQueryWrapper);

//                复制rssItem
                rssItem.setId(null);
                rssItem.setPid(oldRss.getId());
                affect += rssItemService.createMaster(rssItem);


                if (rssComponentList != null && rssComponentList.size() > 0) {

                    for (RssComponent rssComponent : rssComponentList) {
                        QueryWrapper<RssComponentProp> rssComponentPropQueryWrapper = new QueryWrapper<>();
                        rssComponentPropQueryWrapper.eq(RssComponentProp.COMPONENT_ID, rssComponent.getId());
                        List<RssComponentProp> rssComponentPropList = rssComponentPropMapper.selectList(rssComponentPropQueryWrapper);

//                        复制rssComponent
                        rssComponent.setId(null);
                        rssComponent.setRssItemId(rssItem.getId());
                        affect += rssComponentService.createMaster(rssComponent);


                        if (rssComponentPropList != null && rssComponentPropList.size() > 0) {
                            for (RssComponentProp rssComponentProp : rssComponentPropList) {
                                rssComponentProp.setId(null);
                                rssComponentProp.setComponentId(rssComponent.getId());
                            }

//                            复制rssComponentProp
                            affect += queryRssComponentPropDao.batchAdd(rssComponentPropList);

                        }


                    }


                }


            }


        }

        return affect;
    }


}
