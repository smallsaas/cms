package com.jfeat.am.module.survey.api.crud;

import com.jfeat.am.module.survey.services.crud.service.CRUDSurveyItemService;
import com.jfeat.am.module.survey.services.model.SurveyItemModel;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
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
import com.jfeat.am.module.log.annotation.BusinessLog;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-10-15
 */
@RestController
@Api("问卷选项")
@RequestMapping("/api/survey/surveys")
public class SurveyItemEndpoint   {


    @Resource
    CRUDSurveyItemService surveyItemService;


    @BusinessLog(name = "SurveyItem", value = "create SurveyItem")
    @PostMapping("/{surveyId}/items")
    @ApiOperation("add one option into survey")
    public Tip createSurveyItem(@PathVariable Long surveyId, @RequestBody SurveyItemModel entity) {

        Integer affected = 0;
        try {
            affected = surveyItemService.addOneItemIntoSurvey(surveyId, entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }


    @BusinessLog(name = "SurveyItem", value = "update SurveyItem")
    @PutMapping("/items/{id}")
    @ApiOperation("update one option")
    public Tip updateSurveyItem(@PathVariable Long id, @RequestBody SurveyItemModel entity) {
        entity.setId(id);
        return SuccessTip.create(surveyItemService.updateItem(id,entity));
    }

    @BusinessLog(name = "SurveyItem", value = "delete SurveyItem")
    @DeleteMapping("/items/{id}")
    @ApiOperation("delete one option")
    public Tip deleteSurveyItem(@PathVariable Long id) {
        return SuccessTip.create(surveyItemService.deleteItem(id));
    }


}
