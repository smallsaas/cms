package com.jfeat.module.feedback.services.service.impl;

import com.jfeat.module.feedback.services.service.NTFComplainTypeService;
import org.springframework.stereotype.Service;

@Service("NTFComplainTypeService")
public class NTFComplainTypeServiceImpl  extends NFTCRUDComplainTypeServiceImpl implements NTFComplainTypeService  {

    @Override
    protected String entityName() {
        return "ComplainType";
    }
}
