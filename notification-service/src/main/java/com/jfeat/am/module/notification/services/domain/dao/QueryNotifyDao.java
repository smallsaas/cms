package com.jfeat.am.module.notification.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.notification.services.domain.model.NotifyModel;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface QueryNotifyDao extends BaseMapper<Notify> {

    List<NotifyModel> paginationNotifies(Page<NotifyModel> page,
                                         @Param("userId") Long userId,
                                         @Param("targetTypes") List<String> targetTypes,
                                         @Param("isRead") Integer isRead);

    List<NotifyModel> searchNotifies(Page<NotifyModel> page,
                                         @Param("userId") Long userId,
                                         @Param("actions") List<String> actions);

    List<Notify> queryNotifies(@Param("targetId") Long targetId,
                             @Param("targetType") String targetType,
                             @Param("action") String action,
                             @Param("createAt") Date createAt);
}