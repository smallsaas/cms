package com.jfeat.images.services.crud.service.impl;
            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import com.jfeat.images.services.crud.service.CRUDStockImagesService;
import com.jfeat.images.services.persistence.dao.StockImagesMapper;
import com.jfeat.images.services.persistence.model.StockImages;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDStockImagesService
 * @author Code Generator
 * @since 2018-08-07
 */

@Service("CRUDStockImagesService")
public class CRUDStockImagesServiceImpl extends CRUDServiceOnlyImpl<StockImages> implements CRUDStockImagesService {

        @Resource
        private StockImagesMapper stockImagesMapper;

        @Override
        protected BaseMapper<StockImages> getMasterMapper() {
                return stockImagesMapper;
        }







}


