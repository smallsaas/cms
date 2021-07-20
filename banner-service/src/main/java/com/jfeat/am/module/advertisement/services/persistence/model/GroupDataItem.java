package com.jfeat.am.module.advertisement.services.persistence.model;

import java.util.List;

/**
 * Created by vincent on 2017/11/8.
 * 提供指定分组广告信息，前端调用
 */
public class GroupDataItem extends AdGroup {
    private List<Ad> ads;

    public List<Ad> getAds() {
        return ads;
    }

    public void setAds(List<Ad> ads) {
        this.ads = ads;
    }
}
