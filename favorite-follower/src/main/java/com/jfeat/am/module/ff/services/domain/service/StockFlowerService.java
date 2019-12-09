package com.jfeat.am.module.ff.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.ff.services.crud.service.CRUDStockFlowerService;
import com.jfeat.am.module.ff.services.persistence.model.StockFlower;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface StockFlowerService extends CRUDStockFlowerService {

    /**
     * 点赞 记录 我的点赞 记录
     *
     * */
    List<StockFlower> myFlowers(Page<StockFlower> page, Long memberId,String stockType);


    /**
     *  单个商品点赞 记录
     *
     * */
    List<StockFlower> stockFlowers(Page<StockFlower> page, Long stockId, String stockType);

    /**
     *
     * 是否 已点赞
     * */
    Integer isFlowered(Long userId,Long stockId,String stockType);

    /**
     *
     * 是否 已点赞
     * */
    StockFlower flowered(Long userId,Long stockId,String stockType);

    /**
     *
     * 点赞(cancel点赞)
     * */
    Integer floweredOrNot(StockFlower flower, Long originId, String originType);
}