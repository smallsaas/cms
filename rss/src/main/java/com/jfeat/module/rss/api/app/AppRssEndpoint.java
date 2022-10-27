package com.jfeat.module.rss.api.app;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.crud.tag.services.persistence.dao.StockTagRelationMapper;
import com.jfeat.am.crud.tag.services.persistence.model.StockTagRelation;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.META;
import com.jfeat.crud.plus.annotation.SQLTag;
import com.jfeat.module.rss.services.domain.dao.QueryRssComponentDao;
import com.jfeat.module.rss.services.domain.dao.QueryRssDao;
import com.jfeat.module.rss.services.domain.dao.QueryRssItemDao;
import com.jfeat.module.rss.services.domain.model.RssRecord;
import com.jfeat.module.rss.services.domain.service.*;
import com.jfeat.module.rss.services.gen.crud.model.RssModel;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Api("Rss")
@RequestMapping("/api/u/rss/master")
public class AppRssEndpoint {

    @Resource
    RssOverModelService rssOverModelService;

    @Resource
    QueryRssDao queryRssDao;


    @Resource
    ComponentTypeRegexService componentTypeRegexService;

    @Resource
    QueryRssItemDao queryRssItemDao;

    @Resource
    QueryRssComponentDao queryRssComponentDao;

    @Resource
    RssItemService rssItemService;

    @Resource
    RssComponentService rssComponentService;

    @Resource
    StockTagRelationMapper stockTagRelationMapper;

    @Resource
    RssStyleControl rssStyleControl;


    // 要查询[从表]关联数据，取消下行注释
    // @Resource
    // QueryRssItemDao queryRssItemDao;

    @PostMapping
    @ApiOperation(value = "新建 Rss", response = RssModel.class)
    public Tip createRss(@RequestBody List<RssItem> rssItemModelList) {
        Integer affected = 0;
        RssModel rssModel = new RssModel();
        String uuid = UUID.randomUUID().toString();
        rssModel.setUuid(uuid);
        rssModel.setName(uuid);
        affected = rssOverModelService.createRss(rssModel,rssItemModelList);

        return SuccessTip.create(affected);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "查询rss详情")
    public Tip getPreView(@PathVariable("id")Long id){
        RssRecord record = new RssRecord();
        record.setId(id);
        List<RssRecord> recordList = queryRssDao.queryRssWithItem(null, record, null, null, null, null, null);
        if (recordList!=null && recordList.size()==1){
            rssStyleControl.andRssStyleValue(recordList);
            return SuccessTip.create(recordList.get(0));
        }
        return SuccessTip.create();
    }



//    @PutMapping("/{id}")
//    @ApiOperation(value = "修改 Rss", response = RssModel.class)
//    public Tip updateRss(@PathVariable Long id, @RequestBody RssModel entity) {
//        entity.setId(id);
//        if (entity.getItems()==null){
//            throw new BusinessException(BusinessCode.BadRequest,"items不能为空");
//        }else {
//            for (RssItem rssItem:entity.getItems()){
//                rssItem.setPid(entity.getId());
//            }
//        }
//        // use update flags
//        int newOptions = META.UPDATE_CASCADING_DELETION_FLAG;  //default to delete not exist items
//        // newOptions = FlagUtil.setFlag(newOptions, META.UPDATE_ALL_COLUMNS_FLAG);
//
//        return SuccessTip.create(rssOverModelService.updateMaster(entity, null, null, null, newOptions));
//    }

//    @PutMapping("/{id}")
//    @ApiOperation(value = "修改 Rss", response = RssModel.class)
//    public Tip updateRss(@PathVariable Long id, @RequestBody RssModel entity) {
//        entity.setId(id);
//        if (entity.getItems()==null){
//            throw new BusinessException(BusinessCode.BadRequest,"items不能为空");
//        }else {
//            for (RssItem rssItem:entity.getItems()){
//                rssItem.setPid(entity.getId());
//            }
//        }
//        // use update flags
//        int newOptions = META.UPDATE_CASCADING_DELETION_FLAG;  //default to delete not exist items
//        // newOptions = FlagUtil.setFlag(newOptions, META.UPDATE_ALL_COLUMNS_FLAG);
//        return SuccessTip.create(rssOverModelService.updateMaster(entity, null, null, null, newOptions));
//    }

    @PutMapping("/{id}")
    @ApiOperation(value = "更新 Rss 并重新解析值",response = RssRecord.class)
    public Tip updatePreview(@PathVariable Long id,@RequestBody RssRecord rssRecord){
        rssRecord.setId(id);
        return SuccessTip.create(rssOverModelService.updateAndParseRss(rssRecord));
    }


