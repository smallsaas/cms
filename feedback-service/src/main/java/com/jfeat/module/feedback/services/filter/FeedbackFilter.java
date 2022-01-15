package com.jfeat.module.feedback.services.filter;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.crud.plus.CRUDFilterResult;
import com.jfeat.module.feedback.services.gen.persistence.model.Feedback;

import java.util.Date;


/**
 * Created by SB-Code-Generator on 2017/9/14.
 */
public class FeedbackFilter implements CRUDFilterResult<Feedback> {

    private String[] ignoreFields = new String[]{Feedback.SOLVED_BY, Feedback.CREATE_TIME, Feedback.ID, Feedback.SOLVED_USER_ID, Feedback.SOLVED_NOTE};
    private String[] updateIgnoreFields = new String[]{Feedback.STATUS, Feedback.CREATE_TIME};

    @Override
    public JSONObject result() {
        return new JSONObject();
    }

    @Override
    public void filter(Feedback entity, boolean insertOrUpdate) {

        //if insertOrUpdate is true,means for insert, do this
        if (insertOrUpdate){
            //then insertOrUpdate is false,means for update,do this
        }else {

        }
    }

    @Override
    public String[] ignore(boolean retrieveOrUpdate) {
        //if retrieveOrUpdate is true,means for retrieve ,do this
        if (retrieveOrUpdate){
            return ignoreFields;
            //then retrieveOrUpdate  if false ,means for update,do this
        }else {
            return updateIgnoreFields;
        }
    }
}
