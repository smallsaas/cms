package com.jfeat.am.module.meta.services.gen.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEntityPatchMachine;
import com.jfeat.am.module.meta.services.gen.persistence.dao.MetaEntityPatchMachineMapper;


import com.jfeat.am.module.meta.services.gen.crud.service.CRUDMetaEntityPatchMachineService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 * implementation
 * </p>
 * CRUDMetaEntityPatchMachineService
 *
 * @author Code Generator
 * @since 2019-01-07
 */

@Service
public class CRUDMetaEntityPatchMachineServiceImpl extends CRUDServiceOnlyImpl<MetaEntityPatchMachine> implements CRUDMetaEntityPatchMachineService {


    @Resource
    protected MetaEntityPatchMachineMapper metaEntityPatchMachineMapper;

    @Override
    protected BaseMapper<MetaEntityPatchMachine> getMasterMapper() {
        return metaEntityPatchMachineMapper;
    }


}


