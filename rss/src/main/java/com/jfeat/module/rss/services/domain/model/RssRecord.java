package com.jfeat.module.rss.services.domain.model;

import com.jfeat.module.rss.services.gen.crud.model.RssItemModel;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;

import java.util.List;

/**
 * Created by Code generator on 2022-09-26
 */
public class RssRecord extends Rss {

    String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
