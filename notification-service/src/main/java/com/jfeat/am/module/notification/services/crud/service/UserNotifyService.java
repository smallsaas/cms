package com.jfeat.am.module.notification.services.crud.service;
        
import com.jfeat.am.module.notification.services.persistence.model.Notify;
import com.jfeat.am.module.notification.services.persistence.model.UserNotify;
import com.jfeat.crud.plus.CRUDServiceOnly;

import java.util.List;
import java.util.Map;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */



public interface UserNotifyService extends CRUDServiceOnly<UserNotify> {

    List<Map<String,Object>> queryTypeCount(Long userId, Integer isRead);

    List<Map<String,Object>> returnTypeCount(Long userId, Integer isRead);

    Integer read(Long id);

    /**
     * 创建提醒
     * targetId,targetType,action,senderId,content,originId,originType,sourceId,sourceType都为必填参数
     * */
    Boolean createRemind(Notify notify);

    // 返回提醒
    List<UserNotify> pullRemind(Long userId);
}
