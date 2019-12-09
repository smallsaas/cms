package com.jfeat.am.module.termconfig.services.crud.service.impl;
            
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.termconfig.services.crud.service.TermConfigService;
import com.jfeat.am.module.termconfig.services.persistence.dao.TermConfigMapper;
import com.jfeat.am.module.termconfig.services.persistence.model.TermConfig;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  implementation
 * </p>
 *
 * @author Code Generator
 * @since 2017-12-09
 */
@Service
public class TermConfigServiceImpl  extends CRUDServiceOnlyImpl<TermConfig> implements TermConfigService {

    @Resource
    private TermConfigMapper termConfigMapper;

    @Override
    protected BaseMapper<TermConfig> getMasterMapper() {
        return termConfigMapper;
    }

    @Override
    public TermConfig getTermConfigByType(String type) {
        TermConfig termConfig = new TermConfig();
        termConfig.setType(type);
        return termConfigMapper.selectOne(termConfig);
    }

    @Override
    public List<TermConfig> getTermConfig() {

        List<TermConfig> termConfigs= termConfigMapper.selectList(new EntityWrapper<TermConfig>());

        return termConfigs;
    }
}


