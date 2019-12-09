package com.jfeat.am.module.meta.services.domain.filter;

import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaWorkflowLiteActivity;


/**
 * Created by Code Generator on 2019-01-29
 */
public class MetaWorkflowLiteActivityFilter implements CRUDFilter<MetaWorkflowLiteActivity> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(MetaWorkflowLiteActivity entity, boolean insertOrUpdate) {

        //if insertOrUpdate is true,means for insert, do this
        if (insertOrUpdate) {

            //then insertOrUpdate is false,means for update,do this
        } else {

        }

    }

    @Override
    public String[] ignore(boolean retrieveOrUpdate) {
        //if retrieveOrUpdate is true,means for retrieve ,do this
        if (retrieveOrUpdate) {
            return ignoreFields;
            //then retrieveOrUpdate  if false ,means for update,do this
        } else {
            return updateIgnoreFields;
        }
    }
}
