package com.jfeat.module.rss.services.domain.service.impl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.module.rss.services.domain.dao.QueryRssDao;
import com.jfeat.module.rss.services.domain.dao.QueryRssItemDao;
import com.jfeat.module.rss.services.domain.service.RssOverModelService;
import com.jfeat.module.rss.services.gen.crud.model.RssItemModel;
import com.jfeat.module.rss.services.gen.crud.model.RssModel;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssOverModelServiceImpl;
import com.jfeat.module.rss.services.gen.persistence.dao.RssItemMapper;
import com.jfeat.module.rss.services.gen.persistence.dao.RssMapper;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
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
                if (pid==null){
                    throw new BusinessException(BusinessCode.DatabaseInsertError,"未找到pid");
                }
                for (RssItem rssItem:rssItemList){
                    rssItem.setPid(pid);
                }
                affected+=queryRssItemDao.batchAddRssItem(rssItemList);
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        return affected;
    }

    @Override
    public Integer deleteRss(Long id) {

        Integer affect = 0;
        affect+=rssMapper.deleteById(id);

        QueryWrapper<RssItem> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RssItem.PID,id);
        affect+=rssItemMapper.delete(queryWrapper);

        return affect;
    }
}
