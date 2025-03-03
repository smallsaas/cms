package com.jfeat.module.feedback.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.feedback.services.service.FeedbackService;
import com.jfeat.module.feedback.services.gen.persistence.model.Feedback;
import com.jfeat.module.feedback.services.domain.dao.QueryFeedbackDao;
import com.jfeat.module.feedback.services.filter.FeedbackFilter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.Date;
import java.util.List;

@RestController
@Api("反馈模块")
@RequestMapping("/api/cms/feedback")
public class FeedbackEndpoint {

    @Resource
    private FeedbackService feedbackService;

    @Resource
    private QueryFeedbackDao queryFeedbackDao;


    @ApiOperation(value = "终端用户进行反馈")
    @PostMapping("/{appid}/{type}")
    public Tip feedback(@RequestBody Feedback request, @PathVariable String type, @PathVariable("appid") String appid) {
        if (JWTKit.getUserId() == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }
        request.setAppid(appid);
        request.setRequestType(type);

        return SuccessTip.create(feedbackService.createMaster(request));
    }

/*    @ApiOperation(value = "用户进行反馈")
    @PostMapping("/feedback/{appid}")
    public Tip feedbackType(@RequestBody ComplainRecord request) {
        if (JWTKit.getUserId() == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }
        request.setComplainantId(JWTKit.getUserId());
        request.setRequestType(FBComplainRequestType.FEEDBACK.name());
        int insert = complainRecordMapper.insert(request);
        return SuccessTip.create(insert);
    }*/

    @ApiOperation("提交反馈或建议")
    @PostMapping
    public Tip commitFeedback(@Valid @RequestBody Feedback entity) {
        Long userId = JWTKit.getUserId();
        entity.setUserId(userId);
        entity.setUserName(JWTKit.getAccount());

        return SuccessTip.create(feedbackService.createMaster(entity, new FeedbackFilter()));
    }

    @ApiOperation("查看反馈或建议")
    @GetMapping("/{id}")
    public Tip getFeedback(@PathVariable Long id) {
        return SuccessTip.create(feedbackService.retrieveMaster(id));
    }

    @ApiOperation("反馈或建议列表")
    @GetMapping
    public Tip queryFeedbacks(Page page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                              @RequestParam(name = "status", required = false) String status,
                              @RequestParam(name = "createName", required = false) String createName,
                              @RequestParam(name = "startTime", required = false) String startTime,
                              @RequestParam(name = "endTime", required = false) String endTime) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryFeedbackDao.findFeedbacks(page, status, createName, startTime, endTime));
        return SuccessTip.create(page);
    }

}