    @DeleteMapping("/{id}")
    @ApiOperation("删除 Rss")
    public Tip deleteRss(@PathVariable Long id) {
        return SuccessTip.create(rssOverModelService.deleteRss(id));
    }



    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "uuid", dataType = "String"),
            @ApiImplicitParam(name = "orgId", dataType = "Long"),
            @ApiImplicitParam(name = "name", dataType = "String"),
            @ApiImplicitParam(name = "status", dataType = "Integer"),
            @ApiImplicitParam(name = "sortNumber", dataType = "Integer"),
            @ApiImplicitParam(name = "note", dataType = "String"),
            @ApiImplicitParam(name = "createTime", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", dataType = "Date"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    @ApiOperation(value = "Rss 列表信息", response = RssRecord.class)
    public Tip queryRssPage(Page<RssRecord> page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            // for tag feature query
                            @RequestParam(name = "tag", required = false) String tag,
                            @RequestParam(name = "tagId", required = false) Long tagId,
                            // end tag
                            @RequestParam(name = "search", required = false) String search,

                            @RequestParam(name = "uuid", required = false) String uuid,

                            @RequestParam(name = "orgId", required = false) Long orgId,

                            @RequestParam(name = "name", required = false) String name,

                            @RequestParam(name = "status", required = false) Integer status,

                            @RequestParam(name = "sortNumber", required = false) Integer sortNumber,

                            @RequestParam(name = "note", required = false) String note,

                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                            @RequestParam(name = "createTime", required = false) Date createTime,

                            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                            @RequestParam(name = "updateTime", required = false) Date updateTime,
                            @RequestParam(name = "orderBy", required = false) String orderBy,
                            @RequestParam(name = "sort", required = false) String sort) {

        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String sortPattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(sortPattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }


        page.setCurrent(pageNum);
        page.setSize(pageSize);

        RssRecord record = new RssRecord();
        record.setUuid(uuid);
        record.setOrgId(orgId);
        record.setName(name);
        record.setStatus(status);
        record.setSortNumber(sortNumber);
        record.setNote(note);
        record.setCreateTime(createTime);
        record.setUpdateTime(updateTime);

        List<RssRecord> rssPage = new ArrayList<>();
        if (tagId!=null && tagId==-1){
            List<Long> ids = new ArrayList<>();
            QueryWrapper<StockTagRelation> stockTagRelationQueryWrapper = new QueryWrapper<>();
            stockTagRelationQueryWrapper.eq(StockTagRelation.STOCK_TYPE,rssOverModelService.getEntityName());
            List<StockTagRelation> stockTagRelationList = stockTagRelationMapper.selectList(stockTagRelationQueryWrapper);
            ids = stockTagRelationList.stream().map(StockTagRelation::getStockId).collect(Collectors.toList());
            rssPage = queryRssDao.queryRssWithItemNotComponentAndTag(page, record, ids,tag, search, orderBy, null, null);
        }else {
            rssPage = queryRssDao.queryRssWithItemNotComponent(page, record, tag, search, orderBy, null, null);
        }

        page.setRecords(rssPage);

        return SuccessTip.create(page);
    }



    @PostMapping("/preview")
    @ApiOperation(value = "更新 Rss 并重新解析值",response = RssRecord.class)
    public Tip updatePreview(@RequestBody RssRecord rssRecord){
        return SuccessTip.create(rssOverModelService.updateAndParseRss(rssRecord));
    }


    @PostMapping("/uploadRss")
    public SuccessTip uploadRssFile(@RequestParam(value = "file") MultipartFile file) {
        //判断是否为空
        if (file == null || file.isEmpty()) {
           throw new BusinessException(BusinessCode.EmptyNotAllowed,"file is empty");
        }
        String rssItemString =  rssOverModelService.uploadRssFile(file);

        Integer affected = 0;
        RssModel rssModel = new RssModel();
        String uuid = UUID.randomUUID().toString();
        rssModel.setUuid(uuid);
        rssModel.setName(uuid);

        List<RssItem> rssItemModelList = new ArrayList<>();
        RssItem rssItem = new RssItem();
        rssItem.setTitle(rssItemString);
        rssItemModelList.add(rssItem);


        affected = rssOverModelService.createRss(rssModel,rssItemModelList);

        //读取成功返回文件名称
        return SuccessTip.create(affected);
    }



}
