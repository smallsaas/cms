package com.jfeat.am.module.evaluation.services.domain.dao;

import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationAdditionRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
public interface QueryStockEvaluationAdditionDao extends BaseMapper<StockEvaluationAdditionRecord> {
    List<StockEvaluationAdditionRecord> findStockEvaluationAdditionPage(Page<StockEvaluationAdditionRecord> page, @Param("record") StockEvaluationAdditionRecord record, @Param("orderBy") String orderBy);
}