package com.jfeat.am.module.cms.api;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.evaluation.api.permission.EvaluationPermission;
import com.jfeat.am.module.evaluation.services.domain.service.StockEvaluationService;
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
import com.jfeat.am.module.evaluation.services.domain.dao.QueryStockEvaluationAdditionDao;
import com.jfeat.am.module.log.annotation.BusinessLog;

import com.jfeat.am.module.evaluation.services.domain.service.StockEvaluationAdditionService;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationAdditionRecord;
import com.jfeat.am.module.evaluation.services.domain.model.StockEvaluationAdditionModel;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-16
 */

@RestController
@Api("追加 评价")
@RequestMapping("/api/cms/evaluation")
public class CMSEvaluationAdditionEndpoint  {


    @Resource
    StockEvaluationAdditionService stockEvaluationAdditionService;
    @Resource
    StockEvaluationService stockEvaluationService;

    @Resource
    QueryStockEvaluationAdditionDao queryStockEvaluationAdditionDao;

    @BusinessLog(name = "StockEvaluationAddition", value = "create StockEvaluationAddition")
    @PostMapping("/{evaluationId}/addition")
    @ApiOperation("追加评价 - 只能追加自己的评论 或者 具有 追加评价权限的用户进行追加")
    public Tip createStockEvaluationAddition(@PathVariable Long evaluationId, @RequestBody StockEvaluationAdditionModel entity) {
       /* if (stockEvaluationService.retrieveMaster(evaluationId).getMemberId().compareTo(JWTKit.getUserId())==0 ||
                ShiroKit.hasPermission(EvaluationPermission.STOCKEVALUATION_EDIT)){*/
            entity.setEvaluateId(evaluationId);
            Integer affected = 0;
            try {
                affected = stockEvaluationAdditionService.addSlaveItem(entity);

            } catch (DuplicateKeyException e) {
                throw new BusinessException(BusinessCode.DuplicateKey);
            }
            return SuccessTip.create(affected);
        }
       /* throw new BusinessException(BusinessCode.NoPermission);
    }*/

    @BusinessLog(name = "StockEvaluationAddition", value = "create StockEvaluationAddition")
    @PostMapping("/{evaluationId}/pub/addition")
    @ApiOperation("追加评价 - 类似BBS 可以直接追加别的评论")
    public Tip evaluationAddition(@PathVariable Long evaluationId, @RequestBody StockEvaluationAdditionModel entity) {
            entity.setEvaluateId(evaluationId);
            Integer affected = 0;
            try {
                affected = stockEvaluationAdditionService.addSlaveItem(entity);

            } catch (DuplicateKeyException e) {
                throw new BusinessException(BusinessCode.DuplicateKey);
            }
            return SuccessTip.create(affected);
    }

    @GetMapping("/addition/{id}")
    @ApiOperation("查看 追加评价")
    public Tip getStockEvaluationAddition(@PathVariable Long id) {
        return SuccessTip.create(stockEvaluationAdditionService.getSlaveItem(id));
    }

    @BusinessLog(name = "StockEvaluationAddition", value = "update StockEvaluationAddition")
    @PutMapping("/addition/{id}")
    @ApiOperation("修改追加")
    public Tip updateStockEvaluationAddition(@PathVariable Long id, @RequestBody StockEvaluationAdditionModel entity) {
        entity.setId(id);
        return SuccessTip.create(stockEvaluationAdditionService.updateSlaveItem(entity));
    }

    @BusinessLog(name = "StockEvaluationAddition", value = "delete StockEvaluationAddition")
    @DeleteMapping("/addition/{id}")
    @ApiOperation("删除 追加评价")
    public Tip deleteStockEvaluationAddition(@PathVariable Long id) {
        return SuccessTip.create(stockEvaluationAdditionService.removeSlaveItem(id));
    }

    @GetMapping("/{evaluationId}/addition")
    @ApiOperation("查看 单个评价下所有的追加评价")
    public Tip queryStockEvaluationAdditions(Page<StockEvaluationAdditionRecord> page,
                                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                             @RequestParam(name = "id", required = false) Long id,
                                             @RequestParam(name = "content", required = false) String content,
                                             @RequestParam(name = "status", required = false) String status,
                                             @RequestParam(name = "isDisplay", required = false) Integer isDisplay,
                                             @RequestParam(name = "createTime", required = false) Date createTime,
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

        StockEvaluationAdditionRecord record = new StockEvaluationAdditionRecord();
        record.setId(id);
        record.setContent(content);
        record.setStatus(status);
        record.setIsDisplay(isDisplay);
        record.setCreateTime(createTime);

        page.setRecords(queryStockEvaluationAdditionDao.findStockEvaluationAdditionPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}
