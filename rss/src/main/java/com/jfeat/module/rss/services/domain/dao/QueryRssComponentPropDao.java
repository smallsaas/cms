package com.jfeat.module.rss.services.domain.dao;

import com.jfeat.module.rss.services.domain.model.RssComponentPropRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;
import com.jfeat.module.rss.services.gen.crud.model.RssComponentPropModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-09-30
 */
public interface QueryRssComponentPropDao extends QueryMasterDao<RssComponentProp> {
   /*
    * Query entity list by page
    */
    List<RssComponentPropRecord> findRssComponentPropPage(Page<RssComponentPropRecord> page, @Param("record") RssComponentPropRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    RssComponentPropModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<RssComponentPropModel> queryMasterModelList(@Param("masterId") Object masterId);

    List<RssComponentPropModel> queryComponentProp(@Param("componentId") Long componentId);

    int batchAdd(@Param("componentPropModelList") List<RssComponentProp> componentPropModelList);
}