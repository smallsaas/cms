package com.jfeat.module.rss.services.domain.service;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssCssNamedPropsService;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssNamedProps;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssPropOption;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;

import java.util.List;
import java.util.Map;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssCssNamedPropsService extends CRUDRssCssNamedPropsService {

    Map<String,JSONObject> getAllCssJson();

    Integer deleteRssNameProp(Long id);

    Integer updateRssNameProp(RssCssNamedProps rssCssNamedProps);

    Integer createRssNameProp(RssCssNamedProps rssCssNamedProps);
}