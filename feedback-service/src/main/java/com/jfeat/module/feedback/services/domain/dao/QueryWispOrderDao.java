package com.jfeat.module.feedback.services.domain.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.plus.QueryMasterDao;

import com.jfeat.module.feedback.services.gen.persistence.model.ComplainType;
import com.jfeat.module.feedback.services.gen.persistence.model.WispOrder;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

public interface QueryWispOrderDao extends QueryMasterDao<WispOrder> {

    @Select("SELECT * from nft_complain_type where nft_complain_type.request_type = #{type}")
    List<ComplainType> queryComplainType(@Param("type") String type);
}