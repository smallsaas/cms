package com.jfeat.am.module.survey.api.crud;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.survey.services.crud.service.CRUDSurveyService;
import com.jfeat.am.module.survey.services.model.SurveyModel;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.survey.services.domain.dao.QuerySurveyDao;

import com.jfeat.am.module.survey.services.persistence.model.Survey;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


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
public class SurveyEndpoint   {


    @Resource
    CRUDSurveyService surveyService;

    @Resource
    QuerySurveyDao querySurveyDao;


    @BusinessLog(name = "Survey", value = "create Survey")
    @PostMapping
    @ApiOperation("create survey including survey option")
    public Tip createSurvey(@RequestBody SurveyModel entity) {

        Integer affected = 0;
        try {
            entity.setAuthor(JWTKit.getAccount());
            surveyService.createSurvey(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(entity);
    }

    @PutMapping("/{id}/enabled")
    @ApiOperation("set survey enable=1")
    public Tip setEnableSurvey(@PathVariable Long id) {
        return SuccessTip.create(surveyService.setDefaultSurvey(id));
    }


    @GetMapping("/selected")
    @ApiOperation("show default survey including all details")
    public Tip enabledSurvey(@RequestParam(name = "type", required = true) String type) {
        return SuccessTip.create(surveyService.showDefaultSurveyForCustomer(type));
    }


    @GetMapping("/{id}")
    @ApiOperation("show survey including all details")
    public Tip getSurvey(@PathVariable Long id) {
        return SuccessTip.create(surveyService.showSurvey(id,false,null));
    }



    @BusinessLog(name = "Survey", value = "update Survey")
    @PutMapping("/{id}")
    @ApiOperation("update survey including survey option")
    public Tip updateSurvey(@PathVariable Long id, @RequestBody SurveyModel entity) {
        entity.setId(id);
        entity.setUpdateTime(new Date());
        Integer result = surveyService.updateSurvey(id,entity);
        return SuccessTip.create(result);
    }

    @BusinessLog(name = "Survey", value = "delete Survey")
    @DeleteMapping("/{id}")
    @ApiOperation("delete survey including survey option")
    public Tip deleteSurvey(@PathVariable Long id) {
        return SuccessTip.create(surveyService.deleteSurvey(id));
    }

    @BusinessLog(name = "Survey", value = "delete Survey")
    @PostMapping("/bulk/delete")
    @ApiOperation("delete survey including survey option")
    public Tip bulkDeleteSurvey(@RequestBody Ids ids) {
        return SuccessTip.create(surveyService.bulkDel(ids.getIds()));
    }

    @GetMapping
    @ApiOperation("get survey including survey option")
    public Tip querySurveys(Page<Survey> page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "author", required = false) String author,
                            @RequestParam(name = "title", required = false) String title,
                            @RequestParam(name = "type", required = false) String type,
                            @RequestParam(name = "status", required = false) String status,
                            @RequestParam(name = "enabled", required = false) Integer enabled,
                            @RequestParam(name = "createTime", required = false) Date createTime,
                            @RequestParam(name = "updateTime", required = false) Date updateTime,
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

        Survey record = new Survey();
        record.setAuthor(author);
        record.setTitle(title);
        record.setType(type);
        record.setStatus(status);
        record.setEnabled(enabled);
        record.setCreateTime(createTime);
        record.setUpdateTime(updateTime);

        page.setRecords(querySurveyDao.findSurveyPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}
