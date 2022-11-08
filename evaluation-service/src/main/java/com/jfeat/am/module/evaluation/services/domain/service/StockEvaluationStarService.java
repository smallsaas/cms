package com.jfeat.am.module.evaluation.services.domain.service;

import com.jfeat.am.module.evaluation.services.crud.service.CRUDStockEvaluationStarService;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationRecord;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface StockEvaluationStarService extends CRUDStockEvaluationStarService{


    void setEvaluationStar(List<StockEvaluationRecord> evaluations);
    
}