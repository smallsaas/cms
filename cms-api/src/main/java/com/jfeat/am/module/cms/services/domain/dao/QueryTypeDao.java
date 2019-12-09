package com.jfeat.am.module.cms.services.domain.dao;

import com.jfeat.am.module.cms.services.domain.model.record.TypeRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-11
 */
public interface QueryTypeDao extends BaseMapper<TypeRecord> {
    List<TypeRecord> findTypePage(Page<TypeRecord> page, @Param("record") TypeRecord record, @Param("orderBy") String orderBy);
}