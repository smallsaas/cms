package com.jfeat.am.module.feedback.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedbackImage;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryTFeedbackImageDao  extends BaseMapper<TFeedbackImage> {

    List<TFeedbackImage> findTFeedbackImages(Page<TFeedbackImage> page,
            @Param("status") String status);

}