package com.jfeat.am.module.cms.services.domain.dao;

import com.jfeat.am.module.cms.services.domain.model.record.ImageRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-11
 */
public interface QueryImageDao extends BaseMapper<ImageRecord> {
    List<ImageRecord> findImagePage(Page<ImageRecord> page, @Param("record") ImageRecord record, @Param("orderBy") String orderBy);
}