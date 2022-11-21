package com.jfeat.module.album.services.domain.service;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.module.album.services.gen.crud.model.RssImageNameModel;
import com.jfeat.module.album.services.gen.crud.service.CRUDRssImageNameOverModelService;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssImageNameOverModelService extends CRUDRssImageNameOverModelService {

    Integer createRssImageNameItem(RssImageNameModel entity);

    RssImageNameModel getRssImageNameModelById(Long id);

    Map<String,String> getAllRssImageToMap();

    Map<String,List<JSONObject>> getAllRssImageToList();

}