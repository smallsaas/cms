package com.jfeat.am.module.ff.services.domain.model;

import com.jfeat.am.module.ff.services.persistence.model.StockFavorite;

import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
public class StockFavoriteModel extends StockFavorite {
    List<StockFavorite> favorites;


    public List<StockFavorite> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<StockFavorite> favorites) {
        this.favorites = favorites;
    }
}
