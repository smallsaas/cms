package com.jfeat.am.module.advertisement.services.service;

import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
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

    public List<AdGroup> getAllAdGroup(String search);

    List<AdGroup> getCurrentAdGroup(Long orgId,String appid);


}
