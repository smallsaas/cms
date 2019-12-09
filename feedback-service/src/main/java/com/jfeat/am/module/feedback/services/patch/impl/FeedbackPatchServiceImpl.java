package com.jfeat.am.module.feedback.services.patch.impl;


import com.jfeat.am.module.feedback.constant.FeedbackStatus;
import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import com.jfeat.am.module.feedback.services.patch.FeedbackPatchService;
import com.jfeat.am.module.feedback.services.persistence.dao.TFeedbackMapper;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Silent-Y on 2017/9/14.
 */
@Service
public class FeedbackPatchServiceImpl implements FeedbackPatchService {
    @Resource
    TFeedbackMapper tFeedbackMapper;

    public Integer solve(TFeedbackModel tFeedbackModel) {
        TFeedback tFeedback = tFeedbackMapper.selectById(tFeedbackModel.getId());
        tFeedback.setSolved(FeedbackStatus.SOLVED);
        tFeedback.setDealOpinion(tFeedbackModel.getDealOpinion());
        return tFeedbackMapper.updateById(tFeedback);
    }
}
