package com.jfeat.am.module.ff.services.crud.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.ff.services.crud.service.CRUDStockFavoriteService;
import com.jfeat.am.module.ff.services.persistence.dao.StockFavoriteMapper;
import com.jfeat.am.module.ff.services.persistence.model.StockFavorite;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDStockFavoriteService
 * @author Code Generator
 * @since 2018-07-16
 */

@Service
public class CRUDStockFavoriteServiceImpl  extends CRUDServiceOnlyImpl<StockFavorite> implements CRUDStockFavoriteService {





        @Resource
        private StockFavoriteMapper stockFavoriteMapper;

        @Override
        protected BaseMapper<StockFavorite> getMasterMapper() {
                return stockFavoriteMapper;
        }







}


