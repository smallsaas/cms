package com.jfeat.am.module.feedback.services.domain.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import java.util.Map;

public interface QueryTFeedbackDao  extends BaseMapper<TFeedback> {

    List<TFeedbackModel> findTFeedbacks(Page<TFeedbackModel> page,
                                        @Param("unread") Integer unread,
                                        @Param("createName") String createName,
                                        @Param("startTime") String startTime,
                                        @Param("endTime") String endTime);

}