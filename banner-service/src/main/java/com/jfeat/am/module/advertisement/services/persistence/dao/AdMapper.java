package com.jfeat.am.module.advertisement.services.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-08
 */
public interface AdMapper extends BaseMapper<Ad> {
    @Deprecated
    @Select("select a.* from t_ad a LEFT JOIN t_ad_group g ON g.id=a.group_id WHERE g.identifier=#{group} and a.enabled > 0")
    List<Ad> getAdRecordsByGroup(String group);

}