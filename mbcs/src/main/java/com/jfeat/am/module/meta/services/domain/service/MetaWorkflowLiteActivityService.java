package com.jfeat.am.module.meta.services.domain.service;

import com.jfeat.am.module.meta.services.gen.crud.service.CRUDMetaWorkflowLiteActivityService;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaWorkflowLiteActivity;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface MetaWorkflowLiteActivityService extends CRUDMetaWorkflowLiteActivityService {

    List<MetaWorkflowLiteActivity> getListByEntity(String entity, Long entityId);

}