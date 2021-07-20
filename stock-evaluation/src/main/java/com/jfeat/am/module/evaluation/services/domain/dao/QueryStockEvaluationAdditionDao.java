package com.jfeat.am.module.evaluation.services.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationAdditionRecord;

import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
public interface QueryStockEvaluationAdditionDao extends BaseMapper<StockEvaluationAdditionRecord> {
    List<StockEvaluationAdditionRecord> findStockEvaluationAdditionPage(Page<StockEvaluationAdditionRecord> page, @Param("record") StockEvaluationAdditionRecord record, @Param("orderBy") String orderBy);
}