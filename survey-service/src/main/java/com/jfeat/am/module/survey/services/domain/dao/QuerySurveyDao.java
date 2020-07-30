package com.jfeat.am.module.survey.services.domain.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.survey.services.persistence.model.Survey;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface QuerySurveyDao {

    List<Survey> findSurveyPage(Page<Survey> page,
                                @Param("record") Survey record,
                                @Param("orderBy") String orderBy);

    Integer updateEnabled(@Param("type")String type);

    Survey enableSurvey();
}
