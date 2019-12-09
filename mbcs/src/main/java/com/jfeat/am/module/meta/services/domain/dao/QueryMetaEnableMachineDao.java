package com.jfeat.am.module.meta.services.domain.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.meta.services.domain.model.MetaEnableMachineRecord;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEnableMachine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2019-01-05
 */
public interface QueryMetaEnableMachineDao extends BaseMapper<MetaEnableMachine> {
    List<MetaEnableMachineRecord> findMetaEnableMachinePage(Page<MetaEnableMachineRecord> page,
                                                            @Param("record") MetaEnableMachineRecord record,
                                                            @Param("search") String search,
                                                            @Param("orderBy") String orderBy);

    List<MetaEnableMachine> findMetaEnableMachine(MetaEnableMachine entity);

    Integer validateEntity(@Param("entityTableName") String entityTableName,
                           @Param("entityFieldName") String entityFieldName,
                           @Param("value") Integer value,
                           @Param("id") Long id);

    Integer bulkValidateEntity(@Param("entityTableName") String entityTableName,
                           @Param("entityFieldName") String entityFieldName,
                           @Param("value") Integer value,
                           @Param("ids") List<Long> ids);
}