package com.jfeat.module.rss.services.domain.dao;

import com.jfeat.module.rss.services.domain.model.RssComponentRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;
import com.jfeat.module.rss.services.gen.crud.model.RssComponentModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-09-30
 */
public interface QueryRssComponentDao extends QueryMasterDao<RssComponent> {
   /*
    * Query entity list by page
    */
    List<RssComponentRecord> findRssComponentPage(Page<RssComponentRecord> page, @Param("record") RssComponentRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    RssComponentModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<RssComponentModel> queryMasterModelList(@Param("masterId") Object masterId);

    List<RssComponentModel> queryComponent(@Param("rssItemId") Long rssItemId);

    int batchAdd(@Param("rssComponentModelList") List<RssComponent> rssComponentList);
}