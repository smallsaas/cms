package com.jfeat.am.module.meta.services.domain.service.impl;

import com.jfeat.am.module.meta.services.domain.dao.QueryMetaWorkflowLiteActivityDao;
import com.jfeat.am.module.meta.services.domain.service.MetaWorkflowLiteActivityService;
import com.jfeat.am.module.meta.services.gen.crud.service.impl.CRUDMetaWorkflowLiteActivityServiceImpl;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaWorkflowLiteActivity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("metaWorkflowLiteActivityService")
public class MetaWorkflowLiteActivityServiceImpl extends CRUDMetaWorkflowLiteActivityServiceImpl
        implements MetaWorkflowLiteActivityService {

    @Resource
    private QueryMetaWorkflowLiteActivityDao queryMetaWorkflowLiteActivityDao;

    @Override
    public List<MetaWorkflowLiteActivity> getListByEntity(String entity, Long entityId) {
        return queryMetaWorkflowLiteActivityDao.getListByEntity(entity, entityId);
    }
}
