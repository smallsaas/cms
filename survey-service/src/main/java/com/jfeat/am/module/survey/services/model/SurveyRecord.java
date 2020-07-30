package com.jfeat.am.module.survey.services.model;

import com.jfeat.am.module.survey.services.persistence.model.Survey;

public class SurveyRecord extends Survey {

    Integer finishedCount;

    public Integer getFinishedCount() {
        return finishedCount;
    }

    public void setFinishedCount(Integer finishedCount) {
        this.finishedCount = finishedCount;
    }
}
