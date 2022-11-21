package com.jfeat.module.album.services.gen.crud.model;
// this is serviceModel.java.vm




import java.util.List;
import com.jfeat.module.album.services.gen.persistence.model.RssImageProp;
import com.jfeat.module.album.services.gen.persistence.model.RssImageName;

/**
 * Created by Code generator on 2022-10-21
 *  * slaves.size() : 1
 *  * modelpack : import com.jfeat.module.rss.services.gen.crud.model.RssImageNameModel;
 */
public class RssImageNameModel extends RssImageName{

    // rssImageProp
    // RssImageProp
    // rssImageProp
    private List<RssImageProp> items;



    public List<RssImageProp> getItems() {
        return this.items;
    }

    public void setItems(List<RssImageProp> items) {
        this.items = items;
    }
}
