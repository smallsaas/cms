package com.jfeat.am.module.cms.api;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.ff.api.permission.StockFavoritePermission;
import com.jfeat.am.module.ff.services.domain.dao.QueryStockFlowerDao;
import com.jfeat.am.module.ff.services.domain.model.StockFlowerModel;
import com.jfeat.am.module.ff.services.domain.service.StockFlowerService;
import com.jfeat.am.module.ff.services.persistence.model.StockFlower;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.log.annotation.BusinessLog;

import javax.annotation.Resource;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-16
 */
@RestController
@Api("CMS-用户点击点赞的路径")
@RequestMapping("/api/cms")
public class CMSFlowerEndpoint  {


    @Resource
    StockFlowerService stockFlowerService;

    @Resource
    QueryStockFlowerDao queryStockFlowerDao;

    @BusinessLog(name = "StockFlower", value = "create StockFlower")
    @ApiOperation("点赞/取消点赞 提交的参数需要带上 点赞对象的 类型 以及 点赞对象的 id")
    @PostMapping("/flowers")
    public Tip createFlower(@RequestBody StockFlowerModel entity) {

        Integer affected = 0;

        Long userId = JWTKit.getUserId();
        entity.setMemberId(userId);

        if(entity.getMemberName() == null || "".equals(entity.getMemberName())) {
            String userName = JWTKit.getAccount();
            entity.setMemberName(userName);
        }

        try {
            affected = stockFlowerService.floweredOrNot(entity, entity.getStockId(), entity.getStockType());

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }


    @BusinessLog(name = "StockFlower", value = "delete StockFlower")
    @DeleteMapping("/flowers/{id}")
    @ApiOperation("取消点赞")
    @Deprecated
    public Tip deleteStockFlower(@PathVariable Long id) {

        Long userId = JWTKit.getUserId();

        StockFlower flower = stockFlowerService.retrieveMaster(id);
         //权限判断
        /* if (flower.getMemberId().compareTo(userId) != 0 || !(ShiroKit.hasPermission(StockFavoritePermission.STOCKFAVORITE_EDIT))) {
            throw new BusinessException(BusinessCode.NoPermission);
        }*/
        return SuccessTip.create(stockFlowerService.deleteMaster(id));
    }


    @BusinessLog(name = "StockFavorite", value = "delete StockFavorite")
    @GetMapping("/app/flowers")
    @ApiOperation("我的点赞history/不传递类型默认为点赞的文章列表")
    public Tip myStockFlower(Page<StockFlower> page,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "stockType", required = false, defaultValue = "Article") String stockType) {
        Long userId = JWTKit.getUserId();
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        page.setRecords(stockFlowerService.myFlowers(page, userId, stockType));
        return SuccessTip.create(page);
    }

    @BusinessLog(name = "StockFavorite", value = "delete StockFavorite")
    @GetMapping("/stocks/flowers")
    @ApiOperation("某个stock点赞history/不传递类型默认为点赞的文章列表")
    public Tip stockFlower(Page<StockFlower> page,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(name = "stockType", required = true, defaultValue = "Article") String stockType,
                           @RequestParam(name = "stockId", required = true) Long stockId) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);

        page.setRecords(stockFlowerService.stockFlowers(page, stockId, stockType));
        return SuccessTip.create(page);
    }
   /* @BusinessLog(name = "StockFlower", value = "create StockFlower")
    @ApiOperation("用户点赞商品post提交路径中包含stock货物的id号码")
    @PostMapping("/stock/{stockId}")
    public Tip createStockFlower(@PathVariable Long stockId, @RequestBody StockFlowerModel entity) {
        entity.setStockId(stockId);
        entity.setStockType(EvaluationType.Product.toString());
        entity.setMemberId(JWTKit.getUserId(getHttpServletRequest()));

        Integer affected = 0;
        try {
            affected = stockFlowerService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }


*/
/*    @GetMapping("/flowers/{id}")
    public Tip getStockFlower(@PathVariable Long id) {
        return SuccessTip.create(stockFlowerService.retrieveMaster(id));
    }*/

/*    @BusinessLog(name = "StockFlower", value = "update StockFlower")
    @Deprecated
    @ApiOperation("修改")
    @PutMapping("/{id}")
    public Tip updateStockFlower(@PathVariable Long id, @RequestBody StockFlowerModel entity) {
        entity.setId(id);
        return SuccessTip.create(stockFlowerService.updateMaster(entity));
    }*/


    /*@ApiOperation("我的点赞列表")
    @GetMapping
    public Tip queryStockFlowers(Page<StockFlowerRecord> page,
                                 @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "id", required = false) Long id,
                                 @RequestParam(name = "memberName", required = false) String memberName,
                                 @RequestParam(name = "stockId", required = false) Long stockId,
                                 @RequestParam(name = "stockName", required = false) Long stockName,
                                 @RequestParam(name = "stockType", required = false) String stockType,
                                 @RequestParam(name = "flowerCount", required = false) Integer flowerCount,
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

        StockFlowerRecord record = new StockFlowerRecord();
        record.setId(id);
        record.setMemberId(JWTKit.getUserId(getHttpServletRequest()));
        record.setMemberName(memberName);
        record.setStockId(stockId);
        record.setStockName(stockName);
        record.setStockType(stockType);
        record.setFlowerCount(flowerCount);
        record.setCreateTime(createTime);

        page.setRecords(queryStockFlowerDao.findStockFlowerPage(page, record, orderBy));

        return SuccessTip.create(page);
    }
*/

}
