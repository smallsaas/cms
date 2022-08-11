package com.jfeat.module.feedback.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.feedback.services.gen.persistence.dao.CMSFeedbackMapper;
import com.jfeat.module.smallsaas.ticket.api.FeedbackEndpoint;
import com.jfeat.module.smallsaas.ticket.api.request.CreateFeedBackRequest;
import com.jfeat.module.smallsaas.ticket.services.domain.dao.QueryComplainRecordDao;
import com.jfeat.module.smallsaas.ticket.services.gen.persistence.dao.ComplainRecordMapper;
import com.jfeat.module.smallsaas.ticket.services.gen.persistence.model.complainrecord.ComplainRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Api("反馈模块")
@RequestMapping("/api/u/user")
public class CloudFeedBackEndpoint {

    @Resource
    FeedbackEndpoint feedbackEndpoint;


    @Resource
    QueryComplainRecordDao queryComplainRecordDao;

    @ApiOperation(value = "用户进行反馈")
    @PostMapping("/feedback")
    public Tip feedbackPage(@RequestBody CreateFeedBackRequest request) {
        if (JWTKit.getUserId() == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }
        request.setComplainantId(JWTKit.getUserId());
        return feedbackEndpoint.createFeedbackRecord(request);
    }

    @ApiOperation(value = "反馈列表")
    @GetMapping("/feedback")
    public Tip feedback(Page page,
                        @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                        @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryComplainRecordDao.queryComplainRecordPage(page,null));

        return SuccessTip.create(page);
    }

}
