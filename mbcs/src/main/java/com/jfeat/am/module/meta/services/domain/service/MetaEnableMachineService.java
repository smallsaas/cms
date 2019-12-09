package com.jfeat.am.module.meta.services.domain.service;


import com.jfeat.am.module.meta.services.gen.crud.service.CRUDMetaEnableMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEnableMachine;
import com.jfeat.crud.base.tips.BulkResult;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface MetaEnableMachineService extends CRUDMetaEnableMachineService {

    List<MetaEnableMachine> findMetaEnableMachine(MetaEnableMachine queryEntity);

    Integer enableEntity(Long id, String entity);

    Integer disableEntity(Long id, String entity);

    Integer validateEntity(Long id, String entity, Integer value);

    BulkResult bulkEnableEntity(List<Long> ids, String entity);

    BulkResult bulkDisableEntity(List<Long> ids, String entity);

    BulkResult bulkValidateEntity(List<Long> ids, String entity, Integer value);
}