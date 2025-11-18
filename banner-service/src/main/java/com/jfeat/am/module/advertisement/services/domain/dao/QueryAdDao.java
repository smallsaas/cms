package com.jfeat.am.module.advertisement.services.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.am.module.advertisement.services.domain.model.smallProductRequest;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2018-12-13
 */
public interface QueryAdDao extends BaseMapper<Ad> {
    List<AdRecord> findAdPage(Page<AdRecord> page,
                              @Param("record") AdRecord record,
                              @Param("orderBy") String orderBy,
                              @Param("search") String search);
    List<AdGroup> findAdGroupPage(Page<AdGroup> page,
                              @Param("record") AdGroup record,
                              @Param("orderBy") String orderBy,
                              @Param("search") String search);


    List<AdRecord> findAdPageByAppid(Page<AdRecord> page,
                                     @Param("record") AdRecord record,
                                     @Param("orderBy") String orderBy,
                                     @Param("search") String search,
                                     @Param("appid")String appid);

    AdRecord findAd( @Param("id") Long id);

    List<smallProductRequest> getProductIdName();
    List<smallProductRequest> getCategoryIdName();


}