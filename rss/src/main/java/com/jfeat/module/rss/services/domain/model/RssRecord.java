package com.jfeat.module.rss.services.domain.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jfeat.module.rss.services.gen.crud.model.RssItemModel;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;

import java.util.List;

/**
 * Created by Code generator on 2022-09-26
 */
public class RssRecord extends Rss {



    private List<RssItem> records;

    String content;


    public List<RssItem> getRecords() {
        return records;
    }

    public void setRecords(List<RssItem> records) {
        this.records = records;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
