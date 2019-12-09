package com.jfeat.am.module.advertisement.services.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdGroupMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import com.jfeat.am.module.advertisement.services.service.AdGroupService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-09-20
 */
@Service
public class AdGroupServiceImpl extends CRUDServiceOnlyImpl<AdGroup> implements AdGroupService {
    @Resource
    private AdGroupMapper adGroupMapper;

    @Override
    protected BaseMapper<AdGroup> getMasterMapper() {
        return adGroupMapper;
    }

    @Override
    public List<AdGroup> getAllAdGroup(String search){
        List<AdGroup> adGroups=adGroupMapper.selectList(new EntityWrapper<AdGroup>()
                .like("name",search).or()
                .like("identifier",search)

        );
        return  adGroups;
    };


}
