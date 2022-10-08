package com.jfeat.am.module.evaluation.services.domain.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationRecord;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationStarRecord;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationAddition;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationImage;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationStar;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluation;

/**
 * Created by Code Generator on 2018-07-16
 */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class StockEvaluationModel extends StockEvaluation{
        private List<StockEvaluationAddition> stockEvaluationAdditions;
        private List<StockEvaluationImage> images;
        private List<StockEvaluationStar> stockEvaluationStars;

        public List<StockEvaluationAddition> getStockEvaluationAdditions() {
                return stockEvaluationAdditions;
        }

        public void setStockEvaluationAdditions(List<StockEvaluationAddition> stockEvaluationAdditions) {
                this.stockEvaluationAdditions = stockEvaluationAdditions;
        }

        public List<StockEvaluationImage> getImages() {
                return images;
        }

        public void setImages(List<StockEvaluationImage> images) {
                this.images = images;
        }

        public List<StockEvaluationStar> getStockEvaluationStars() {
                return stockEvaluationStars;
        }

        public void setStockEvaluationStars(List<StockEvaluationStar> stockEvaluationStars) {
                this.stockEvaluationStars = stockEvaluationStars;
        }
}
