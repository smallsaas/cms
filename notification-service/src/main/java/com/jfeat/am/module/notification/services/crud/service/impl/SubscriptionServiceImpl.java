package com.jfeat.am.module.notification.services.crud.service.impl;
        
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.notification.services.persistence.model.Subscription;
import com.jfeat.am.module.notification.services.persistence.dao.SubscriptionMapper;
import com.jfeat.am.module.notification.services.crud.service.SubscriptionService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
@Service
public class SubscriptionServiceImpl extends CRUDServiceOnlyImpl<Subscription> implements SubscriptionService {


    @Resource
    private SubscriptionMapper subscriptionMapper;

    @Override
    protected BaseMapper<Subscription> getMasterMapper() {
        return subscriptionMapper;
    }


    @Override
    public Boolean subscribe(Long userId, Long targetId, String targetType, List<String> actions) {
        for (String action : actions) {
//            先删除防止重复订阅
            subscriptionMapper.delete(new QueryWrapper<Subscription>().eq(Subscription.USER_ID, userId)
                    .eq(Subscription.TARGET_TYPE, targetType)
                    .eq(Subscription.TARGET_ID, targetId)
                    .eq(Subscription.ACTION, action));

            Subscription subscription = new Subscription();
            subscription.setUserId(userId);
            subscription.setTargetId(targetId);
            subscription.setTargetType(targetType);
            subscription.setAction(action);
            subscription.setCreatedAt(new Date());
            subscriptionMapper.insert(subscription);
        }
        return true;
    }

    @Override
    public Boolean unsubscribe(Long userId, Long targetId, String targetType) {
        return subscriptionMapper.delete(new QueryWrapper<Subscription>()
                .eq(Subscription.USER_ID, userId)
                .eq(Subscription.TARGET_ID, targetId)
                .eq(Subscription.TARGET_TYPE, targetType)) > 0;
    }
}


