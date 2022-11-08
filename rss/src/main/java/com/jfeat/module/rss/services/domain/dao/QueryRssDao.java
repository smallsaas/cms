package com.jfeat.module.rss.services.domain.dao;

import com.jfeat.crud.plus.annotation.SQLTag;
import com.jfeat.module.rss.services.domain.model.RssRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.crud.model.RssModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-09-26
 */
public interface QueryRssDao extends QueryMasterDao<Rss> {
    /*
     * Query entity list by page
     */
    List<RssRecord> findRssPage(Page<RssRecord> page, @Param("record") RssRecord record,
                                @Param("tag") String tag,
                                @Param("search") String search, @Param("orderBy") String orderBy,
                                @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    RssModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<RssModel> queryMasterModelList(@Param("masterId") Object masterId);


    @SQLTag("Rss")
    List<RssRecord> queryRssWithItem(Page<RssRecord> page, @Param("record") RssRecord record,
                                     @Param("tag") String tag,
                                     @Param("search") String search, @Param("orderBy") String orderBy,
                                     @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<RssRecord> queryRssWithItemNotTags(Page<RssRecord> page, @Param("record") RssRecord record,
                                     @Param("tag") String tag,
                                     @Param("search") String search, @Param("orderBy") String orderBy,
                                     @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<RssRecord> queryRssWithItemNotTag(Page<RssRecord> page, @Param("record") RssRecord record,
                                     @Param("ids") List<Long> ids,
                                     @Param("tag") String tag,
                                     @Param("search") String search, @Param("orderBy") String orderBy,
                                     @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    int batchAddRss(@Param("recordList") List<RssRecord> recordList);


    @SQLTag("Rss")
    List<RssRecord> queryRssWithItemNotComponent(Page<RssRecord> page, @Param("record") RssRecord record,
                                                 @Param("tag") String tag,
                                                 @Param("search") String search, @Param("orderBy") String orderBy,
                                                 @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    List<RssRecord> queryRssWithItemNotComponentAndTag(Page<RssRecord> page, @Param("record") RssRecord record,
                                           @Param("ids") List<Long> ids,
                                           @Param("tag") String tag,
                                           @Param("search") String search, @Param("orderBy") String orderBy,
                                           @Param("startTime") Date startTime, @Param("endTime") Date endTime);
}