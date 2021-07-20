package com.jfeat.am.module.feedback.services.domain.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedbackImage;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface QueryTFeedbackImageDao  extends BaseMapper<TFeedbackImage> {

    List<TFeedbackImage> findTFeedbackImages(Page<TFeedbackImage> page,
                                             @Param("status") String status);

}