package com.jfeat.module.rss.services.gen.crud.model;
// this is serviceModel.java.vm




import java.util.List;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;

/**
 * Created by Code generator on 2022-09-26
 *  * slaves.size() : 1
 *  * modelpack : import com.jfeat.module.rss.services.gen.crud.model.RssModel;
 */
public class RssModel extends Rss{

    // rssItem
    // RssItem
    // rssItem
    private List<RssItem> items;

    public List<RssItem> getItems() {
        return this.items;
    }

    public void setItems(List<RssItem> items) {
        this.items = items;
    }
}
