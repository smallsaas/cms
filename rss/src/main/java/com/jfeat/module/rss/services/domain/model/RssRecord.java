package com.jfeat.module.rss.services.domain.model;

import com.jfeat.module.rss.services.gen.crud.model.RssItemModel;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;

import java.util.List;

/**
 * Created by Code generator on 2022-09-26
 */
public class RssRecord extends Rss {
    private List<RssItemModel> rssItemModelList;

    public List<RssItemModel> getRssItemModelList() {
        return rssItemModelList;
    }

    public void setRssItemModelList(List<RssItemModel> rssItemModelList) {
        this.rssItemModelList = rssItemModelList;
    }
}
