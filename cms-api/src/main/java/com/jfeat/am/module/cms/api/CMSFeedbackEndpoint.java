package com.jfeat.am.module.cms.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.feedback.services.crud.filter.TFeedbackFilter;
import com.jfeat.am.module.feedback.services.crud.service.TFeedbackService;
import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import com.jfeat.am.module.feedback.services.patch.FeedbackPatchService;
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
@Api("CMS-反馈建议")
@RequestMapping("/api/cms")
public class CMSFeedbackEndpoint{

    @Resource
    private TFeedbackService tFeedbackService;
/*    @Resource
    private SysUserService userService;*/
    @Resource
    FeedbackPatchService feedbackPatchService;

    @ApiOperation("提交反馈或建议")
    @PostMapping("/feedback")
    public Tip createTFeedback(@Valid @RequestBody TFeedbackModel entity) {
        Long userId = JWTKit.getUserId();

        entity.setUserId(userId);
        if (entity.getCreateUser() != null) {
            entity.setCreateName(entity.getCreateUser());
        } else {
           /* SysUser user = userService.getById(userId);*/
            entity.setCreateName(JWTKit.getAccount());
        }
        return SuccessTip.create(tFeedbackService.createMaster(entity, new TFeedbackFilter(), null, null));
    }

    @ApiOperation("查看反馈或建议")
    @GetMapping("/feedback/{id}")
    public Tip getTFeedback(@PathVariable Long id) {
        return SuccessTip.create(tFeedbackService.retrieveMaster(id, new TFeedbackFilter(), null, null).toJSONObject());
    }

    @ApiOperation("修改反馈或建议")
    @PutMapping("/adm/feedback/{id}")
    public Tip updateTFeedback(@PathVariable Long id, @RequestBody TFeedbackModel entity) {
        Long userId = JWTKit.getUserId();
        entity.setDealUserId(userId);
        entity.setId(id);
        return SuccessTip.create(tFeedbackService.updateMaster(entity, new TFeedbackFilter(), null, null));
    }
    @ApiOperation("删除反馈或建议")
    @DeleteMapping("/feedback/{id}")
    public Tip deleteTFeedback(@PathVariable Long id) {
        return SuccessTip.create(tFeedbackService.deleteMaster(id));
    }

   /* @ApiOperation("反馈或建议列表")
   该方法测试无数据,可调用下面的方法替代
    @GetMapping("/feedback")
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryTFeedbacks(Page page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                               @RequestParam(name = "unread", required = false) Integer unread,
                               @RequestParam(name = "name", required = false) String name) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(tFeedbackService.findFeedback(page, name, unread));
        return SuccessTip.create(page);
    }*/

    @GetMapping("/feedback/list")
    //此方法可能需要自行添加需要的参数,按需要使用
    public Tip queryTFeedbacks(Page page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                               @RequestParam(required = false) Integer unread,
                               @RequestParam(required = false) String createName,
                               @RequestParam(required = false) String startTime,
                               @RequestParam(required = false) String endTime) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(tFeedbackService.findTFeedbacks(page, unread, createName, startTime, endTime));

        return SuccessTip.create(page);
    }

    @ApiOperation("处理(解决)反馈或建议")
    @PutMapping("/adm/feedback/{id}/solve")
    public Tip solve(@PathVariable Long id, @RequestBody TFeedbackModel tFeedbackModel) {
        tFeedbackModel.setId(id);
        Integer result = feedbackPatchService.solve(tFeedbackModel);
        return SuccessTip.create(result);
    }
}
