package com.jfeat.am.module.advertisement.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.AdLibrary;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdLibraryMapper;


import com.jfeat.am.module.advertisement.services.crud.service.CRUDAdLibraryService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDAdLibraryService
 * @author Code Generator
 * @since 2018-12-13
 */

@Service
public class CRUDAdLibraryServiceImpl  extends CRUDServiceOnlyImpl<AdLibrary> implements CRUDAdLibraryService {





        @Resource
        private AdLibraryMapper adLibraryMapper;

        @Override
        protected BaseMapper<AdLibrary> getMasterMapper() {
                return adLibraryMapper;
        }







}


