package com.jfeat.am.module.advertisement.services.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdLibraryDao;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdLibraryMapper;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroupedModel;
import com.jfeat.am.module.advertisement.services.persistence.model.AdLibrary;
import com.jfeat.am.module.advertisement.services.service.AdService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
public class AdServiceImpl extends CRUDServiceOnlyImpl<Ad> implements AdService {
    @Resource
    private AdMapper adMapper;
    @Resource
    private AdLibraryMapper adLibraryMapper;
    @Resource
    private QueryAdLibraryDao queryAdLibraryDao;
    @Resource
    private QueryAdDao queryAdDao;

    @Override
    protected BaseMapper<Ad> getMasterMapper() {
        return adMapper;
    }

    private Integer updateAdLibrary(String url) {
        if(url != null && !"".equals(url)) {
            AdLibrary adLibrary = new AdLibrary();
            adLibrary.setUrl(url);
            if(adLibraryMapper.selectOne(new LambdaQueryWrapper<>(adLibrary)) == null) {
                return adLibraryMapper.insert(adLibrary);
            }
        }
        return 0;
    }

    @Override
    @Transactional
    public Integer createMaster(Ad ad) {
        Integer affected = 0;
        affected += updateAdLibrary(ad.getImage());
        affected += super.createMaster(ad);
        return affected;
    }

    @Override
    @Transactional
    public Integer updateMaster(Ad ad) {
        Integer affected = 0;
        affected += updateAdLibrary(ad.getImage());
        affected += super.updateMaster(ad);
        return affected;
    }

    @Override
    public AdGroupedModel getAdRecordsByGroup(String group,String appid) {
        return getAdRecordsByGroup(group, appid,1);
    }

    @Override
    public AdGroupedModel getAdRecordsByGroup(String group) {
        return getAdRecordsByGroup(group, null,1);
    }

    @Override
    public List<Ad> getAdList(Ad record) {
        String groupId=null;
        String enabled=null;
        if(record.getEnabled()!=null){
            enabled=record.getEnabled().toString();
        }
        if(record.getGroupId()!=null){
            groupId=record.getGroupId().toString();
        }

        List<Ad> adList=adMapper.selectList(new QueryWrapper<Ad>()
                .like("name",record.getName())
                .like("group_id",groupId)
                .like("enabled",enabled)
        );
        return adList;
    }

    @Override
    public AdRecord getAdRecord(Long id) {
        AdRecord adRecord= queryAdDao.findAd(id);
        String str=adRecord.getStrategy();
        String[] strArr=null;
        if(str!=null && str.trim().length()>0){
            strArr = str.split("\\&");
        }

        adRecord.setStrategyArray(strArr);
        return adRecord;
    }

    @Override
    public AdGroupedModel getAdRecordsByGroup(String group, String appid,Integer enabled) {
        /// group means group identifier
        List<Ad> records= queryAdLibraryDao.getAdRecordsByGroup(group,appid, enabled);

        AdGroupedModel model = new AdGroupedModel();
        model.setAds(records);
        model.setGroup(group);

        return model;
    }
}
