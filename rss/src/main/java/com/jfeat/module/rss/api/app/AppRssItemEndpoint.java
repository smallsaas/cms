package com.jfeat.module.rss.api.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.rss.api.permission.RssItemPermission;
import com.jfeat.module.rss.services.domain.dao.QueryRssItemDao;
import com.jfeat.module.rss.services.domain.model.RssItemRecord;
import com.jfeat.module.rss.services.domain.service.RssItemService;
import com.jfeat.module.rss.services.gen.crud.model.RssItemModel;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@RestController
@Api("RssItem")
@RequestMapping("/api/cms/rss/rssItem")
public class AppRssItemEndpoint {


    @Resource
    RssItemService rssItemService;

    @Resource
    QueryRssItemDao queryRssItemDao;


    @GetMapping("/{id}")
    @ApiOperation(value = "查看 RssItem", response = RssItem.class)
    public Tip getRssItem(@PathVariable Long id) {
        return SuccessTip.create(rssItemService.queryMasterModel(queryRssItemDao, id));
    }

    @GetMapping("/content/{id}")
    @ApiOperation(value = "查看 RssItem", response = RssItem.class)
    public Tip getRssItemContent(@PathVariable Long id) {
        RssItemModel rssItemModel = rssItemService.queryMasterModel(queryRssItemDao, id);
        if (rssItemModel!=null){
            return SuccessTip.create(rssItemModel.getTitle());
        }
        return SuccessTip.create();
    }


    @ApiOperation(value = "RssItem 列表信息", response = RssItemRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "pid", dataType = "Long"),
            @ApiImplicitParam(name = "title", dataType = "String"),
            @ApiImplicitParam(name = "pictures", dataType = "String"),
            @ApiImplicitParam(name = "status", dataType = "Integer"),
            @ApiImplicitParam(name = "sortNumber", dataType = "Integer"),
            @ApiImplicitParam(name = "note", dataType = "String"),
            @ApiImplicitParam(name = "createTime", dataType = "Date"),
            @ApiImplicitParam(name = "updateTime", dataType = "Date"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryRssItemPage(Page<RssItemRecord> page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                // for tag feature query
                                @RequestParam(name = "tag", required = false) String tag,
                                // end tag
                                @RequestParam(name = "search", required = false) String search,

                                @RequestParam(name = "pid", required = false) Long pid,

                                @RequestParam(name = "title", required = false) String title,

                                @RequestParam(name = "pictures", required = false) String pictures,

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

        RssItemRecord record = new RssItemRecord();
        record.setPid(pid);
        record.setTitle(title);
        record.setPictures(pictures);
        record.setStatus(status);
        record.setSortNumber(sortNumber);
        record.setNote(note);
        record.setCreateTime(createTime);
        record.setUpdateTime(updateTime);


        List<RssItemRecord> rssItemPage = queryRssItemDao.findRssItemPage(page, record, tag, search, orderBy, null, null);


        page.setRecords(rssItemPage);

        return SuccessTip.create(page);
    }
}
