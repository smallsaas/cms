package com.jfeat.am.module.meta.services.gen.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.meta.services.gen.crud.service.CRUDMetaStatusMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.dao.MetaStatusMachineMapper;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaStatusMachine;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * implementation
 * </p>
 * CRUDMetaStatusMachineService
 *
 * @author Code Generator
 * @since 2018-12-19
 */

@Service
public class CRUDMetaStatusMachineServiceImpl extends CRUDServiceOnlyImpl<MetaStatusMachine> implements CRUDMetaStatusMachineService {


    @Resource
    protected MetaStatusMachineMapper metaStatusMachineMapper;

    @Override
    protected BaseMapper<MetaStatusMachine> getMasterMapper() {
        return metaStatusMachineMapper;
    }


}


