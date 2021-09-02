package com.jfeat.am.module.evaluation.services.crud.service.impl;
            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationImage;
import com.jfeat.am.module.evaluation.services.persistence.dao.StockEvaluationImageMapper;


import com.jfeat.am.module.evaluation.services.crud.service.CRUDStockEvaluationImageService;
import com.jfeat.crud.plus.impl.CRUDServiceSlaveImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDStockEvaluationImageService
 * @author Code Generator
 * @since 2018-07-16
 */

@Service
public class CRUDStockEvaluationImageServiceImpl  extends CRUDServiceSlaveImpl<StockEvaluationImage> implements CRUDStockEvaluationImageService {







    private static final String masterField = "evaluation_id";

    @Resource
    private StockEvaluationImageMapper stockEvaluationImageMapper;

    @Override
    protected BaseMapper<StockEvaluationImage> getSlaveItemMapper() {
        return stockEvaluationImageMapper;
    }

    @Override
    protected String masterFieldName() {
        if(true){
           throw new RuntimeException("Please check masterField is correct!");
        }
        return masterField;
    }





}


