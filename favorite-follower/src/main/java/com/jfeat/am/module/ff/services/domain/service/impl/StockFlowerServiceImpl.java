package com.jfeat.am.module.ff.services.domain.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.ff.services.crud.service.impl.CRUDStockFlowerServiceImpl;
import com.jfeat.am.module.ff.services.domain.service.StockFlowerService;
import com.jfeat.am.module.ff.services.persistence.dao.StockFlowerMapper;
import com.jfeat.am.module.ff.services.persistence.model.StockFlower;
import com.jfeat.am.module.notification.services.crud.service.UserNotifyService;
import com.jfeat.am.module.notification.services.persistence.model.Notify;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("StockFlowerService")
public class StockFlowerServiceImpl extends CRUDStockFlowerServiceImpl implements StockFlowerService {


    @Resource
    StockFlowerMapper flowerMapper;
    @Resource
    UserNotifyService userNotifyService;


    /**
     * 点赞 记录 我的点赞 记录
     */
    public List<StockFlower> myFlowers(Page<StockFlower> page, Long memberId,String stockType) {

        List<StockFlower> flowers = flowerMapper.selectPage(page, new EntityWrapper<StockFlower>().eq(StockFlower.MEMBER_ID, memberId)
                .eq(StockFlower.STOCK_TYPE, stockType));
        return flowers;

    }

    /**
     * 单个商品点赞 记录
     */
    public List<StockFlower> stockFlowers(Page<StockFlower> page, Long stockId, String stockType) {
        List<StockFlower> flowers = flowerMapper.selectPage(page, new EntityWrapper<StockFlower>()
                .eq(StockFlower.STOCK_ID, stockId)
                .eq(StockFlower.STOCK_TYPE, stockType).orderBy(StockFlower.CREATE_TIME, false));
        return flowers;
    }


    /**
     * 是否 已点赞
     */
    public Integer isFlowered(Long userId, Long stockId, String stockType) {
        StockFlower origin = null;
        if(userId != null) {
            StockFlower flower = new StockFlower();
            flower.setMemberId(userId);
            flower.setStockId(stockId);
            flower.setStockType(stockType);
            origin = flowerMapper.selectOne(flower);
        }
        return origin == null ? Integer.valueOf(0) : Integer.valueOf(1);
    }

    /**
     * 是否 已点赞
     */
    public StockFlower flowered(Long userId, Long stockId, String stockType) {
        StockFlower flower = new StockFlower();
        flower.setMemberId(userId);
        flower.setStockId(stockId);
        flower.setStockType(stockType);
        StockFlower origin = flowerMapper.selectOne(flower);
        if (origin == null)
            return null;
        return origin;
    }

    /**
     * 点赞(cancel点赞)
     */
    public Integer floweredOrNot(StockFlower flower, Long originId, String originType) {
        Integer affected = 0;
        StockFlower origin = flowerMapper.selectOne(flower);
        Notify notify = new Notify();
        notify.setTargetId(flower.getStockId());
        notify.setTargetType(flower.getStockType());
        notify.setOriginType(originType);
        notify.setOriginId(originId);
        notify.setSenderId(flower.getMemberId());
        notify.setContent("");
        if (origin == null) {
            affected += flowerMapper.insert(flower);
            notify.setSourceId(flower.getId());
            notify.setSourceType("Flower");
            notify.setAction("UnFlower");
            userNotifyService.createRemind(notify);

            return affected;
        }
        // 取消点赞获取消息
//        userNotifyService.createRemind(flower.getStockId(),
//                flower.getStockType(),
//                "UnFlower",
//                flower.getMemberId(),
//                "",
//                flower.getStockId(),
//                flower.getStockType());
        affected += flowerMapper.deleteById(origin.getId());
        return affected;
    }
}
