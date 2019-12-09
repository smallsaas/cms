package com.jfeat.am.module.notification.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.notification.services.persistence.model.Subscription;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QuerySubscriptionDao  extends BaseMapper<Subscription> {

    List<Subscription> findSubscriptions(Page<Subscription> page, @Param("status") String status);

}