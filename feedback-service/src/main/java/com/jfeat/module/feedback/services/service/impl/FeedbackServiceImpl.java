package com.jfeat.module.feedback.services.service.impl;
        
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import com.jfeat.module.feedback.constant.FeedbackStatus;
import com.jfeat.module.feedback.services.domain.dao.QueryFeedbackDao;
import com.jfeat.module.feedback.services.domain.model.FeedbackModel;
import com.jfeat.module.feedback.services.gen.persistence.dao.FeedbackMapper;
import com.jfeat.module.feedback.services.gen.persistence.model.Feedback;
import com.jfeat.module.feedback.services.service.FeedbackService;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */
@Service
public class FeedbackServiceImpl extends CRUDServiceOnlyImpl<Feedback> implements FeedbackService {

    @Resource
    private FeedbackMapper feedbackMapper;
    @Resource
    private QueryFeedbackDao queryFeedbackDao;

    private static final String ItemKeyName = "images";
    private static final String ItemFieldName = "feedback_id";

    @Override
    protected BaseMapper<Feedback> getMasterMapper() {
        return feedbackMapper;
    }


    public Integer solve(Long feedbackId, String note) {
        Feedback feedback = feedbackMapper.selectById(feedbackId);
        feedback.setStatus(FeedbackStatus.SOLVED);
        feedback.setSolvedBy(JWTKit.getAccount());
        return feedbackMapper.updateById(feedback);
    }
}


