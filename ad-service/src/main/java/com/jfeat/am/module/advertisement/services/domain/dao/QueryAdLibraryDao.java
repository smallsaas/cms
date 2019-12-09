package com.jfeat.am.module.advertisement.services.domain.dao;

import com.jfeat.am.module.advertisement.services.domain.model.record.AdLibraryRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Code Generator on 2018-12-13
 */
public interface QueryAdLibraryDao extends BaseMapper<AdLibraryRecord> {
    List<AdLibraryRecord> findAdLibraryPage(Page<AdLibraryRecord> page, @Param("record") AdLibraryRecord record, @Param("orderBy") String orderBy);

    List<Ad> getAdRecordsByGroup(@Param("group") String group, @Param("enabled") Integer enabled);
}