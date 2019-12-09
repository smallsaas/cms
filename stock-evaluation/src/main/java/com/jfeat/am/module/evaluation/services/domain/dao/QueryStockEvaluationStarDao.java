package com.jfeat.am.module.evaluation.services.domain.dao;

import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationStarRecord;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
public interface QueryStockEvaluationStarDao extends BaseMapper<StockEvaluationStarRecord> {
    List<StockEvaluationStarRecord> findStockEvaluationStarPage(Page<StockEvaluationStarRecord> page, @Param("record") StockEvaluationStarRecord record, @Param("orderBy") String orderBy);
}