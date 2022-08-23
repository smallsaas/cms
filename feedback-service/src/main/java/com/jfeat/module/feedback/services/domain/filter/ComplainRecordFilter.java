package com.jfeat.am.module.feedback.services.domain.filter;

import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.module.feedback.services.gen.persistence.model.ComplainRecord;


/**
 * Created by Code generator on 2022-08-12
 */
public class ComplainRecordFilter implements CRUDFilter<ComplainRecord> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(ComplainRecord entity, boolean insertOrUpdate) {

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
