package com.jfeat.am.module.ff.services.domain.model.record;

import com.jfeat.am.module.ff.services.persistence.model.StockFavorite;

/**
 * Created by Code Generator on 2018-07-16
 */
public class StockFavoriteRecord extends StockFavorite {

    String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
