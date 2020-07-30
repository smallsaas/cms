package com.jfeat.am.module.survey.services.model;

import com.jfeat.am.module.survey.services.persistence.model.Survey;

import java.util.List;

public class SurveyModel extends Survey {

    List<SurveyItemModel> surveyItems;

    public List<SurveyItemModel> getSurveyItems() {
        return surveyItems;
    }

    public void setSurveyItems(List<SurveyItemModel> surveyItems) {
        this.surveyItems = surveyItems;
    }
}
