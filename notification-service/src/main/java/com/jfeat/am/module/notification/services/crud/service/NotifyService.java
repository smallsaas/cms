package com.jfeat.am.module.notification.services.crud.service;
        
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.notification.services.domain.model.NotifyModel;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
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



public interface NotifyService extends CRUDServiceOnly<Notify> {

    List<NotifyModel> paginationNotifies(Page<NotifyModel> page, Long userId, List<String> targetTypes, Integer isRead);

    List<NotifyModel> searchNotifies(Page<NotifyModel> page, Long userId, List<String> actions);
}

