package com.jfeat.am.module.evaluation.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.evaluation.services.crud.filter.StockEvaluationFilter;
import com.jfeat.am.module.evaluation.services.domain.model.EvaluationType;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationStarRecord;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
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
import com.jfeat.am.module.evaluation.services.domain.dao.QueryStockEvaluationDao;

import com.jfeat.am.module.evaluation.services.domain.service.StockEvaluationService;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationRecord;
import com.jfeat.am.module.evaluation.services.domain.model.StockEvaluationModel;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;


/*
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-16
 */

@RestController
@Api("cms-评价")
@RequestMapping("/api/cms")
public class CMSEvaluationEndpoint  {



    @Resource
    StockEvaluationService stockEvaluationService;
//    @Resource
//    UserNotifyService userNotifyService;
//    @Resource
//    SubscriptionService subscriptionService;

    @Resource
    QueryStockEvaluationDao queryStockEvaluationDao;



    /**
     * 用户   操作           事件
     * U1    发表文章A1      订阅了A1
     * U2    对A1发表评论C1  订阅了C1，生成notify(sourceId:C1, targetId:A1) -> U1可以收到这个消息
     * U3    对C1发表评论C2  订阅了C2，生成notify(sourceId:C2, targetId:C1) -> U2可以收到这个消息
     * */
    @BusinessLog(name = "StockEvaluation", value = "create StockEvaluation")
    @PostMapping("/evaluations")
    @ApiOperation("新建评价，如评价文件，则带上文章的id及类型 stockId stockType,其他情况同上,回复评论时originId,originType带文章id、type")
    public Tip createArticleEvaluation(@RequestBody StockEvaluationModel entity) {

        StockEvaluationModel stockEvaluationModel = stockEvaluationService.createOne(entity);

        return SuccessTip.create(stockEvaluationModel);
    }

    @BusinessLog(name = "StockEvaluation", value = "create StockEvaluation")
    @PostMapping("/evaluations/{stockType}/{stockId}")
    @ApiOperation("新建评价，如评价文件，则带上文章的id及类型 stockId stockType,其他情况同上,回复评论时originId,originType带文章id、type")
    public Tip createArticleEvaluationAccurate(@PathVariable Long stockId,@PathVariable String stockType,@RequestBody StockEvaluationModel entity) {
        entity.setStockId(stockId);
        entity.setStockType(stockType);
        StockEvaluationModel stockEvaluationModel = stockEvaluationService.createOne(entity);

        return SuccessTip.create(stockEvaluationModel);
    }


    @GetMapping("/evaluations/{id}")
    @ApiOperation("查看评价")
    public Tip getStockEvaluation(@PathVariable Long id) {
        return SuccessTip.create(stockEvaluationService.retrieveMaster(id));
    }

    @BusinessLog(name = "StockEvaluation", value = "update StockEvaluation")
    @PutMapping("/evaluations/{id}")
    @ApiOperation("修改评价")
    public Tip updateStockEvaluation(@PathVariable Long id, @RequestBody StockEvaluationModel entity) {
        Long memberId = JWTKit.getUserId();
       /* if (stockEvaluationService.retrieveMaster(id).getMemberId().compareTo(memberId) == 0 ||
                ShiroKit.hasPermission(EvaluationPermission.STOCKEVALUATION_EDIT)) {*/
            entity.setId(id);
            return SuccessTip.create(stockEvaluationService.updateMaster(entity,false));
       /* }
        throw new BusinessException(BusinessCode.BadRequest);*/
    }

    @BusinessLog(name = "StockEvaluation", value = "delete StockEvaluation")
    @DeleteMapping("/evaluations/{id}")
    @ApiOperation("删除评价")
    @Deprecated
    public Tip deleteStockEvaluation(@PathVariable Long id) {
        Long memberId = JWTKit.getUserId();
      /*  if (stockEvaluationService.retrieveMaster(id).getMemberId().compareTo(memberId) == 0 ||
                ShiroKit.hasPermission(EvaluationPermission.STOCKEVALUATION_EDIT)) {*/
            return SuccessTip.create(stockEvaluationService.deleteMaster(id));
       /* }
        throw new BusinessException(BusinessCode.NoPermission);*/
    }

    @ApiOperation("商品/订单评价详情 : 默认评论与回复同级返回; 当isLayered=true时,返回分层的树形结构评论列表")
    @GetMapping("/evaluations")
    public Tip queryStockEvaluations(Page<StockEvaluationRecord> page,
                                     @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                     @RequestParam(name = "stockId", required = false) Long stockId,
                                     @RequestParam(name = "stockType", required = false) String stockType,
                                     @RequestParam(name = "createTime", required = false) Date createTime,
                                     @RequestParam(name = "orderBy", required = false) String orderBy,
                                     @RequestParam(name = "sort", required = false) String sort,
                                     @RequestParam(name = "starValue", required = false) Integer starValue,
                                     @RequestParam(name = "tradeNumber", required = false) String tradeNumber,
                                     @RequestParam(name = "originId", required = false) Long originId,
                                     @RequestParam(name = "originType", required = false) String originType,
                                     @RequestParam(name = "isLayered", required = false, defaultValue = "false") Boolean isLayered,
                                     @RequestParam(name = "isDisplay", required = false, defaultValue = "1") Integer isDisplay


    ) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Long memberId = JWTKit.getUserId();

