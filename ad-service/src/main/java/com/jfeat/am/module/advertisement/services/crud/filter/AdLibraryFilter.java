package com.jfeat.am.module.advertisement.services.crud.filter;

import com.jfeat.am.module.advertisement.services.persistence.model.AdLibrary;
import com.jfeat.crud.plus.CRUDFilter;


/**
 * Created by Code Generator on 2018-12-13
 */
public class AdLibraryFilter implements CRUDFilter<AdLibrary> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(AdLibrary entity, boolean insertOrUpdate) {

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
