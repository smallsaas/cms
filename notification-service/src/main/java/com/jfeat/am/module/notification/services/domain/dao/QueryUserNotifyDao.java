package com.jfeat.am.module.notification.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.notification.services.persistence.model.UserNotify;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QueryUserNotifyDao extends BaseMapper<UserNotify> {

    List<Map<String,Object>> queryTypeCount(@Param("userId") Long userId, @Param("isRead") Integer isRead);

    List<Map<String,Object>> returnTypeCount(@Param("userId") Long userId, @Param("isRead") Integer isRead);

    Integer read(@Param("id") Long id);
}