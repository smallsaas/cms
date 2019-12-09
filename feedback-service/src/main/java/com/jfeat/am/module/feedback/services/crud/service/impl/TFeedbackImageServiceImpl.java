package com.jfeat.am.module.feedback.services.crud.service.impl;
        
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedbackImage;
import com.jfeat.am.module.feedback.services.persistence.dao.TFeedbackImageMapper;
import com.jfeat.am.module.feedback.services.crud.service.TFeedbackImageService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */
@Service
public class TFeedbackImageServiceImpl  extends CRUDServiceOnlyImpl<TFeedbackImage> implements TFeedbackImageService {


    @Resource
    private TFeedbackImageMapper tFeedbackImageMapper;

    @Override
    protected BaseMapper<TFeedbackImage> getMasterMapper() {
        return tFeedbackImageMapper;
    }
}


