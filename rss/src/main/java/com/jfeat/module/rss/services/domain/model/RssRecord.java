package com.jfeat.module.rss.services.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jfeat.module.rss.services.gen.crud.model.RssItemModel;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import com.jfeat.module.rss.services.gen.persistence.model.RssItemC;

import java.util.List;

/**
 * Created by Code generator on 2022-09-26
 */
public class RssRecord extends Rss {



    private List<RssItemC> records;

    String content;


    public List<RssItemC> getRecords() {
        return records;
    }

    public void setRecords(List<RssItemC> records) {
        this.records = records;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
