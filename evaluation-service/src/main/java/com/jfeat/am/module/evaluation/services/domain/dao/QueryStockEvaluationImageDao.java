package com.jfeat.am.module.evaluation.services.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationImageRecord;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
public interface QueryStockEvaluationImageDao extends BaseMapper<StockEvaluationImageRecord> {
    List<StockEvaluationImageRecord> findStockEvaluationImagePage(Page<StockEvaluationImageRecord> page, @Param("record") StockEvaluationImageRecord record, @Param("orderBy") String orderBy);
}