package com.jfeat.module.feedback.services.domain.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.module.feedback.services.gen.persistence.model.Feedback;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryFeedbackDao extends BaseMapper<Feedback> {

    List<Feedback> findFeedbacks(Page<Feedback> page,
                                       @Param("appid") String appid,
                                       @Param("type") String type,
                                       @Param("status") String status,
                                       @Param("createName") String createName,
                                       @Param("startTime") String startTime,
                                       @Param("endTime") String endTime);
}
