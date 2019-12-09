package com.jfeat.am.module.evaluation.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.evaluation.services.crud.service.CRUDStockEvaluationService;
import com.jfeat.am.module.evaluation.services.domain.model.StockEvaluationModel;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationRecord;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluation;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface StockEvaluationService extends CRUDStockEvaluationService{


    /**
     * 评价用户，以及当前用户是否可以删除
     * */
    List<StockEvaluationRecord> evaluations(Page<StockEvaluationRecord> page,
                                           StockEvaluationRecord record,
                                           String orderBy,
                                           Long memberId);

    /**
     * 检索评价列表，并将评价下的回复以树形结构整合到相应评价下
     * */
    List<StockEvaluationRecord> evaluationsOnLayered(Page<StockEvaluationRecord> page,
                                                            StockEvaluationRecord record,
                                                            String orderBy,
                                                            Long memberId);

    /**
     * 屏蔽评价
     *
     * */
    Integer forbiddenEvaluation(Long id);


    /**
     * 置顶评价
     *
     * */
    Integer stickEvaluation(Long id);

    /**
     *
     * 单个 stock 的总评论数
     * @Parma Long stockId ，String stockType
     * */
    Integer stockEvaluationCount(Long stockId,String stockType);



    /**
     * 单个 stock 的 总评论  以及 评论的追加
     *
     * @Parma Long stockId ，String stockType
     */
    public List<StockEvaluation> evaluation(Long stockId, String stockType);

}