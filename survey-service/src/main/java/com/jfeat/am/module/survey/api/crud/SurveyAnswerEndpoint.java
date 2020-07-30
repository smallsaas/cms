package com.jfeat.am.module.survey.api.crud;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.survey.services.model.SurveyAnswerModel;
import com.jfeat.am.module.survey.services.model.SurveyAnswerRecord;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.core.util.HttpKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.survey.services.domain.dao.QuerySurveyAnswerDao;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.survey.services.domain.service.SurveyAnswerService;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-10-15
 */
@RestController
@Api("问卷")
@RequestMapping("/api/survey/surveys")
public class SurveyAnswerEndpoint   {


    @Resource
    SurveyAnswerService surveyAnswerService;

    @Resource
    QuerySurveyAnswerDao querySurveyAnswerDao;


    @GetMapping("/my/answers")
    @ApiOperation("show the answer")
    public Tip mySurveyAnswer() {
        Long userId = JWTKit.getUserId();
        return SuccessTip.create(querySurveyAnswerDao.myAnswer(userId));
    }




    @BusinessLog(name = "SurveyAnswer", value = "create SurveyAnswer")
    @PostMapping("/answers")
    @ApiOperation("answer the  survey")
    public Tip createSurveyAnswer(@RequestBody SurveyAnswerModel entity) {

        Integer affected = 0;

        try {
            affected = surveyAnswerService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/answers/{id}")
    @ApiOperation("show the answer")
    public Tip getSurveyAnswer(@PathVariable Long id) {
        return SuccessTip.create(surveyAnswerService.retrieveMaster(id));
    }

    @BusinessLog(name = "SurveyAnswer", value = "update SurveyAnswer")
    @PutMapping("/answers/{id}")
    @Deprecated
    public Tip updateSurveyAnswer(@PathVariable Long id, @RequestBody SurveyAnswerModel entity) {
        entity.setId(id);
        return SuccessTip.create(surveyAnswerService.updateMaster(entity));
    }

    @BusinessLog(name = "SurveyAnswer", value = "delete SurveyAnswer")
    @DeleteMapping("/answers/{id}")
    @Deprecated
    public Tip deleteSurveyAnswer(@PathVariable Long id) {
        return SuccessTip.create(surveyAnswerService.deleteMaster(id));
    }

    @GetMapping("/answers")
    @ApiOperation("/answers/answers")
    public Tip querySurveyAnswers(Page<SurveyAnswerRecord> page,
                                  @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                  @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                  @RequestParam(name = "id", required = false) Long id,
                                  @RequestParam(name = "surveyId", required = false) Long surveyId,
                                  @RequestParam(name = "authorId", required = false) Long authorId,
                                  @RequestParam(name = "authorMessage", required = false) String authorMessage,
                                  @RequestParam(name = "content", required = false) String content,
                                  @RequestParam(name = "orderBy", required = false) String orderBy,
                                  @RequestParam(name = "sort", required = false) String sort) {
        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        SurveyAnswerRecord record = new SurveyAnswerRecord();
        record.setId(id);
        record.setSurveyId(surveyId);
        record.setAuthorId(authorId);
        record.setAuthorMessage(authorMessage);
        record.setContent(content);

        page.setRecords(querySurveyAnswerDao.findSurveyAnswerPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}
