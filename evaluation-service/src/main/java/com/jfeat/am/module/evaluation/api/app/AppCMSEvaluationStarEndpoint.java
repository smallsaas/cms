package com.jfeat.am.module.evaluation.api.app;


import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.evaluation.services.domain.service.StockEvaluationService;
import com.jfeat.am.module.evaluation.services.domain.service.StockEvaluationStarService;
import com.jfeat.am.module.ff.services.domain.service.StockFlowerService;
import com.jfeat.am.module.ff.services.persistence.model.StockFlower;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import org.apache.ibatis.ognl.Evaluation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("cms-评价 点赞")
@RequestMapping("/api/cms/star")
public class AppCMSEvaluationStarEndpoint {

    @Resource
    StockEvaluationService stockEvaluationService;

    @Resource
    StockFlowerService stockFlowerService;



    @PostMapping
    public Tip floweredOrNot(@RequestBody StockFlower flower){

        Long userId = JWTKit.getUserId();

        if (userId==null){
            throw new BusinessException(BusinessCode.NoPermission,"没有登录");
        }


        flower.setMemberId(userId);
        flower.setStockType(stockEvaluationService.getEntityName());

        return SuccessTip.create(stockFlowerService.floweredOrNot(flower));
    }

}
