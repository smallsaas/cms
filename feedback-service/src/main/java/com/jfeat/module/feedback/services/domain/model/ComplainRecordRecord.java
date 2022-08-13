package com.jfeat.module.feedback.services.domain.model;


import com.jfeat.module.feedback.services.gen.persistence.model.ComplainRecord;

/**
 * Created by Code generator on 2022-08-12
 */
public class ComplainRecordRecord extends ComplainRecord {
    private String userName;
    private String userPhone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
