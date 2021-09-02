package com.jfeat.am.module.evaluation.services.domain.model.record;

import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationStar;

/**
 * Created by Code Generator on 2018-07-16
 */
public class StockEvaluationStarRecord extends StockEvaluationStar{
    private Integer startCount;

    public Integer getStartCount() {
        return startCount;
    }

    public void setStartCount(Integer startCount) {
        this.startCount = startCount;
    }
}
