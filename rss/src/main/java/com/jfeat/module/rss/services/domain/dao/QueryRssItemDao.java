package com.jfeat.module.rss.services.domain.dao;

import com.jfeat.module.rss.services.domain.model.RssItemRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import com.jfeat.module.rss.services.gen.crud.model.RssItemModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-09-26
 */
public interface QueryRssItemDao extends QueryMasterDao<RssItem> {
    /*
     * Query entity list by page
     */
    List<RssItemRecord> findRssItemPage(Page<RssItemRecord> page, @Param("record") RssItemRecord record,
                                        @Param("tag") String tag,
                                        @Param("search") String search, @Param("orderBy") String orderBy,
                                        @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    RssItemModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<RssItemModel> queryMasterModelList(@Param("masterId") Object masterId);

    Integer batchAddRssItem(@Param("rssItemList") List<RssItem> rssItemList);

    Integer batchAddRssItemModel(@Param("rssItemList") List<RssItemModel> rssItemList);

    List<RssItemModel> queryRssItemByPid(@Param("pid") Long pid);

    List<RssItemModel> queryRssItemNotComponentByPid(@Param("pid") Long pid);

    Integer updateRssItemModel(@Param("record") RssItemModel rssItemModel);
}