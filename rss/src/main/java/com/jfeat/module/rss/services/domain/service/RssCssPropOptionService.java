package com.jfeat.module.rss.services.domain.service;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.module.rss.services.gen.crud.service.CRUDRssCssPropOptionService;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssPropOption;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface RssCssPropOptionService extends CRUDRssCssPropOptionService {
    JSONObject rssCssPropOptionToJson(List<RssCssPropOption> rssCssPropOption);

    Integer deleteRssCssPropOptionByCssNameId(Long id);

    Integer batchRssCssPropOption(List<RssCssPropOption> rssCssPropOptionList);
}