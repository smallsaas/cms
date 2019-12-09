package com.jfeat.am.module.meta.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaWorkflowLiteActivity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2019-01-29
 */
public interface QueryMetaWorkflowLiteActivityDao extends BaseMapper<MetaWorkflowLiteActivity> {

    List<MetaWorkflowLiteActivity> getListByEntity(@Param("entity") String entity,
                                                   @Param("entityId") Long entityId);
}