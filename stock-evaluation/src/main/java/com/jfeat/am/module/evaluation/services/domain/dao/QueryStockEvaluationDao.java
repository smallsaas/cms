package com.jfeat.am.module.evaluation.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationRecord;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationStarRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
public interface QueryStockEvaluationDao extends BaseMapper<StockEvaluationRecord> {
    List<StockEvaluationRecord> findStockEvaluationPage(Page<StockEvaluationRecord> page,
                                                       @Param("record") StockEvaluationRecord record,
                                                       @Param("orderBy") String orderBy,
                                                       @Param("memberId")Long memberId);
    // 评论与评论下回复同级返回
    List<StockEvaluationRecord> findStockEvaluationPageWithReplys(Page<StockEvaluationRecord> page,
                                                        @Param("record") StockEvaluationRecord record,
                                                        @Param("orderBy") String orderBy,
                                                        @Param("memberId")Long memberId);

    List<StockEvaluationStarRecord> findStarCount(@Param("record") StockEvaluationRecord record, @Param("display") String display);

    List<StockEvaluationRecord> bulkFindEvaluations(Page<StockEvaluationRecord> page,
                                                    @Param("ids") List<Long> ids,
                                                    @Param("stockIds") List<Long> stockIds,
                                                    @Param("stockType") String stockType,
                                                    @Param("starValue") Integer starValue);
    // 查询评论下的回复列表
    List<StockEvaluationRecord> findReplys(@Param("record") StockEvaluationRecord record,@Param("memberId") Long memberId);
}