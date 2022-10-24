package com.jfeat.module.rss.services.domain.model;

import com.jfeat.module.rss.services.gen.persistence.model.RssImageName;
import com.jfeat.module.rss.services.gen.persistence.model.RssImageProp;

import java.util.List;

/**
 * Created by Code generator on 2022-10-21
 */
public class RssImageNameRecord extends RssImageName {

    List<RssImageProp> rssImagePropList;

    public List<RssImageProp> getRssImagePropList() {
        return rssImagePropList;
    }

    public void setRssImagePropList(List<RssImageProp> rssImagePropList) {
        this.rssImagePropList = rssImagePropList;
    }
}
