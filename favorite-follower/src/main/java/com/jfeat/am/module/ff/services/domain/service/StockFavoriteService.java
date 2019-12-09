package com.jfeat.am.module.ff.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.ff.services.crud.service.CRUDStockFavoriteService;
import com.jfeat.am.module.ff.services.domain.model.StockFavoriteModel;
import com.jfeat.am.module.ff.services.persistence.model.StockFavorite;
import com.jfeat.crud.base.request.Ids;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface StockFavoriteService extends CRUDStockFavoriteService {

    /**
     *
     * 我的收藏(文章/商品 etc....)
     * */
    List<StockFavorite> myFavorites(Page<StockFavorite> page, Long memberId, String stockType);

    /**
     *
     *  单个商品收藏列表
     * */
    List<StockFavorite> stockFavorites(Page<StockFavorite> page,Long stockId,String stockType);


    /**
     *
     *  是否 已点赞 改 物体
     * */
    Integer isFavorited(Long userId,Long stockId,String stockType);

    /**
     * @return StockFavorite
     *  是否 已收藏 改 物体
     * */
    StockFavorite favorited(Long userId,Long stockId,String stockType);

    /**
     *
     *  是否 已收藏 改 物体
     * */
    Integer favoritedOrNot(StockFavorite favorite,  Long originId, String originType);

    /**
     * enhance  bulk cancel Favorite
     * */
    Integer bulkCancelFavorite(Long userId, Ids ids);
}