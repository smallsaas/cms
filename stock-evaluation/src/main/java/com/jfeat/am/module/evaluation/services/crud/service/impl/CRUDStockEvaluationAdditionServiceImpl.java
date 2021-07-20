package com.jfeat.am.module.evaluation.services.crud.service.impl;
            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationAddition;
import com.jfeat.am.module.evaluation.services.persistence.dao.StockEvaluationAdditionMapper;


import com.jfeat.am.module.evaluation.services.crud.service.CRUDStockEvaluationAdditionService;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDStockEvaluationAdditionService
 * @author Code Generator
 * @since 2018-07-16
 */

@Service
public class CRUDStockEvaluationAdditionServiceImpl  extends CRUDServiceSlaveImpl<StockEvaluationAddition> implements CRUDStockEvaluationAdditionService {







    private static final String masterField = "evaluation_id";

    @Resource
    private StockEvaluationAdditionMapper stockEvaluationAdditionMapper;

    @Override
    protected BaseMapper<StockEvaluationAddition> getSlaveItemMapper() {
        return stockEvaluationAdditionMapper;
    }

    @Override
    protected String masterFieldName() {
        if(true){
           throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }





}


