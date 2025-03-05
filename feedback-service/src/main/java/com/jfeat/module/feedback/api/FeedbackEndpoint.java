package com.jfeat.module.feedback.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.feedback.constant.FeedbackRequest;
import com.jfeat.module.feedback.services.gen.persistence.model.FeedbackRequestType;
import com.jfeat.module.feedback.services.service.FeedbackService;
import com.jfeat.module.feedback.services.gen.persistence.model.Feedback;
import com.jfeat.module.feedback.services.domain.dao.QueryFeedbackDao;
import com.jfeat.module.feedback.services.filter.FeedbackFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;


@RestController
@Api("反馈模块")
@RequestMapping("/api/cms/feedback")
public class FeedbackEndpoint {
    private static Long USER_ID = 110L;
    private static String ACCOUNT = "dev";

    @Resource
    private FeedbackService feedbackService;

    @Resource
    private QueryFeedbackDao queryFeedbackDao;

    @ApiOperation(value = "终端用户进行反馈")
    @PostMapping("/{appid}/{type}")
    public Tip feedback(@RequestBody Feedback request, @PathVariable String type, @PathVariable("appid") String appid) {
        Long userId = JWTKit.getUserId();  // end-user id
        if (userId == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }
        String account = JWTKit.getAccount();
        // if(userId == null) {
        //     userId = USER_ID;
        // }
        // if(account == null) {
        //     account = ACCOUNT;
        // }


        Feedback feedback = new Feedback();
        feedback.setAppid(appid);
        feedback.setUserId(userId);
        feedback.setUserName(account);

        feedback.setFeedback(request.getFeedback());
        feedback.setRequestType(type);

        return SuccessTip.create(feedbackService.createMaster(feedback, new FeedbackFilter()));
    }

    @ApiOperation(value = "用户进行反馈")
    @PostMapping("/{appid}")
    public Tip feedbackType(@RequestBody FeedbackRequest request, @PathVariable("appid") String appid) {
        Long userId = JWTKit.getUserId();  // end-user id
        if (userId == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }
        String account = JWTKit.getAccount();
        // if(userId == null) {
        //     userId = USER_ID;
        // }
        // if(account == null) {
        //     account = ACCOUNT;
        // }


        Feedback feedback = new Feedback();
        feedback.setAppid(appid);
        feedback.setUserId(userId);
        feedback.setFeedback(request.getContent());
        feedback.setUserName(account);
        feedback.setRequestType(FeedbackRequestType.FEEDBACK.name());

        return SuccessTip.create(feedbackService.createMaster(feedback, new FeedbackFilter()));
    }

    @ApiOperation("提交反馈或建议")
    @PostMapping
    public Tip commitFeedback(@Valid @RequestBody FeedbackRequest request) {
        Long userId = JWTKit.getUserId();  // end-user id
        if (userId == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }
        String account = JWTKit.getAccount();
        String appid = JWTKit.getAppid();


        Feedback feedback = new Feedback();
        feedback.setAppid(appid);
        feedback.setUserId(userId);
        feedback.setFeedback(request.getContent());
        feedback.setUserName(account);
        feedback.setRequestType(FeedbackRequestType.FEEDBACK.name());

        return SuccessTip.create(feedbackService.createMaster(feedback, new FeedbackFilter()));
    }

    /**
     * 一般无需查看
     * @param id
     * @return
     */
    @ApiOperation("查看具体反馈或建议")
    @GetMapping("/{id}")
    public Tip getFeedback(@PathVariable Long id) {
        throw new BusinessException(BusinessCode.NoPermission);
        // return SuccessTip.create(feedbackService.retrieveMaster(id));
    }

    @ApiOperation("查看个人反馈或建议列表")
    @GetMapping
    public Tip queryFeedbacks(Page<Feedback> page,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                              @RequestParam(name = "status", required = false) String status,
                              @RequestParam(name = "appid", required = false) String appid,
                              @RequestParam(name = "type", required = false) String type,   // [FEEDBACK, COMPLAIN, PROPOSAL]
                              @RequestParam(name = "createName", required = false) String createName,
                              @RequestParam(name = "startTime", required = false) String startTime,
                              @RequestParam(name = "endTime", required = false) String endTime) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryFeedbackDao.findFeedbacks(page, appid, type, status, createName, startTime, endTime));
        
        return SuccessTip.create(page);
    }
}

