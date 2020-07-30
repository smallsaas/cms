package com.jfeat.am.module.survey.services.crud.service;
            import com.jfeat.am.module.survey.services.model.SurveyItemModel;
            import com.jfeat.am.module.survey.services.persistence.model.SurveyItem;
            import com.jfeat.crud.plus.CRUDServiceOnly;
            import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  service interface
 * </p>
 *
 * @author Code Generator
 * @since 2018-10-15
 * Master: ${cfg.masterModel}
  * Slave : t_survey_item
  */
public interface CRUDSurveyItemService  extends CRUDServiceOnly<SurveyItem> {


    @Transactional
    public Integer addOneItemIntoSurvey(Long surVeyId, SurveyItemModel item);


    public Integer deleteItem(Long itemId);


    public Integer updateItem(Long itemId,SurveyItem item);

}
