package com.jfeat.module.rss.api.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.am.crud.tag.services.domain.service.StockTagRelationService;
import com.jfeat.am.crud.tag.services.persistence.dao.StockTagMapper;
import com.jfeat.am.crud.tag.services.persistence.dao.StockTagRelationMapper;
import com.jfeat.am.crud.tag.services.persistence.model.StockTag;
import com.jfeat.am.crud.tag.services.persistence.model.StockTagRelation;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.rss.services.domain.dao.QueryRssCssNamedPropsDao;
import com.jfeat.module.rss.services.domain.model.RssCssNamedPropsRecord;
import com.jfeat.module.rss.services.domain.service.RssOverModelService;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssNamedProps;
import com.jfeat.module.rss.services.gen.persistence.model.RssTag;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/u/rss/tag")
public class AppRssTagEndpoint {

    @Resource
    StockTagMapper stockTagMapper;

    @Resource
    StockTagRelationMapper stockTagRelationMapper;

    @Resource
    StockTagRelationService stockTagRelationService;

    @Resource
    RssOverModelService rssOverModelService;

    @Resource
    QueryRssCssNamedPropsDao queryRssCssNamedPropsDao;

    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "rssId", dataType = "Long"),
            @ApiImplicitParam(name = "other", dataType = "String"),
    })
    public Tip getTagList(@RequestParam(name = "tagType", required = false, defaultValue = "Rss") String tagType,
                          @RequestParam(name = "rssId",required = false) Long rssId,
                          @RequestParam(name = "other",required = false) String other) {

        QueryWrapper<StockTag> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StockTag.TAG_TYPE, tagType);
        List<StockTag> stockTagList = stockTagMapper.selectList(queryWrapper);

        QueryWrapper<StockTagRelation> stockTagRelationQueryWrapper = new QueryWrapper<>();
        List<StockTagRelation> stockTagRelationList = new ArrayList<>();
        if (rssId!=null && rssId>0){
            stockTagRelationQueryWrapper.eq(StockTagRelation.STOCK_ID,rssId).eq(StockTagRelation.STOCK_TYPE,tagType);
            stockTagRelationList = stockTagRelationMapper.selectList(stockTagRelationQueryWrapper);
        }

        List<Long> tagIds = new ArrayList<>();

        if (stockTagRelationList!=null && stockTagList.size()>0){
            tagIds = stockTagRelationList.stream().map(StockTagRelation::getTagId).collect(Collectors.toList());
        }


        List<RssTag> rssTagList = new ArrayList<>();
        if (stockTagList!=null && stockTagList.size()>0 ){
            for (StockTag stockTag:stockTagList){
                RssTag rssTag = new RssTag();
                rssTag.setId(stockTag.getId());
                rssTag.setTagName(stockTag.getTagName());
                rssTag.setTagType(stockTag.getTagType());
                rssTag.setIsPrimary(stockTag.getIsPrimary());
                rssTag.setSortOrder(stockTag.getSortOrder());
                if (rssId!=null && rssId>0){
                    rssTag.setRssId(rssId);

                    if (tagIds!=null&&tagIds.size()>0 && tagIds.contains(rssId)){
                        rssTag.setSelected(Boolean.TRUE);
                    }
                }
                rssTagList.add(rssTag);
            }
        }

        if (other!=null && !other.equals("")){
            RssTag rssTag = new RssTag();
            rssTag.setId(-1L);
            rssTag.setTagName(other);
            rssTagList.add(rssTag);
        }

        return SuccessTip.create(rssTagList);
    }



//    添加
    @PostMapping
    @ApiOperation(value = "添加rssTag",response = RssTag.class)
    public Tip createRssTag(@RequestBody RssTag entity){

        Integer affected = 0;
        if (entity.getId()==null || entity.getRssId()==null){
            throw new BusinessException(BusinessCode.BadRequest,"id or resId Can't be empty");
        }
        QueryWrapper<StockTagRelation> stockTagRelationQueryWrapper = new QueryWrapper<>();
        stockTagRelationQueryWrapper.eq(StockTagRelation.STOCK_ID,entity.getRssId());
        stockTagRelationQueryWrapper.eq(StockTagRelation.STOCK_TYPE,rssOverModelService.getEntityName());
        stockTagRelationQueryWrapper.eq(StockTagRelation.TAG_ID,entity.getId());

        StockTagRelation selectOne = stockTagRelationMapper.selectOne(stockTagRelationQueryWrapper);

        if (selectOne==null){
            StockTagRelation stockTagRelation = new StockTagRelation();
            stockTagRelation.setStockId(entity.getRssId());
            stockTagRelation.setTagId(entity.getId());
            stockTagRelation.setStockType(rssOverModelService.getEntityName());
            affected+=stockTagRelationMapper.insert(stockTagRelation);
        }

        return SuccessTip.create(affected);
    }



//    删除
    @DeleteMapping("/{id}/{rssId}")
    @ApiOperation(value = "删除Rss 标签")
    public Tip deleteRssTag(@PathVariable("id")Long id,@PathVariable("rssId")Long rssId){
        QueryWrapper<StockTagRelation> stockTagRelationQueryWrapper = new QueryWrapper<>();
        stockTagRelationQueryWrapper.eq(StockTagRelation.STOCK_ID,rssId);
        stockTagRelationQueryWrapper.eq(StockTagRelation.STOCK_TYPE,rssOverModelService.getEntityName());
        stockTagRelationQueryWrapper.eq(StockTagRelation.TAG_ID,id);
        return SuccessTip.create(stockTagRelationMapper.delete(stockTagRelationQueryWrapper));
    }

    @PostMapping("/bulk")
    @ApiOperation(value = "批量修改和添加 rss标签",response = RssTag.class)
    public Tip updateRssTag(@RequestBody List<RssTag> entity){
        Integer affected = 0;
        if (entity!=null && entity.size()>0){
            List<StockTagRelation> stockTagRelationList = new ArrayList<>();
            for (RssTag rssTag:entity){
                StockTagRelation stockTagRelation = new StockTagRelation();
                stockTagRelation.setStockId(rssTag.getRssId());
                stockTagRelation.setTagId(rssTag.getId());
                stockTagRelation.setStockType(rssOverModelService.getEntityName());
                stockTagRelationList.add(stockTagRelation);
            }
            if (stockTagRelationList!=null && stockTagRelationList.size()>0){
                affected+=stockTagRelationService.updateRelations(stockTagRelationList.get(0).getStockId(),stockTagRelationList.get(0).getStockType(),stockTagRelationList);
            }

        }
        return SuccessTip.create(affected);
    }


}
