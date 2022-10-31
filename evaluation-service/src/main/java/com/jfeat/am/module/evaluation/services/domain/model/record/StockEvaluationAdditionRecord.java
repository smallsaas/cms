package com.jfeat.am.module.evaluation.services.domain.model.record;

import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationAddition;

/**
 * Created by Code Generator on 2018-07-16
 */
public class StockEvaluationAdditionRecord extends StockEvaluationAddition{
    private String userName;
    private String avatar;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
