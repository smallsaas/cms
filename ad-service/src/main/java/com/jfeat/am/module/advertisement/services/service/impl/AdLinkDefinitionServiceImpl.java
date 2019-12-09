package com.jfeat.am.module.advertisement.services.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.AdLinkDefinition;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdLinkDefinitionMapper;
import com.jfeat.am.module.advertisement.services.service.AdLinkDefinitionService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-09-20
 */
@Service
public class AdLinkDefinitionServiceImpl extends CRUDServiceOnlyImpl<AdLinkDefinition> implements AdLinkDefinitionService {


    @Resource
    private AdLinkDefinitionMapper adLinkDefinitionMapper;

    @Override
    protected BaseMapper<AdLinkDefinition> getMasterMapper() {
        return adLinkDefinitionMapper;
    }
}
