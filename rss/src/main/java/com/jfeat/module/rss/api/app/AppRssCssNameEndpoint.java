package com.jfeat.module.rss.api.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.rss.api.permission.RssCssNamedPropsPermission;
import com.jfeat.module.rss.services.domain.dao.QueryRssCssNamedPropsDao;
import com.jfeat.module.rss.services.domain.model.RssCssNamedPropsRecord;
import com.jfeat.module.rss.services.domain.service.RssCssNamedPropsService;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssNamedProps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api("Rss")
@RequestMapping("/api/u/rss/cssStyle")
public class AppRssCssNameEndpoint {
    @Resource
    RssCssNamedPropsService rssCssNamedPropsService;
    @Resource
    QueryRssCssNamedPropsDao queryRssCssNamedPropsDao;
    @BusinessLog(name = "RssCssNamedProps", value = "create RssCssNamedProps")
    @PostMapping
    @ApiOperation(value = "新建 RssCssNamedProps", response = RssCssNamedProps.class)
    public Tip createRssCssNamedProps(@RequestBody RssCssNamedProps entity) {

        return SuccessTip.create(rssCssNamedPropsService.createRssNameProp(entity));
    }
    @Permission(RssCssNamedPropsPermission.RSSCSSNAMEDPROPS_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 RssCssNamedProps", response = RssCssNamedProps.class)
    public Tip getRssCssNamedProps(@PathVariable Long id) {

        RssCssNamedPropsRecord record = new RssCssNamedPropsRecord();
        record.setId(id);


        List<RssCssNamedProps> rssCssNamedPropsPage = queryRssCssNamedPropsDao.findRssCssNamedPropsWithItems(null, record, null, null, null, null, null);
        if (rssCssNamedPropsPage!=null && rssCssNamedPropsPage.size()==1){
            return SuccessTip.create(rssCssNamedPropsPage.get(0));
        }

        return SuccessTip.create(null);
    }

    @BusinessLog(name = "RssCssNamedProps", value = "update RssCssNamedProps")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 RssCssNamedProps", response = RssCssNamedProps.class)
    public Tip updateRssCssNamedProps(@PathVariable Long id, @RequestBody RssCssNamedProps entity) {
        entity.setId(id);
        return SuccessTip.create(rssCssNamedPropsService.updateRssNameProp(entity));
    }

    @BusinessLog(name = "RssCssNamedProps", value = "delete RssCssNamedProps")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 RssCssNamedProps")
    public Tip deleteRssCssNamedProps(@PathVariable Long id) {
        return SuccessTip.create(rssCssNamedPropsService.deleteRssNameProp(id));
    }

    @ApiOperation(value = "RssCssNamedProps 列表信息", response = RssCssNamedPropsRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "rssItemId", dataType = "Long"),
            @ApiImplicitParam(name = "appid", dataType = "String"),
            @ApiImplicitParam(name = "tags", dataType = "String"),
            @ApiImplicitParam(name = "name", dataType = "String"),
            @ApiImplicitParam(name = "cssName", dataType = "String"),
            @ApiImplicitParam(name = "cssValue", dataType = "String"),
            @ApiImplicitParam(name = "dataType", dataType = "String"),
            @ApiImplicitParam(name = "optionName", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryRssCssNamedPropsPage(Page<RssCssNamedProps> page,
                                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                         // for tag feature query
                                         @RequestParam(name = "tag", required = false) String tag,
                                         // end tag
                                         @RequestParam(name = "search", required = false) String search,

                                         @RequestParam(name = "rssItemId", required = false) Long rssItemId,

                                         @RequestParam(name = "appid", required = false) String appid,

                                         @RequestParam(name = "tags", required = false) String tags,

                                         @RequestParam(name = "name", required = false) String name,

                                         @RequestParam(name = "cssName", required = false) String cssName,

                                         @RequestParam(name = "cssValue", required = false) String cssValue,

                                         @RequestParam(name = "dataType", required = false) String dataType,

                                         @RequestParam(name = "optionName", required = false) String optionName,
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

        RssCssNamedPropsRecord record = new RssCssNamedPropsRecord();
        record.setRssItemId(rssItemId);
        record.setAppid(appid);
        record.setTags(tags);
        record.setName(name);
        record.setCssName(cssName);
        record.setCssValue(cssValue);
        record.setDataType(dataType);
        record.setOptionName(optionName);
        List<RssCssNamedProps> rssCssNamedPropsPage = queryRssCssNamedPropsDao.findRssCssNamedPropsWithItems(page, record, tag, search, orderBy, null, null);

        page.setRecords(rssCssNamedPropsPage);

        return SuccessTip.create(page);
    }
}
