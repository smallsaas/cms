package com.jfeat.module.rss.services.domain.dao;

import com.jfeat.module.rss.services.domain.model.RssCssNamedPropsRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssNamedProps;
import com.jfeat.module.rss.services.gen.crud.model.RssCssNamedPropsModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-09-30
 */
public interface QueryRssCssNamedPropsDao extends QueryMasterDao<RssCssNamedProps> {
    /*
     * Query entity list by page
     */
    List<RssCssNamedPropsRecord> findRssCssNamedPropsPage(Page<RssCssNamedPropsRecord> page, @Param("record") RssCssNamedPropsRecord record,
                                                          @Param("tag") String tag,
                                                          @Param("search") String search, @Param("orderBy") String orderBy,
                                                          @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    RssCssNamedPropsModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<RssCssNamedPropsModel> queryMasterModelList(@Param("masterId") Object masterId);


    List<RssCssNamedProps> findRssCssNamedPropsWithItems(Page<RssCssNamedProps> page, @Param("record") RssCssNamedPropsRecord record,
                                                               @Param("tag") String tag,
                                                               @Param("search") String search, @Param("orderBy") String orderBy,
                                                               @Param("startTime") Date startTime, @Param("endTime") Date endTime);


    RssCssNamedProps queryRssCssNamePropsWithItems(@Param("record") RssCssNamedProps record);
}