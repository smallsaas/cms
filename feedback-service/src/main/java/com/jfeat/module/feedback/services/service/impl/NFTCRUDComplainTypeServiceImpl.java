package com.jfeat.module.feedback.services.service.impl;
// ServiceImpl start


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import com.jfeat.module.feedback.services.gen.persistence.dao.ComplainTypeMapper;
import com.jfeat.module.feedback.services.gen.persistence.model.ComplainType;
import com.jfeat.module.feedback.services.service.NFTCRUDComplainTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDComplainTypeService
 * @author Code generator
 * @since 2022-01-27
 */

@Service
public class NFTCRUDComplainTypeServiceImpl extends CRUDServiceOnlyImpl<ComplainType> implements NFTCRUDComplainTypeService {





        @Resource
        protected ComplainTypeMapper complainTypeMapper;

        @Override
        protected BaseMapper<ComplainType> getMasterMapper() {
                return complainTypeMapper;
        }







}


