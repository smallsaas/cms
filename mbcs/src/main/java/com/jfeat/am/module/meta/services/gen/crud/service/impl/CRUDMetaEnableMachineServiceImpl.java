package com.jfeat.am.module.meta.services.gen.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.meta.services.gen.crud.service.CRUDMetaEnableMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.dao.MetaEnableMachineMapper;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEnableMachine;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * implementation
 * </p>
 * CRUDMetaEnableMachineService
 *
 * @author Code Generator
 * @since 2019-01-05
 */

@Service
public class CRUDMetaEnableMachineServiceImpl extends CRUDServiceOnlyImpl<MetaEnableMachine> implements CRUDMetaEnableMachineService {


    @Resource
    protected MetaEnableMachineMapper metaEnableMachineMapper;

    @Override
    protected BaseMapper<MetaEnableMachine> getMasterMapper() {
        return metaEnableMachineMapper;
    }


}


