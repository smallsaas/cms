package com.jfeat.module.feedback.services.domain.dao;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;
import com.jfeat.module.feedback.services.domain.model.ComplainRecordRecord;
import com.jfeat.module.feedback.services.gen.crud.model.ComplainRecordModel;
import com.jfeat.module.feedback.services.gen.persistence.model.ComplainRecord;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Code generator on 2022-08-12
 */
public interface QueryComplainRecordDao extends QueryMasterDao<ComplainRecord> {
   /*
    * Query entity list by page
    */
    List<ComplainRecordRecord> findComplainRecordPage(Page<ComplainRecordRecord> page, @Param("record") ComplainRecordRecord record,
                                                      @Param("search") String search, @Param("orderBy") String orderBy,
                                                      @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    /*
     * Query entity model for details
     */
    ComplainRecordModel queryMasterModel(@Param("id") Long id);
}