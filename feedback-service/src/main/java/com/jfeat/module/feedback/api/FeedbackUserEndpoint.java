package com.jfeat.module.feedback.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.module.feedback.services.domain.dao.QueryFeedbackDao;
import com.jfeat.module.feedback.services.filter.FeedbackFilter;
import com.jfeat.module.feedback.services.gen.persistence.model.Feedback;
import com.jfeat.module.feedback.services.service.FeedbackService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */
@RestController
@Api("反馈建议")
@RequestMapping("/api/u")
public class FeedbackUserEndpoint {

    @Resource
    private FeedbackService feedbackService;

    @Resource
    private QueryFeedbackDao queryFeedbackDao;

    @ApiOperation("提交反馈或建议")
    @PostMapping("/feedback")
    public Tip commitFeedback(@Valid @RequestBody Feedback entity) {
        Long userId = JWTKit.getUserId();
        entity.setUserId(userId);
        entity.setUserName(JWTKit.getAccount());

        return SuccessTip.create(feedbackService.createMaster(entity, new FeedbackFilter()));
    }

    @ApiOperation("查看反馈或建议")
    @GetMapping("/feedback/{id}")
    public Tip getFeedback(@PathVariable Long id) {
        return SuccessTip.create(feedbackService.retrieveMaster(id));
    }

    @ApiOperation("反馈或建议列表")
    @GetMapping("/feedback")
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
