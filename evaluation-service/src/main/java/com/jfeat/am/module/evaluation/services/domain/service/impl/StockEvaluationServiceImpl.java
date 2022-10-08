package com.jfeat.am.module.evaluation.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.evaluation.services.crud.filter.StockEvaluationFilter;
import com.jfeat.am.module.evaluation.services.domain.dao.QueryStockEvaluationDao;
import com.jfeat.am.module.evaluation.services.domain.model.EvaluationType;
import com.jfeat.am.module.evaluation.services.domain.model.StockEvaluationModel;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationRecord;
import com.jfeat.am.module.evaluation.services.domain.service.StockEvaluationService;

import com.jfeat.am.module.evaluation.services.crud.service.impl.CRUDStockEvaluationServiceImpl;
import com.jfeat.am.module.evaluation.services.persistence.dao.StockEvaluationMapper;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluation;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("StockEvaluationService")
public class StockEvaluationServiceImpl extends CRUDStockEvaluationServiceImpl implements StockEvaluationService {

    @Resource
    StockEvaluationService stockEvaluationService;
    @Resource
    StockEvaluationMapper stockEvaluationMapper;
    @Resource
    QueryStockEvaluationDao queryStockEvaluationDao;

    @Override
    public StockEvaluationModel createOne(StockEvaluationModel entity){
        Integer affected = 0;
        Long userId = JWTKit.getUserId();
        entity.setMemberId(userId);

        if(entity.getMemberName() == null || "".equals(entity.getMemberName())) {
            String userName = JWTKit.getAccount();
            entity.setMemberName(userName);
        }
        // 评论对象不为Evaluation时,originId、originType即stockId,stockType
        if(!EvaluationType.Evaluation.toString().equals(entity.getStockType())) {
            entity.setOriginId(entity.getStockId());
            entity.setOriginType(entity.getStockType());
        }
        try {
            StockEvaluationFilter filter = new StockEvaluationFilter();
            affected += stockEvaluationService.createMaster(entity, filter, null, null);
            Long modelId = (Long) filter.result().get("id") == null ? null : (Long) filter.result().get("id");
            entity.setId(modelId);
            // 插入消息通知
//            List<String> actions = new ArrayList<>();
//            actions.add("Evaluation");
//            actions.add("Flower");
//            actions.add("Favorite");
//            actions.add("UnFavorite");
//            actions.add("UnFlower");

            // 通知部分
//            subscriptionService.subscribe(userId,modelId, "Evaluation",actions);
//            Notify notify = new Notify();
//            notify.setTargetId(entity.getStockId());
//            notify.setTargetType(entity.getStockType());
//            notify.setOriginType(entity.getOriginType());
//            notify.setOriginId(entity.getOriginId());
//            notify.setSenderId(entity.getMemberId());
//            notify.setContent(entity.getContent());
//            notify.setSourceId(modelId);
//            notify.setSourceType(EvaluationType.Evaluation.toString());
//            notify.setAction("Evaluation");
//            userNotifyService.createRemind(notify);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        return entity;
    };

    /**
     * 评价用户，以及当前用户是否可以删除
     * */
    public List<StockEvaluationRecord> evaluations(Page<StockEvaluationRecord> page,
                                                   StockEvaluationRecord record,
                                                   String orderBy,
                                                   Long memberId){
        return queryStockEvaluationDao.findStockEvaluationPageWithReplys(page, record, orderBy, memberId);
    }

    /**
     * 检索评价列表，并将评价下的回复以树形结构整合到相应评价下
     * */
    public List<StockEvaluationRecord> evaluationsOnLayered(Page<StockEvaluationRecord> page,
                                                   StockEvaluationRecord record,
                                                   String orderBy,
                                                   Long memberId){

        List<StockEvaluationRecord> evaList =
                queryStockEvaluationDao.findStockEvaluationPage(page, record, orderBy, memberId);

        List<StockEvaluationRecord> replys =
                queryStockEvaluationDao.findReplys(record, memberId);

        buildEvaOnLayered(evaList, replys, new ArrayList<Long>());
        return evaList;
    }


    /**
     * 辅助递归整合评价树形结构 并返回子节点id
     */
    private final void buildEvaOnLayered(List<StockEvaluationRecord> nodes, List<StockEvaluationRecord> childs,
                                            List<Long> childIds) {
        if(nodes == null || childs == null) {
            return;
        }
        // 存储已被整合的childs
        List<StockEvaluationRecord> index = new ArrayList<>();  // 记录已整合节点
        for(int i = 0; i < nodes.size(); i++) {
            StockEvaluationRecord node  = nodes.get(i);
            List<StockEvaluationRecord> temp = null;    // 子节点列表
            for(int j = 0; j < childs.size(); j++) {
                if(node.getId().compareTo(childs.get(j).getStockId())  == 0
                        && "Evaluation".equals(childs.get(j).getStockType())) {
                    if(temp == null) {
                        temp = new ArrayList<StockEvaluationRecord>();
                    }
                    temp.add(childs.get(j));
                    index.add(childs.get(j));
                    childIds.add(childs.get(j).getId());
                }
            }
            node.setReplys(temp);
        }
        // 移除已整合节点
        for(int i = 0; i < index.size(); i++) {
            childs.removeAll(index);
        }
        // 递归整合游离节点
        for(int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getReplys() == null) {
                continue;
            } else if(childs.isEmpty()) {
                break;
            }
            buildEvaOnLayered(nodes.get(i).getReplys(), childs, childIds);
        }
    }


