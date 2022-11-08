package com.jfeat.am.module.evaluation.services.domain.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.am.module.evaluation.services.domain.model.record.StockEvaluationRecord;
import com.jfeat.am.module.evaluation.services.domain.service.StockEvaluationService;
import com.jfeat.am.module.evaluation.services.domain.service.StockEvaluationStarService;

import com.jfeat.am.module.evaluation.services.crud.service.impl.CRUDStockEvaluationStarServiceImpl;
import com.jfeat.am.module.ff.services.persistence.dao.StockFlowerMapper;
import com.jfeat.am.module.ff.services.persistence.model.StockFlower;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("StockEvaluationStarService")
public class StockEvaluationStarServiceImpl extends CRUDStockEvaluationStarServiceImpl implements StockEvaluationStarService {

    @Resource
    StockEvaluationService stockEvaluationService;

    @Resource
    StockFlowerMapper stockFlowerMapper;
    @Override
    public void setEvaluationStar(List<StockEvaluationRecord> evaluations) {

        if (evaluations==null || evaluations.size()<=0){
            return;
        }

        QueryWrapper<StockFlower> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StockFlower.STOCK_TYPE,stockEvaluationService.getEntityName());
        List<StockFlower> stockFlowerList = stockFlowerMapper.selectList(queryWrapper);

        Map<Long,Integer> map = new HashMap<>();
        for (StockFlower stockFlower:stockFlowerList){
            if (map.containsKey(stockFlower.getStockId())){
                map.put(stockFlower.getStockId(),map.get(stockFlower.getStockId())+1);
            }else {
                map.put(stockFlower.getStockId(),1);
            }
        }

        if (map!=null){
            for (StockEvaluationRecord stockEvaluationRecord:evaluations){
                if (map.containsKey(stockEvaluationRecord.getId())){
                    stockEvaluationRecord.setStar(map.get(stockEvaluationRecord.getId()));
                }

            }
        }

        List<Long> ids = evaluations.stream().map(StockEvaluationRecord::getId).collect(Collectors.toList());

        queryWrapper.in(StockFlower.STOCK_ID,ids);
        List<StockFlower> stockFlowers = stockFlowerMapper.selectList(queryWrapper);


        if (stockFlowers!=null&&stockFlowers.size()>0){
            for (StockEvaluationRecord stockEvaluationRecord:evaluations){
                for (StockFlower stockFlower:stockFlowerList){
                    if (stockEvaluationRecord.getId().equals(stockFlower.getStockId())){
                        stockEvaluationRecord.setStared(true);
                        break;
                    }

                }
            }


        }

    }
}
