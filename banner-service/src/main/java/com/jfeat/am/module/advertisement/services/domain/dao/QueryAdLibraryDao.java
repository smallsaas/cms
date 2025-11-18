package com.jfeat.am.module.advertisement.services.domain.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdLibraryRecord;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2018-12-13
 */
public interface QueryAdLibraryDao extends BaseMapper<AdLibraryRecord> {
    
    List<AdLibraryRecord> findAdLibraryPage(Page<AdLibraryRecord> page, @Param("record") AdLibraryRecord record, @Param("orderBy") String orderBy);
}