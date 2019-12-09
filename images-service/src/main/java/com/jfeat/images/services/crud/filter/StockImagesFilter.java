package com.jfeat.images.services.crud.filter;

import com.jfeat.crud.plus.CRUDFilter;
import com.jfeat.images.services.persistence.model.StockImages;


/**
 * Created by Code Generator on 2018-08-07
 */
public class StockImagesFilter implements CRUDFilter<StockImages> {

    private String[] ignoreFields = new String[]{};
    private String[] updateIgnoreFields = new String[]{};

    @Override
    public void filter(StockImages entity, boolean insertOrUpdate) {

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
