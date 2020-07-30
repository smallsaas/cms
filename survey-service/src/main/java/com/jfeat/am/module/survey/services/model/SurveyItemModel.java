package com.jfeat.am.module.survey.services.model;

import com.jfeat.am.module.survey.services.persistence.model.SurveyItem;
import com.jfeat.am.module.survey.services.persistence.model.SurveyItemOption;

import java.util.List;

public class SurveyItemModel extends SurveyItem {

    List<SurveyItemOption> itemOptions;


    public List<SurveyItemOption> getItemOptions() {
        return itemOptions;
    }

    public void setItemOptions(List<SurveyItemOption> itemOptions) {
        this.itemOptions = itemOptions;
    }
}
