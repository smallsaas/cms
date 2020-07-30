package com.jfeat.am.module.survey.api.path;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.survey.services.domain.service.SurveyAnswerService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Api("判断是否已回答过默认问卷")
@RequestMapping("/api/app/surveys")
public class UserAnswerEndpoint   {



    @Resource
    SurveyAnswerService surveyAnswerService;


    @BusinessLog(name = "check current user has been finished answer this survey ", value = "check SurveyAnswer")
    @GetMapping("/answered/{surveyId}")
    @ApiOperation("is already answer the  survey")
    public Tip answerSurveyAnswer(@PathVariable Long surveyId) {
        return SuccessTip.create(surveyAnswerService.isAnswered(JWTKit.getUserId(),surveyId));
    }
}
