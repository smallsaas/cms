package com.jfeat.am.module.notification.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.notification.services.crud.service.NotifyService;
import com.jfeat.am.module.notification.services.domain.dao.QueryNotifyDao;
import com.jfeat.am.module.notification.services.domain.model.NotifyModel;
import com.jfeat.am.module.notification.services.persistence.dao.NotifyMapper;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
@Service
public class NotifyServiceImpl extends CRUDServiceOnlyImpl<Notify> implements NotifyService {


    @Resource
    private NotifyMapper notifyMapper;
    @Resource
    private QueryNotifyDao queryNotifyDao;

    @Override
    protected BaseMapper<Notify> getMasterMapper() {
        return notifyMapper;
    }

    @Override
    public List<NotifyModel> paginationNotifies(Page<NotifyModel> page, Long userId, List<String> targetTypes, Integer isRead) {
        List<NotifyModel> notifies = queryNotifyDao.paginationNotifies(page, userId, targetTypes, isRead);
        return notifies;
    }

    @Override
    public List<NotifyModel> searchNotifies(Page<NotifyModel> page, Long userId, List<String> actions) {
        List<NotifyModel> notifies = queryNotifyDao.searchNotifies(page, userId, actions);
        return notifies;
    }

}


