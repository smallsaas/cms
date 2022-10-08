package com.jfeat.module.rss.services.domain.dao;

import com.jfeat.module.rss.services.domain.model.RssCssPropOptionRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import org.apache.ibatis.annotations.Param;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssPropOption;
import com.jfeat.module.rss.services.gen.crud.model.RssCssPropOptionModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-09-30
 */
public interface QueryRssCssPropOptionDao extends QueryMasterDao<RssCssPropOption> {
   /*
    * Query entity list by page
    */
    List<RssCssPropOptionRecord> findRssCssPropOptionPage(Page<RssCssPropOptionRecord> page, @Param("record") RssCssPropOptionRecord record,
                                            @Param("tag") String tag,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    RssCssPropOptionModel queryMasterModel(@Param("id") Long id);


    /*
     * Query entity model list for slave items
     */
    List<RssCssPropOptionModel> queryMasterModelList(@Param("masterId") Object masterId);
}