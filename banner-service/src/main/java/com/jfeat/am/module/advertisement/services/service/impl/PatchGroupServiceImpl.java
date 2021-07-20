package com.jfeat.am.module.advertisement.services.service.impl;

import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import com.jfeat.am.module.advertisement.services.persistence.model.GroupDataItem;
import com.jfeat.am.module.advertisement.services.service.AdGroupService;
import com.jfeat.am.module.advertisement.services.service.AdService;
import com.jfeat.am.module.advertisement.services.service.PatchGroupService;
import org.springframework.stereotype.Service;

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
public class PatchGroupServiceImpl implements PatchGroupService {

    @Resource
    private AdGroupService adGroupService;

    @Resource
    private AdService adService;

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
