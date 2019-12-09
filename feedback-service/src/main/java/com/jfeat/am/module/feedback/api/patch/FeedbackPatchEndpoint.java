package com.jfeat.am.module.feedback.api.patch;

import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import com.jfeat.am.module.feedback.services.patch.FeedbackPatchService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by vincenthuang on 18/09/2017.
 */
@RestController
@Api("反馈建议")
@RequestMapping("/api")
public class FeedbackPatchEndpoint  {

    @Resource
    FeedbackPatchService feedbackPatchService;

    @ApiOperation("处理(解决)反馈或建议")
    @PutMapping("/adm/feedback/{id}/solve")
    public Tip solve(@PathVariable Long id, @RequestBody TFeedbackModel tFeedbackModel) {
        tFeedbackModel.setId(id);
        Integer result = feedbackPatchService.solve(tFeedbackModel);
        return SuccessTip.create(result);
    }

}