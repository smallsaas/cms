package com.jfeat.am.module.survey.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.am.module.survey.services.domain.dao.QuerySurveyDao;
import com.jfeat.am.module.survey.services.persistence.model.SurveyAnswer;
import com.jfeat.am.module.survey.services.persistence.dao.SurveyAnswerMapper;


import com.jfeat.am.module.survey.services.crud.service.CRUDSurveyAnswerService;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * <p>
 * implementation
 * </p>
 * CRUDSurveyAnswerService
 *
 * @author Code Generator
 * @since 2018-10-15
 */

@Service
public class CRUDSurveyAnswerServiceImpl extends CRUDServiceOnlyImpl<SurveyAnswer> implements CRUDSurveyAnswerService {


    @Resource
    protected SurveyAnswerMapper surveyAnswerMapper;
    @Resource
    QuerySurveyDao querySurveyDao;

    @Override
    protected BaseMapper<SurveyAnswer> getMasterMapper() {
        return surveyAnswerMapper;
    }


    /**
     * is answered this survey
     *
     * */
    public Integer isAnswered(Long userId,Long surveyId){
        return surveyAnswerMapper.selectCount(new EntityWrapper<SurveyAnswer>()
                .eq(SurveyAnswer.AUTHOR_ID,userId)
                .eq(SurveyAnswer.SURVEY_ID,surveyId));
    }

}


