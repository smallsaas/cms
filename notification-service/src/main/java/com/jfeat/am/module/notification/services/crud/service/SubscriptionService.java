package com.jfeat.am.module.notification.services.crud.service;
        
import com.jfeat.am.module.notification.services.persistence.model.Subscription;
import com.jfeat.crud.plus.CRUDServiceOnly;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */



public interface SubscriptionService extends CRUDServiceOnly<Subscription> {

    //    创建订阅
    Boolean subscribe(Long userId, Long targetId, String targetType, List<String> actions);

    Boolean unsubscribe(Long userId, Long targetId, String targetType);

}
