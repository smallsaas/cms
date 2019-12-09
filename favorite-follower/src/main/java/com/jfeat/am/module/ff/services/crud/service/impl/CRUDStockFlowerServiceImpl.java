package com.jfeat.am.module.ff.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.ff.services.crud.service.CRUDStockFlowerService;
import com.jfeat.am.module.ff.services.persistence.dao.StockFlowerMapper;
import com.jfeat.am.module.ff.services.persistence.model.StockFlower;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDStockFlowerService
 * @author Code Generator
 * @since 2018-07-16
 */

@Service
public class CRUDStockFlowerServiceImpl  extends CRUDServiceOnlyImpl<StockFlower> implements CRUDStockFlowerService {





        @Resource
        private StockFlowerMapper stockFlowerMapper;

        @Override
        protected BaseMapper<StockFlower> getMasterMapper() {
                return stockFlowerMapper;
        }







}


