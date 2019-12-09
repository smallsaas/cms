package com.jfeat.images.services.domain.url;

import java.util.List;

/**
 * Created by leuo on 2018/8/8.
 */
public class UrlRequest {

    // 同时添加 单张 或者多张图片
    List<String> urls;

    // 关联主体的 类别
    String stockType;

    Long stockId;

    // 是否为私有：0不是，1是
    Integer isPrimary;

    public Integer getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(Integer isPrimary) {
        this.isPrimary = isPrimary;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }
}
