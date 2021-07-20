package com.jfeat.am.module.advertisement.services.service;

import com.jfeat.am.module.advertisement.services.crud.service.CRUDAdLibraryService;
import com.jfeat.crud.base.request.Ids;

/**
 * Created by vincent on 2017/10/19.
 */
public interface AdLibraryService extends CRUDAdLibraryService{
        Integer bulkDelete(Ids ids);
}