package com.jfeat.module.rss.services.domain.dao;

import com.jfeat.module.rss.services.domain.model.RssImagePropRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.rss.services.gen.persistence.model.RssImageProp;
import com.jfeat.module.rss.services.gen.crud.model.RssImagePropModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-10-21
 */
public interface QueryRssImagePropDao extends QueryMasterDao<RssImageProp> {
   /*
    * Query entity list by page
    */
    List<RssImagePropRecord> findRssImagePropPage(Page<RssImagePropRecord> page, @Param("record") RssImagePropRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    RssImagePropModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<RssImagePropModel> queryMasterModelList(@Param("masterId") Object masterId);

    List<RssImageProp> queryRssImagePropByPid(@Param("pid") Long pid);
}