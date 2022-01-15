package com.jfeat.module.feedback.services.service;

import com.jfeat.crud.plus.CRUDServiceOnly;
import com.jfeat.module.feedback.services.gen.persistence.model.Feedback;


/**
 * <p>
 * 服务类
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */

public interface FeedbackService extends CRUDServiceOnly<Feedback> {
    Integer solve(Long id, String note);
}
