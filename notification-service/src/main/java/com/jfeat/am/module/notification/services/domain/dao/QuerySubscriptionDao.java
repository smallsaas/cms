package com.jfeat.am.module.notification.services.domain.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.notification.services.persistence.model.Subscription;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QuerySubscriptionDao  extends BaseMapper<Subscription> {

    List<Subscription> findSubscriptions(Page<Subscription> page, @Param("status") String status);

}