        StockEvaluationRecord record = new StockEvaluationRecord();
        record.setStockId(stockId);
        record.setStockType(stockType);
        record.setCreateTime(createTime);
        record.setStarValue(starValue);
        record.setTradeNumber(tradeNumber);
        record.setOriginType(originType);
        record.setOriginId(originId);
        record.setIsDisplay(isDisplay);
        List<StockEvaluationRecord> evaluations =new ArrayList<>();
        if(isLayered) {
            evaluations = stockEvaluationService.evaluationsOnLayered(page, record, orderBy,memberId);
        } else {
            evaluations = stockEvaluationService.evaluations(page, record, orderBy,memberId);
        }
        page.setRecords(evaluations);
        return SuccessTip.create(page);
    }



    @ApiOperation("商品/订单评价详情 : 默认评论与回复同级返回; 当isLayered=true时,返回分层的树形结构评论列表")
    @GetMapping("/evaluations/{stockType}/{stockId}")
    public Tip queryStockEvaluationsAccurate(Page<StockEvaluationRecord> page,
                                     @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                     @PathVariable(name = "stockId") Long stockId,
                                     @PathVariable(name = "stockType") String stockType,
                                     @RequestParam(name = "createTime", required = false) Date createTime,
                                     @RequestParam(name = "orderBy", required = false) String orderBy,
                                     @RequestParam(name = "sort", required = false) String sort,
                                     @RequestParam(name = "starValue", required = false) Integer starValue,
                                     @RequestParam(name = "tradeNumber", required = false) String tradeNumber,
                                     @RequestParam(name = "originId", required = false) Long originId,
                                     @RequestParam(name = "originType", required = false) String originType,
                                     @RequestParam(name = "isLayered", required = false, defaultValue = "false") Boolean isLayered,
                                     @RequestParam(name = "isDisplay", required = false, defaultValue = "1") Integer isDisplay


    ) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Long memberId = JWTKit.getUserId();

        StockEvaluationRecord record = new StockEvaluationRecord();
        record.setStockId(stockId);
        record.setStockType(stockType);
        record.setCreateTime(createTime);
        record.setStarValue(starValue);
        record.setTradeNumber(tradeNumber);
        record.setOriginType(originType);
        record.setOriginId(originId);
        record.setIsDisplay(isDisplay);
        List<StockEvaluationRecord> evaluations =new ArrayList<>();
        if(isLayered) {
            evaluations = stockEvaluationService.evaluationsOnLayered(page, record, orderBy,memberId);
        } else {
            evaluations = stockEvaluationService.evaluations(page, record, orderBy,memberId);
        }
        page.setRecords(evaluations);
        return SuccessTip.create(page);
    }



    @ApiOperation("评分数量")
    @GetMapping("/evaluations/starCount")
    public Tip queryStarCount( @RequestParam(name = "stockId", required = false) Long stockId,
                               @RequestParam(name = "stockType", required = false) String stockType,
                               @RequestParam(name = "createTime", required = false) Date createTime,
                               @RequestParam(name = "display", required = false) String display) {

        StockEvaluationRecord record = new StockEvaluationRecord();
        record.setStockId(stockId);
        record.setStockType(stockType);
        record.setCreateTime(createTime);

        // 对没有数据的星级初始化
        List<Integer> stars = new ArrayList<>();
        stars.addAll(Arrays.asList(1, 2, 3, 4, 5));
        List<StockEvaluationStarRecord> records = queryStockEvaluationDao.findStarCount(record, display);
        for(StockEvaluationStarRecord item : records) {
            stars.remove(item.getStarValue());
        }
        for(Integer star : stars) {
            StockEvaluationStarRecord entity = new StockEvaluationStarRecord();
            entity.setStartCount(0);
            entity.setStarValue(star);
            records.add(entity);
        }
        records.sort((o1, o2) -> o1.getStarValue() - o2.getStarValue());
        return SuccessTip.create(records);
    }

    @PostMapping("/evaluations/{id}/action/forbidden")
    @ApiOperation("屏蔽评价")
    public Tip forbiddenEvaluation(@PathVariable Long id) {

        Integer result =  stockEvaluationService.forbiddenEvaluation(id);

        return SuccessTip.create(result);
    }
    @PostMapping("/evaluations/{id}/action/stick")
    @ApiOperation("置顶评价")
    public Tip stickEvaluation(@PathVariable Long id) {
        Integer result =  stockEvaluationService.stickEvaluation(id);
        return SuccessTip.create(result);
    }

//    @PostMapping("/evaluations/bulk/search")
//    @ApiOperation("根据Ids批量查询评价")
//    public Tip bulkQueryEvaluations(@RequestBody IdsRequest idsRquest) {
//        Page<StockEvaluationRecord> page = new Page<>();
//        page.setSize(idsRquest.getPageSize() == null ? 10 : idsRquest.getPageSize());
//        page.setCurrent(idsRquest.getPageNum() == null ? 1 : idsRquest.getPageNum());
//        page.setRecords(queryStockEvaluationDao.bulkFindEvaluations(page, idsRquest.getIds(),idsRquest.getStockIds(), idsRquest.getStockType(), idsRquest.getStarValue()));
//        return SuccessTip.create(page);
//    }


}
