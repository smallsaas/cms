package com.jfeat.am.module.icon.services.domain.dao;

import com.jfeat.am.module.icon.services.domain.model.FrontIconRecord;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import com.jfeat.crud.plus.annotation.SQLTag;
import org.apache.ibatis.annotations.Param;
import com.jfeat.am.module.icon.services.gen.persistence.model.FrontIcon;
import com.jfeat.am.module.icon.services.gen.crud.model.FrontIconModel;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-09-02
 */
public interface QueryFrontIconDao extends QueryMasterDao<FrontIcon> {
   /*
    * Query entity list by page
    */
    @SQLTag("icon")
    List<FrontIconRecord> findFrontIconPage(Page<FrontIconRecord> page, @Param("record") FrontIconRecord record,
                                            @Param("search") String search, @Param("orderBy") String orderBy,
                                            @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    FrontIconModel queryMasterModel(@Param("id") Long id);
}