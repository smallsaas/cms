package com.jfeat.am.module.cms.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.ff.api.permission.StockFavoritePermission;
import com.jfeat.am.module.ff.services.domain.dao.QueryStockFavoriteDao;
import com.jfeat.am.module.ff.services.domain.model.StockFavoriteModel;
import com.jfeat.am.module.ff.services.domain.service.StockFavoriteService;
import com.jfeat.am.module.ff.services.persistence.model.StockFavorite;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-16
 */
@RestController
@Api("CMS-用户点击收藏的路径")
@RequestMapping("/api/cms")
public class CMSFavoriteEndpoint  {


    @Resource
    StockFavoriteService favoriteService;

    @Resource
    QueryStockFavoriteDao queryStockFavoriteDao;

    @BusinessLog(name = "StockFavorite", value = "create StockFavorite")
    @ApiOperation(value = "用户收藏文章", response = StockFavoriteModel.class)
    @PostMapping("/favorites")
    public Tip createArticleFavorite(@RequestBody StockFavoriteModel entity) {

        Long userId = JWTKit.getUserId();
        entity.setMemberId(userId);

        if(entity.getMemberName() == null || "".equals(entity.getMemberName())) {
            String userName = JWTKit.getAccount();
            entity.setMemberName(userName);
        }
        Integer affected = 0;
        try {
            affected += favoriteService.favoritedOrNot(entity, entity.getStockId(), entity.getStockType());

        } catch (DuplicateKeyException e) {
            return SuccessTip.create(affected);
        }

        return SuccessTip.create(affected);
    }


    @BusinessLog(name = "StockFavorite", value = "delete StockFavorite")
    @PostMapping("/favorites/bulk/cancel")
    @ApiOperation("bulk取消收藏")
    public Tip bulkCancelFavorite(@RequestBody Ids ids) {

        Integer affected = 0;

        Long userId = JWTKit.getUserId();

        affected += favoriteService.bulkCancelFavorite(userId,ids);
        return SuccessTip.create(affected);
    }


    @BusinessLog(name = "StockFavorite", value = "delete StockFavorite")
    @DeleteMapping("/favorites/{id}")
    @ApiOperation("取消收藏")
    @Deprecated
    public Tip deleteStockFavorite(@PathVariable Long id) {
        Long userId = JWTKit.getUserId();
        StockFavorite stockFavorite = favoriteService.retrieveMaster(id);
       /* if (stockFavorite.getMemberId().compareTo(userId)!=0 || !(ShiroKit.hasPermission(StockFavoritePermission.STOCKFAVORITE_EDIT))){
            throw new BusinessException(BusinessCode.NoPermission);
        }*/
        Integer result = favoriteService.deleteMaster(id);
        return SuccessTip.create(result);
    }


    @BusinessLog(name = "StockFavorite", value = "delete StockFavorite")
    @GetMapping("/app/favorites")
    @ApiOperation("我的收藏/传递类型")
    public Tip stockFavorite(Page<StockFavorite> page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(name = "stockType", required = false) String stockType) {
        Long userId = JWTKit.getUserId();

        page.setCurrent(pageNum);
        page.setSize(pageSize);

        page.setRecords(favoriteService.myFavorites(page,userId,stockType));
        return SuccessTip.create(page);
    }

    @BusinessLog(name = "StockFavorite", value = "delete StockFavorite")
    @GetMapping("/stocks/favorites")
    @ApiOperation("某个stock收藏/不传递类型默认为收藏的文章列表")
    public Tip stockFavorite(Page<StockFavorite> page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(name = "stockType", required = true) String stockType,
                             @RequestParam(name = "stockId", required = true) Long stockId) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);

        page.setRecords(favoriteService.stockFavorites(page,stockId,stockType));
        return SuccessTip.create(page);
    }




/*    @BusinessLog(name = "StockFavorite", value = "create StockFavorite")
    @ApiOperation("用户收藏商品post提交路径中包含stock的名称 Key stockName")
    @PostMapping("/products/{productId}/favorites")
    public Tip createStockFavorite(@PathVariable Long productId, @RequestBody StockFavoriteModel entity) {
        entity.setStockId(productId);
        entity.setStockType(EvaluationType.Product.toString());
        entity.setMemberId(JWTKit.getUserId(getHttpServletRequest()));

        Integer affected = 0;
        try {
            affected = stockFavoriteService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }*/





/*    @BusinessLog(name = "StockFavorite", value = "update StockFavorite")
    @ApiOperation("用户收藏get提交路径中包含stock货物的id号码")
    @PutMapping("/{id}")
    public Tip updateStockFavorite(@PathVariable Long id, @RequestBody StockFavoriteModel entity) {
        entity.setId(id);
        return SuccessTip.create(stockFavoriteService.updateMaster(entity));
    }*/



    /*@GetMapping("/favorite")
    @ApiOperation("所有收藏")
    public Tip queryStockFavorites(Page<StockFavoriteRecord> page,
                                   @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                   @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                   @RequestParam(name = "id", required = false) Long id,
                                   @RequestParam(name = "memberName", required = false) String memberName,
                                   @RequestParam(name = "stockId", required = false) Long stockId,
                                   @RequestParam(name = "stockName", required = false) String stockName,
                                   @RequestParam(name = "stockType", required = false) String stockType,
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

        StockFavoriteRecord record = new StockFavoriteRecord();
        record.setId(id);
        record.setMemberId(JWTKit.getUserId(getHttpServletRequest()));
        record.setMemberName(memberName);
        record.setStockId(stockId);
        record.setStockName(stockName);
        record.setStockType(stockType);
        record.setCreateTime(createTime);

        page.setRecords(queryStockFavoriteDao.findStockFavoritePage(page, record, orderBy));

        return SuccessTip.create(page);
    }*/





}
