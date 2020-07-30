package com.jfeat.am.module.survey.services.crud.service;
import com.jfeat.am.module.survey.services.persistence.model.SurveyAnswer;
            import com.jfeat.crud.plus.CRUDServiceOnly;

/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-10-15
 * Master: ${cfg.masterModel}
  * Slave : t_survey_answer
  */
public interface CRUDSurveyAnswerService  extends CRUDServiceOnly<SurveyAnswer> {



    /**
     * is answered this survey
     *
     * */
    Integer isAnswered(Long userId,Long surveyId);

}
