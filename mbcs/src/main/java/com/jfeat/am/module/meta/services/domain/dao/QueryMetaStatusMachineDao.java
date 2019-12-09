package com.jfeat.am.module.meta.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.meta.services.domain.model.EntityCurrentStatus;
import com.jfeat.am.module.meta.services.domain.model.EntityCurrentVersionAndStatus;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaStatusMachine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2018-12-19
 */
public interface QueryMetaStatusMachineDao extends BaseMapper<MetaStatusMachine> {

    List<MetaStatusMachine> findMetaStatusMachine(MetaStatusMachine entity);

    String getEntityCurrentStatus(@Param("id") Long id, @Param("entityTableName") String entityTableName);

    List<EntityCurrentStatus> getEntitiesCurrentStatus(@Param("ids") List<Long> ids, @Param("entityTableName") String entityTableName);

    List<EntityCurrentVersionAndStatus> getEntityCurrentVersionAndStatus(@Param("ids") List<Long> ids,
                                                                         @Param("entityTableName") String entityTableName);

    Integer updateEntityStatus(@Param("entityTableName") String entityTableName,
                               @Param("id") Long id,
                               @Param("status") String status);

    Integer batchUpdateEntityStatus(@Param("entityTableName") String entityTableName,
                                    @Param("ids") List<Long> ids,
                                    @Param("status") String status);
}