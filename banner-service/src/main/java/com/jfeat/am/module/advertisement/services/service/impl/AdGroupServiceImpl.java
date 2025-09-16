package com.jfeat.am.module.advertisement.services.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdGroupMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import com.jfeat.am.module.advertisement.services.persistence.model.GroupDataItem;
import com.jfeat.am.module.advertisement.services.service.AdGroupService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @Resource
    private AdGroupService adGroupService;

    @Override
    protected BaseMapper<AdGroup> getMasterMapper() {
        return adGroupMapper;
    }

    @Override
    public List<AdGroup> getAllAdGroup(String search){
        if(!StringUtils.isEmpty(search)){
            List<AdGroup> adGroups=adGroupMapper.selectList(
                    new QueryWrapper<AdGroup>()
                            .like("name",search)
                            .or()
                            .like("identifier",search)

            );
            return  adGroups;
        }else{
            List<AdGroup> adGroups=adGroupMapper.selectList( new QueryWrapper<AdGroup>());
            return  adGroups;
        }

    }

//    @Override
//    @Transactional
//    public List<AdGroup> getCurrentAdGroup(Long orgId,String appid) {
//        QueryWrapper queryWrapper = new QueryWrapper();
//        queryWrapper.eq(AdGroup.ORG_ID,orgId);
//        if (appid!=null&& !"".equals(appid)){
//            queryWrapper.eq(AdGroup.APPID,appid);
//        }
//        List<AdGroup> adGroupList = adGroupMapper.selectList(queryWrapper);
//        if (adGroupList==null || adGroupList.size()==0){
//            SysOrg sysOrg = tenantUtilsService.getTenantInfo(orgId);
//
//            String name = "";
//            if (sysOrg!=null && sysOrg.getName()!=null){
//                name= sysOrg.getName();
//            }
//
//            AdGroup adGroup = new AdGroup();
//            adGroup.setOrgId(orgId);
//            adGroup.setIdentifier("banner");
//            adGroup.setAppid(appid);
//            adGroup.setName(name.concat("首页轮播"));
//            adGroupService.createMaster(adGroup);
//            adGroupList.add(adGroup);
//        }
//        return adGroupList;
//    }



    @Override
    public List<GroupDataItem> getAllGroupData() {
        List<GroupDataItem> groups = new ArrayList<>();

        List<AdGroup> groupList = adGroupService.getAllAdGroup("");
        for (AdGroup adGroup : groupList){
            GroupDataItem item = new GroupDataItem();
            item.setId(adGroup.getId());
            item.setName(adGroup.getName());
            /*    item.setIdentifier(adGroup.getIdentifier());*/

            /*  item.setAds(adService.getAdRecordsByGroup(adGroup.getIdentifier()).getAds());*/

            groups.add(item);
        }

        return groups;
    }
}
