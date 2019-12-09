package com.jfeat.am.module.meta.services.gen.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaWorkflowLiteActivity;
import com.jfeat.am.module.meta.services.gen.persistence.dao.MetaWorkflowLiteActivityMapper;


import com.jfeat.am.module.meta.services.gen.crud.service.CRUDMetaWorkflowLiteActivityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;

import javax.annotation.Resource;

import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 * 工作流记录表 implementation
 * </p>
 * CRUDMetaWorkflowLiteActivityService
 *
 * @author Code Generator
 * @since 2019-01-29
 */

@Service
public class CRUDMetaWorkflowLiteActivityServiceImpl extends CRUDServiceOnlyImpl<MetaWorkflowLiteActivity> implements CRUDMetaWorkflowLiteActivityService {


    @Resource
    protected MetaWorkflowLiteActivityMapper metaWorkflowLiteActivityMapper;

    @Override
    protected BaseMapper<MetaWorkflowLiteActivity> getMasterMapper() {
        return metaWorkflowLiteActivityMapper;
    }


}


