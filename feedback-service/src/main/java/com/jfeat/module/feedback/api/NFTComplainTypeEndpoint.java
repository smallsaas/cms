package com.jfeat.module.feedback.api;

import com.jfeat.am.common.annotation.Permission;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.feedback.services.gen.persistence.model.ComplainType;
import com.jfeat.module.feedback.services.service.NTFComplainTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("来自nft的 意见模块")
@RequestMapping("/api/crud/oms/complainType/complainTypes")
public class NFTComplainTypeEndpoint {


    @Resource
    NTFComplainTypeService ntfComplainTypeService;


    @BusinessLog(name = "ComplainType", value = "create ComplainType")
    @PostMapping
    @ApiOperation(value = "新建 ComplainType", response = ComplainType.class)
    public Tip createComplainType(@RequestBody ComplainType entity) {
        Integer affected = 0;
        try {
            affected = ntfComplainTypeService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

}
