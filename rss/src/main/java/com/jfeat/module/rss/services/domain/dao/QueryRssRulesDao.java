package com.jfeat.module.rss.services.domain.dao;

import com.jfeat.module.rss.services.domain.model.RssRulesRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.rss.services.gen.persistence.model.RssRules;
import com.jfeat.module.rss.services.gen.crud.model.RssRulesModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-10-22
 */
public interface QueryRssRulesDao extends QueryMasterDao<RssRules> {
   /*
    * Query entity list by page
    */
    List<RssRulesRecord> findRssRulesPage(Page<RssRulesRecord> page, @Param("record") RssRulesRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    RssRulesModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<RssRulesModel> queryMasterModelList(@Param("masterId") Object masterId);
}