package com.jfeat.module.album.services.domain.dao;

import com.jfeat.crud.plus.annotation.SQLTag;
import com.jfeat.module.album.services.domain.model.RssImageNameRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.album.services.gen.persistence.model.RssImageName;
import com.jfeat.module.album.services.gen.crud.model.RssImageNameModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-10-21
 */
public interface QueryRssImageNameDao extends QueryMasterDao<RssImageName> {
    /*
     * Query entity list by page
     */
    @SQLTag("RssImageName")
    List<RssImageNameRecord> findRssImageNamePage(Page<RssImageNameRecord> page, @Param("record") RssImageNameRecord record,
                                                  @Param("tag") String tag,
                                                  @Param("search") String search, @Param("orderBy") String orderBy,
                                                  @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    RssImageNameModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<RssImageNameModel> queryMasterModelList(@Param("masterId") Object masterId);

    List<RssImageNameRecord> findRssImageNamePageWithItem(Page<RssImageNameRecord> page, @Param("record") RssImageNameRecord record,
                                                          @Param("tag") String tag,
                                                          @Param("search") String search, @Param("orderBy") String orderBy,
                                                          @Param("startTime") Date startTime, @Param("endTime") Date endTime);


}