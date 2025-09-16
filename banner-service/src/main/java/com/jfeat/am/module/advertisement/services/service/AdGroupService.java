package com.jfeat.am.module.advertisement.services.service;

import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import com.jfeat.am.module.advertisement.services.persistence.model.GroupDataItem;
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
public interface AdGroupService extends CRUDServiceOnly<AdGroup> {

    List<AdGroup> getAllAdGroup(String search);

    /**
     * 提供所有分组信息，包括分组下面的广告
     */
    List<GroupDataItem> getAllGroupData();

}
