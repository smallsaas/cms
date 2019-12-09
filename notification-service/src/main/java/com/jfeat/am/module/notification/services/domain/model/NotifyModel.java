package com.jfeat.am.module.notification.services.domain.model;

import com.jfeat.am.module.notification.services.persistence.model.Notify;

/**
 * Created by vincent on 2017/8/27.
 */
public class NotifyModel extends Notify {

    private Long userNotifyId;
    private Integer isRead;

    public Long getUserNotifyId() {
        return userNotifyId;
    }

    public void setUserNotifyId(Long userNotifyId) {
        this.userNotifyId = userNotifyId;
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }
}
