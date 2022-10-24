package com.jfeat.module.rss.services.domain.service;

import com.jfeat.module.rss.services.domain.model.RssImageNameRecord;
import com.jfeat.module.rss.services.gen.crud.model.RssImageNameModel;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssImageNameOverModelService;
import com.jfeat.module.rss.services.gen.persistence.model.RssImageName;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssImageNameOverModelService extends CRUDRssImageNameOverModelService {

    Integer createRssImageNameItem(RssImageNameModel entity);

    RssImageNameModel getRssImageNameModelById(Long id);

    Map<String,String> getAllRssImageToMap();


}