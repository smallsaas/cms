package com.jfeat.module.feedback.services.service.impl;
        
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import com.jfeat.module.feedback.constant.FeedbackStatus;
import com.jfeat.module.feedback.services.domain.dao.QueryFeedbackDao;
import com.jfeat.module.feedback.services.gen.persistence.dao.CMSFeedbackMapper;
import com.jfeat.module.feedback.services.gen.persistence.model.Feedback;
import com.jfeat.module.feedback.services.service.FeedbackService;
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
public class FeedbackServiceImpl extends CRUDServiceOnlyImpl<Feedback> implements FeedbackService {

    @Resource
    private CMSFeedbackMapper CMSFeedbackMapper;
    @Resource
    private QueryFeedbackDao queryFeedbackDao;

    private static final String ItemKeyName = "images";
    private static final String ItemFieldName = "feedback_id";

    @Override
    protected BaseMapper<Feedback> getMasterMapper() {
        return CMSFeedbackMapper;
    }


    public Integer solve(Long feedbackId, String note) {
        Feedback feedback = CMSFeedbackMapper.selectById(feedbackId);
        feedback.setStatus(FeedbackStatus.SOLVED);
        feedback.setSolvedNote(note);
        feedback.setSolvedBy(JWTKit.getAccount());
        return CMSFeedbackMapper.updateById(feedback);
    }
}


