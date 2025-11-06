package com.jfeat.am.module.icon.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.icon.services.gen.persistence.model.FrontIcon;
import com.jfeat.am.module.icon.services.gen.persistence.dao.FrontIconMapper;
import com.jfeat.am.module.icon.services.gen.crud.service.CRUDFrontIconService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDFrontIconService
 * @author Code generator
 * @since 2022-09-02
 */

@Service
public class CRUDFrontIconServiceImpl  extends CRUDServiceOnlyImpl<FrontIcon> implements CRUDFrontIconService {





        @Resource
        protected FrontIconMapper frontIconMapper;

        @Override
        protected BaseMapper<FrontIcon> getMasterMapper() {
                return frontIconMapper;
        }







}


