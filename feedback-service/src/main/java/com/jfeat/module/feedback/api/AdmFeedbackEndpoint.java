package com.jfeat.module.feedback.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.feedback.services.domain.dao.QueryFeedbackDao;
import com.jfeat.module.feedback.services.gen.persistence.dao.FeedbackMapper;
import com.jfeat.module.feedback.services.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
@RequestMapping("/api/adm/cms/feedback")
public class AdmFeedbackEndpoint {

    @Resource
    private FeedbackService feedbackService;

    @Resource
    private QueryFeedbackDao queryFeedbackDao;


    @ApiOperation("处理(解决)反馈或建议")
    @PutMapping("/{id}/solve")
    public Tip solve(@PathVariable Long id, @RequestBody String note) {
        Integer result = feedbackService.solve(id, note);
        return SuccessTip.create(result);
    }

//    @ApiOperation("提交反馈或建议")
//    @PostMapping("/feedback")
//    public Tip createTFeedback(@Valid @RequestBody FeedbackModel entity) {
//        Long userId = JWTKit.getUserId();
//        entity.setUserId(userId);
//        if (entity.getCreateUser() != null) {
//            entity.setCreateName(entity.getCreateUser());
//        } else {
//          /*  User user = userService.getById(userId);
//            entity.setCreateName(user.getName());*/
//            entity.setCreateName(JWTKit.getAccount());
//        }
//        return SuccessTip.create(feedbackService.createMaster(entity, new FeedbackFilter(), null, null));
//    }

    @ApiOperation("查看反馈或建议")
    @GetMapping("/{id}")
    public Tip getTFeedback(@PathVariable Long id) {
        return SuccessTip.create(feedbackService.retrieveMaster(id));
    }

//    @ApiOperation("修改反馈或建议")
//    @PutMapping("/feedback/{id}")
//    public Tip updateTFeedback(@PathVariable Long id, @RequestBody Feedback entity) {
//        Long userId = JWTKit.getUserId();
//        entity.setId(id);
//        return SuccessTip.create(feedbackService.updateMaster(entity, new FeedbackFilter()));
//    }

    @ApiOperation("删除反馈或建议")
    @DeleteMapping("/{id}")
    public Tip deleteTFeedback(@PathVariable Long id) {
        return SuccessTip.create(feedbackService.deleteMaster(id));
    }

    @ApiOperation("反馈或建议列表")
    @GetMapping
    public Tip queryFeedbacks(Page page,
                              @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                              @RequestParam(name = "status", required = false) String status,
                              @RequestParam(name = "createName", required = false) String createName,
                              @RequestParam(name = "startTime", required = false) String startTime,
                              @RequestParam(name = "endTime", required = false) String endTime,
                              @RequestParam(name = "name", required = false) String name) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(queryFeedbackDao.findFeedbacks(page, status, createName, startTime, endTime));
        return SuccessTip.create(page);
    }
}
