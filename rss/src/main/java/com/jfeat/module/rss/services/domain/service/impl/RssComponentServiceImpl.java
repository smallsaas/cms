package com.jfeat.module.rss.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.module.rss.services.domain.dao.QueryRssComponentPropDao;
import com.jfeat.module.rss.services.domain.service.RssComponentService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssComponentServiceImpl;
import com.jfeat.module.rss.services.gen.persistence.dao.RssComponentPropMapper;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssComponentService")
public class RssComponentServiceImpl extends CRUDRssComponentServiceImpl implements RssComponentService {


    @Resource
    RssComponentPropMapper rssComponentPropMapper;

    @Resource
    QueryRssComponentPropDao queryRssComponentPropDao;

    @Override
    protected String entityName() {
        return "RssComponent";
    }


    @Override
    @Transactional
    public Integer updateRssComponent(RssComponent rssComponent) {

        Integer affect = 0;

        QueryWrapper<RssComponentProp> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RssComponentProp.COMPONENT_ID,rssComponent.getId());
        List<RssComponentProp> rssComponentList =  rssComponentPropMapper.selectList(queryWrapper);

        if (rssComponent.getRssComponentPropList()!=null&&rssComponent.getRssComponentPropList().size()>0){

            if (rssComponentList!=null && rssComponentList.size()>0){
//                原来数据有的数据
                List<RssComponentProp> rssComponentPropList = rssComponent.getRssComponentPropList().stream().filter(e->e.getId()!=null).collect(Collectors.toList());

//                新增的数据
                List<RssComponentProp> backItem = rssComponent.getRssComponentPropList().stream().filter(e->e.getId()==null).collect(Collectors.toList());

                List<Long> newIds  = null;
                if (rssComponentPropList!=null){
                    newIds = rssComponentPropList.stream().map(RssComponentProp::getId).collect(Collectors.toList());

//                    更新原有的的组件数据
                    for (RssComponentProp rssComponentProp:rssComponentPropList){
                        affect  += rssComponentPropMapper.updateById(rssComponentProp);
                    }
                }

//                数据库id
                List<Long> oldIds = rssComponentList.stream().map(RssComponentProp::getId).collect(Collectors.toList());

//                需要删除的id
                List<Long> deleteIDs = new ArrayList<>();

                if (newIds!=null && oldIds!=null){
                    for (Long id:oldIds){
                        if (!newIds.contains(id)){
                            deleteIDs.add(id);
                        }
                    }
                }

                if (backItem!=null && backItem.size()>0){
                    affect  +=queryRssComponentPropDao.batchAdd(backItem);
                }

//                删除组件啊
                if (deleteIDs!=null && deleteIDs.size()>0){
                    QueryWrapper<RssComponentProp> itemQueryWrapper = new QueryWrapper<>();
                    itemQueryWrapper.in(RssComponentProp.ID,deleteIDs);
                    affect  += rssComponentPropMapper.delete(itemQueryWrapper);
                }




            }else {
                affect  +=queryRssComponentPropDao.batchAdd(rssComponent.getRssComponentPropList());
            }

        }else if (rssComponentList!=null && rssComponentList.size()>0){
            affect  += rssComponentPropMapper.delete(queryWrapper);
        }

        return affect;
    }
}
