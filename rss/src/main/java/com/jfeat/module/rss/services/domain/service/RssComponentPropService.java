package com.jfeat.module.rss.services.domain.service;

import com.jfeat.module.rss.services.gen.crud.service.CRUDRssComponentPropService;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssComponentPropService extends CRUDRssComponentPropService {

    Integer batchRssComponentProp(List<RssComponentProp> rssComponentPropList);
}