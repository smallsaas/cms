package com.jfeat.am.module.survey.services.crud.service;
            import com.jfeat.am.module.survey.services.model.SurveyModel;
            import com.jfeat.am.module.survey.services.persistence.model.Survey;
            import com.jfeat.crud.plus.CRUDServiceOnly;
            import org.springframework.transaction.annotation.Transactional;

            import java.util.List;

/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-10-15
 * Master: ${cfg.masterModel}
  * Slave : t_survey
  */
public interface CRUDSurveyService  extends CRUDServiceOnly<Survey> {



    @Transactional
    public SurveyModel createSurvey(SurveyModel model);


    public SurveyModel showSurvey(Long id,boolean result,String type);


    @Transactional
    public Integer deleteSurvey(Long id);


    @Transactional
    public Integer updateSurvey(Long id, SurveyModel model);


    /**
     *
     * 设定某个问卷为默认问卷 for C / IPAD 端用户
     * */
    @Transactional
    Integer setDefaultSurvey(Long surveyId);

    /**
     *  查看C端默认问卷
     * */
    SurveyModel showDefaultSurveyForCustomer(String type);

    /**
     * 批量删除问卷
     */
    Integer bulkDel(List<Long> ids);


}
