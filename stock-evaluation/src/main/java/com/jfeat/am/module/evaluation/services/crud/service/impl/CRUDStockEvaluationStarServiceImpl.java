package com.jfeat.am.module.evaluation.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationStar;
import com.jfeat.am.module.evaluation.services.persistence.dao.StockEvaluationStarMapper;


import com.jfeat.am.module.evaluation.services.crud.service.CRUDStockEvaluationStarService;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDStockEvaluationStarService
 * @author Code Generator
 * @since 2018-07-16
 */

@Service
public class CRUDStockEvaluationStarServiceImpl  extends CRUDServiceSlaveImpl<StockEvaluationStar> implements CRUDStockEvaluationStarService {







    private static final String masterField = "evaluation_id";

    @Resource
    private StockEvaluationStarMapper stockEvaluationStarMapper;

    @Override
    protected BaseMapper<StockEvaluationStar> getSlaveItemMapper() {
        return stockEvaluationStarMapper;
    }

    @Override
    protected String masterFieldName() {
        if(true){
           throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }





}


