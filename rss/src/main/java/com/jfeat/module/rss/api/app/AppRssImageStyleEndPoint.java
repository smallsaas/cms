package com.jfeat.module.rss.api.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.crud.plus.META;
import com.jfeat.module.rss.api.permission.RssImageNamePermission;
import com.jfeat.module.rss.services.domain.dao.QueryRssImageNameDao;
import com.jfeat.module.rss.services.domain.model.RssImageNameRecord;
import com.jfeat.module.rss.services.domain.model.RssImagePropRecord;
import com.jfeat.module.rss.services.domain.service.RssImageNameOverModelService;
import com.jfeat.module.rss.services.gen.crud.model.RssImageNameModel;
import com.jfeat.module.rss.services.gen.persistence.dao.RssImageNameMapper;
import com.jfeat.module.rss.services.gen.persistence.model.RssImageName;
import com.jfeat.module.rss.services.gen.persistence.model.RssImageProp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RestController
@Api("RssImageStyle")
@RequestMapping("/api/u/rss/imageStyle")
public class AppRssImageStyleEndPoint {
    @Resource
    RssImageNameOverModelService rssImageNameOverModelService;

    @Resource
    QueryRssImageNameDao queryRssImageNameDao;

    @Resource
    RssImageNameMapper rssImageNameMapper;


    // 要查询[从表]关联数据，取消下行注释
    // @Resource
    // QueryRssImagePropDao queryRssImagePropDao;

    @BusinessLog(name = "RssImageName", value = "create RssImageName")
    @PostMapping
    @ApiOperation(value = "新建 RssImageName", response = RssImageNameModel.class)
    public Tip createRssImageName(@RequestBody RssImageNameModel entity) {
        Integer affected = 0;
        try {
            DefaultFilterResult filterResult = new DefaultFilterResult();
            affected = rssImageNameOverModelService.createMaster(entity, filterResult, null, null);
            if (affected > 0) {
                return SuccessTip.create(filterResult.result());
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "RssImageName", value = "create RssImageName")
    @PostMapping("/style")
    @ApiOperation(value = "新建 RssImageName", response = RssImageNameModel.class)
    public Tip createRssImageNameStyle(@RequestBody RssImageNameModel entity) {
        return SuccessTip.create(rssImageNameOverModelService.createRssImageNameItem(entity));
    }

    @BusinessLog(name = "RssImageName", value = "查看 RssImageNameModel")
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 RssImageName", response = RssImageNameModel.class)
    public Tip getRssImageName(@PathVariable Long id) {
        return SuccessTip.create(rssImageNameOverModelService.getRssImageNameModelById(id));
    }


    @BusinessLog(name = "RssImageName", value = "查看 RssImageNameModel")
    @GetMapping("/style/name")
    @ApiOperation(value = "查看 RssImageName", response = RssImageNameModel.class)
    public Tip getRssImageNameByName(@RequestParam(name = "name",required = true) String name) {

        QueryWrapper<RssImageName> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RssImageName.NAME,name);
        RssImageName rssImageName = rssImageNameMapper.selectOne(queryWrapper);
        if (rssImageName!=null){
            return SuccessTip.create(rssImageNameOverModelService.getRssImageNameModelById(rssImageName.getId()));
        }
        return SuccessTip.create();

    }

    @BusinessLog(name = "RssImageName", value = "update RssImageName")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 RssImageName", response = RssImageNameModel.class)
    public Tip updateRssImageName(@PathVariable Long id, @RequestBody RssImageNameModel entity) {
        entity.setId(id);
        int newOptions = META.UPDATE_CASCADING_DELETION_FLAG;  //default to delete not exist items

        return SuccessTip.create(rssImageNameOverModelService.updateMaster(entity, null, null, null, newOptions));
    }

    @BusinessLog(name = "RssImageName", value = "delete RssImageName")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 RssImageName")
    public Tip deleteRssImageName(@PathVariable Long id) {
        return SuccessTip.create(rssImageNameOverModelService.deleteMaster(id));
    }

    @ApiOperation(value = "RssImageName 列表信息", response = RssImageNameRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "name", dataType = "String"),
            @ApiImplicitParam(name = "orgId", dataType = "Long"),
            @ApiImplicitParam(name = "category", dataType = "String"),
            @ApiImplicitParam(name = "state", dataType = "Integer"),
            @ApiImplicitParam(name = "note", dataType = "String"),
            @ApiImplicitParam(name = "createTime", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", dataType = "Date"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryRssImageNamePage(Page<RssImageNameRecord> page,
                                     @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                     // for tag feature query
                                     @RequestParam(name = "tag", required = false) String tag,
                                     // end tag
                                     @RequestParam(name = "search", required = false) String search,

                                     @RequestParam(name = "name", required = false) String name,

                                     @RequestParam(name = "orgId", required = false) Long orgId,

                                     @RequestParam(name = "category", required = false) String category,

                                     @RequestParam(name = "state", required = false) Integer state,

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

        RssImageNameRecord record = new RssImageNameRecord();
        record.setName(name);
        record.setCategory(category);
        record.setState(state);
        record.setNote(note);
        record.setCreateTime(createTime);
        record.setUpdateTime(updateTime);


        List<RssImageNameRecord> rssImageNamePage = queryRssImageNameDao.findRssImageNamePage(page, record, tag, search, orderBy, null, null);
        page.setRecords(rssImageNamePage);
        return SuccessTip.create(page);
    }

    @GetMapping("/test")
    public Tip rssTest(){
        RssImageNameRecord record = new RssImageNameRecord();
        return SuccessTip.create(rssImageNameOverModelService.getAllRssImageToMap());
    }
}