    /**
     * 屏蔽评价
     */
    public Integer forbiddenEvaluation(Long id) {
        Integer effected = 0;
        StockEvaluation stockEvaluation = stockEvaluationService.retrieveMaster(id);
        if(stockEvaluation != null) {
            // 查询评论回复
            StockEvaluationRecord record = new StockEvaluationRecord();
            record.setId(id);
            record.setOriginId(stockEvaluation.getOriginId());
            record.setOriginType(stockEvaluation.getOriginType());
            List<StockEvaluationRecord> records= new ArrayList<>();
            records.add(record);
            List<StockEvaluationRecord> replys =
                    queryStockEvaluationDao.findReplys(record, null);

            // 获取该评论及该评论下所有节点的id
            List<Long> ids = new ArrayList<>();
            buildEvaOnLayered(records, replys, ids);
            ids.add(id);

            Integer isDisplay = stockEvaluation.getIsDisplay() == 1 ? 0 : 1;
            // 评论屏蔽
            StockEvaluation vo = new StockEvaluation();
            vo.setIsDisplay(isDisplay);
            effected += stockEvaluationMapper.update(vo, new QueryWrapper<StockEvaluation>().in(StockEvaluation.ID, ids));
            stockEvaluation.setIsDisplay(0);
        }
        return effected;
    }


    /**
     * 置顶评价/取消置顶
     */
    public Integer stickEvaluation(Long id) {

        StockEvaluation stockEvaluation = stockEvaluationService.retrieveMaster(id);
        Integer status = stockEvaluation.getIsStick() == 1 ? 0 : 1;
        stockEvaluation.setIsStick(status);
        return stockEvaluationService.updateMaster(stockEvaluation);
    }


    /**
     * 单个 stock 的总评论数
     *
     * @Parma Long stockId ，String stockType
     */
    public Integer stockEvaluationCount(Long stockId, String stockType) {
        Integer count = stockEvaluationMapper.selectCount(new QueryWrapper<StockEvaluation>().eq("stock_id", stockId).eq("stock_type", stockType));
        return count;
    }


    /**
     * 单个 stock 的 总评论  以及 评论的追加
     *
     * @Parma Long stockId ，String stockType
     */
    public List<StockEvaluation> evaluation(Long stockId, String stockType) {

        List<StockEvaluation> evaluations = stockEvaluationMapper.selectList(new QueryWrapper<StockEvaluation>().eq(StockEvaluation.STOCK_ID, stockId).eq(StockEvaluation.STOCK_TYPE, stockType));

        return evaluations;
    }


}
