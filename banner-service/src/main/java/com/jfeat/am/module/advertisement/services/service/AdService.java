package com.jfeat.am.module.advertisement.services.service;

import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroupedModel;
import com.jfeat.crud.plus.CRUDServiceOnly;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2017-09-20
 */
public interface AdService extends CRUDServiceOnly<Ad> {
    Integer createMaster(Ad ad);
    Integer updateMaster(Ad ad);

    /**
     * 依据分组名称提供 轮播图
     * @param group group_name
     * @return
     */
    AdGroupedModel getAdRecordsByGroup(String group);


    /**
     * 依据组ID为前端提供 轮播图
     * @param groupId
     * @return
     */
    AdGroupedModel getAdRecordsByGroupId(Long groupId);

    /**
     * 
     * @param record
     * @return
     */
    List<Ad> getAdList(Ad record);
}

