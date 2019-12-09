package com.jfeat.am.module.advertisement.services.service;

import com.jfeat.am.module.advertisement.services.persistence.model.GroupDataItem;

import java.util.List;

/**
 * Created by vincent on 2017/11/8.
 */
public interface PatchGroupService {

    /**
     * 提供所有分组信息，包括分组下面的广告
     */
    List<GroupDataItem> getAllGroupData();
}
