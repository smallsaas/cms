package com.jfeat.am.module.feedback.services.crud.filter;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;
import com.jfeat.crud.plus.CRUDFilterResult;

import java.util.Date;


/**
 * Created by SB-Code-Generator on 2017/9/14.
 */
public class TFeedbackFilter implements CRUDFilterResult<TFeedback> {

    private String[] ignoreFields = new String[]{TFeedback.UNREAD,TFeedback.CREATED_DATE,TFeedback.ID,TFeedback.DEAL_USER_ID,TFeedback.DEAL_OPINION};
    private String[] updateIgnoreFields = new String[]{TFeedback.UNREAD,TFeedback.CREATED_DATE};

    @Override
    public JSONObject result() {
        return new JSONObject();
    }

    @Override
    public void filter(TFeedback entity, boolean insertOrUpdate) {

        //if insertOrUpdate is true,means for insert, do this
        if (insertOrUpdate){
                entity.setUnread(0);
            entity.setCreatedDate(new Date());
            entity.setDealUserId(null);
            entity.setDealOpinion(null);
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
