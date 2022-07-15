package com.jfeat.module.feedback.api;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.smallsaas.ticket.api.FeedbackEndpoint;
import com.jfeat.module.smallsaas.ticket.api.request.CreateFeedBackRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api("反馈模块")
@RequestMapping("/api/u/user")
public class CloudFeedBackEndpoint {

    @Resource
    FeedbackEndpoint feedbackEndpoint;

    @ApiOperation(value = "用户进行反馈")
    @PostMapping("/feedback")
    public Tip feedback(@RequestBody CreateFeedBackRequest request) {
        if (JWTKit.getUserId() == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }
        request.setComplainantId(JWTKit.getUserId());
        return feedbackEndpoint.createFeedbackRecord(request);
    }

}
