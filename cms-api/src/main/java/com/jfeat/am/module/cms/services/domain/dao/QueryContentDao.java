package com.jfeat.am.module.cms.services.domain.dao;

import com.jfeat.am.module.cms.services.domain.model.record.ContentRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-11
 */
public interface QueryContentDao extends BaseMapper<ContentRecord> {
    List<ContentRecord> findContentPage(Page<ContentRecord> page, @Param("record") ContentRecord record, @Param("orderBy") String orderBy);
}