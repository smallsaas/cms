package com.jfeat.module.rss.services.gen.persistence.model;

import com.jfeat.am.crud.tag.services.persistence.model.StockTag;

public class RssTag extends StockTag {

    private Long rssId;

    private Boolean selected;

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Long getRssId() {
        return rssId;
    }

    public void setRssId(Long rssId) {
        this.rssId = rssId;
    }
}
