package com.jfeat.am.module.evaluation.services.domain.dao;

import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationImageRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
public interface QueryStockEvaluationImageDao extends BaseMapper<StockEvaluationImageRecord> {
    List<StockEvaluationImageRecord> findStockEvaluationImagePage(Page<StockEvaluationImageRecord> page, @Param("record") StockEvaluationImageRecord record, @Param("orderBy") String orderBy);
}