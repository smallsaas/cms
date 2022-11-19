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
    /**
     * 依据组标识为前端提供 广告组
     * @param group
     * @return
     */
    AdGroupedModel getAdRecordsByGroup(String group, String appid,Integer enabled);

    Integer createMaster(Ad ad);

    Integer updateMaster(Ad ad);
    AdGroupedModel getAdRecordsByGroup(String group,String appid);

    AdGroupedModel getAdRecordsByGroup(String group);


    List<Ad> getAdList(Ad record);

    AdRecord getAdRecord(Long id);
}

