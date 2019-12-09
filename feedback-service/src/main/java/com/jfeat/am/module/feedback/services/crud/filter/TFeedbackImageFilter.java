package com.jfeat.am.module.feedback.services.crud.filter;

import com.jfeat.am.module.feedback.services.persistence.model.TFeedbackImage;
import com.jfeat.crud.plus.CRUDFilter;


/**
 * Created by SB-Code-Generator on 2017/9/14.
 */
public class TFeedbackImageFilter implements CRUDFilter<TFeedbackImage> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(TFeedbackImage entity, boolean insertOrUpdate) {

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
