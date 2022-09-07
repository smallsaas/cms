package com.jfeat.am.module.icon.services.domain.filter;

import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.am.module.icon.services.gen.persistence.model.FrontIcon;


/**
 * Created by Code generator on 2022-09-02
 */
public class FrontIconFilter implements CRUDFilter<FrontIcon> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(FrontIcon entity, boolean insertOrUpdate) {

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
