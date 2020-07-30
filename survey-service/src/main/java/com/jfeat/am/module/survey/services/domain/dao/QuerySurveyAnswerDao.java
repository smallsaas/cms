package com.jfeat.am.module.survey.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfeat.am.module.survey.services.model.SurveyAnswerRecord;
import com.jfeat.am.module.survey.services.persistence.model.SurveyAnswer;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-10-15
 */
public interface QuerySurveyAnswerDao extends BaseMapper<SurveyAnswerRecord> {
    List<SurveyAnswerRecord> findSurveyAnswerPage(Page<SurveyAnswerRecord> page, @Param("record") SurveyAnswerRecord record, @Param("orderBy") String orderBy);

    List<SurveyAnswerRecord> myAnswer(@Param("userId")Long userId);
}