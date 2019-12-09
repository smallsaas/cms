package com.jfeat.am.module.advertisement.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdLibraryRecord;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.am.module.advertisement.services.domain.model.smallProductRequest;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
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

    AdRecord findAd( @Param("id") Long id);

    List<smallProductRequest> getProductIdName();
    List<smallProductRequest> getCategoryIdName();


}