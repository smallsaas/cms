
package com.jfeat.module.rss.api.manage;


import com.jfeat.crud.plus.META;
import com.jfeat.am.core.jwt.JWTKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.module.rss.services.domain.dao.QueryRssComponentPropDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.module.rss.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import java.math.BigDecimal;

import com.jfeat.module.rss.services.domain.service.*;
import com.jfeat.module.rss.services.domain.model.RssComponentPropRecord;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONArray;

/**
 * <p>
 * api
 * </p>
 *
 * @author Code generator
 * @since 2022-09-30
 */
@RestController
@Api("RssComponentProp")
@RequestMapping("/api/crud/rss/rssComponentProp/rssComponentProps")
public class RssComponentPropEndpoint {

    @Resource
    RssComponentPropService rssComponentPropService;

    @Resource
    QueryRssComponentPropDao queryRssComponentPropDao;


    @BusinessLog(name = "RssComponentProp", value = "create RssComponentProp")
    @Permission(RssComponentPropPermission.RSSCOMPONENTPROP_NEW)
    @PostMapping
    @ApiOperation(value = "新建 RssComponentProp", response = RssComponentProp.class)
    public Tip createRssComponentProp(@RequestBody RssComponentProp entity) {
        Integer affected = 0;
        try {
            affected = rssComponentPropService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(RssComponentPropPermission.RSSCOMPONENTPROP_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 RssComponentProp", response = RssComponentProp.class)
    public Tip getRssComponentProp(@PathVariable Long id) {
        return SuccessTip.create(rssComponentPropService.queryMasterModel(queryRssComponentPropDao, id));
    }

    @BusinessLog(name = "RssComponentProp", value = "update RssComponentProp")
    @Permission(RssComponentPropPermission.RSSCOMPONENTPROP_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 RssComponentProp", response = RssComponentProp.class)
    public Tip updateRssComponentProp(@PathVariable Long id, @RequestBody RssComponentProp entity) {
        entity.setId(id);
        return SuccessTip.create(rssComponentPropService.updateMaster(entity));
    }

    @BusinessLog(name = "RssComponentProp", value = "delete RssComponentProp")
    @Permission(RssComponentPropPermission.RSSCOMPONENTPROP_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 RssComponentProp")
    public Tip deleteRssComponentProp(@PathVariable Long id) {
        return SuccessTip.create(rssComponentPropService.deleteMaster(id));
    }

    @Permission(RssComponentPropPermission.RSSCOMPONENTPROP_VIEW)
    @ApiOperation(value = "RssComponentProp 列表信息", response = RssComponentPropRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "componentId", dataType = "Long"),
            @ApiImplicitParam(name = "propName", dataType = "String"),
            @ApiImplicitParam(name = "propTips", dataType = "String"),
            @ApiImplicitParam(name = "propDefaultValue", dataType = "String"),
            @ApiImplicitParam(name = "dataType", dataType = "String"),
            @ApiImplicitParam(name = "optionName", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryRssComponentPropPage(Page<RssComponentPropRecord> page,
                                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                         // for tag feature query
                                         @RequestParam(name = "tag", required = false) String tag,
                                         // end tag
                                         @RequestParam(name = "search", required = false) String search,

                                         @RequestParam(name = "componentId", required = false) Long componentId,

                                         @RequestParam(name = "propName", required = false) String propName,

                                         @RequestParam(name = "propTips", required = false) String propTips,

                                         @RequestParam(name = "propDefaultValue", required = false) String propDefaultValue,

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

        RssComponentPropRecord record = new RssComponentPropRecord();
        record.setComponentId(componentId);
        record.setPropName(propName);
        record.setPropTips(propTips);
        record.setPropDefaultValue(propDefaultValue);
        record.setDataType(dataType);
        record.setOptionName(optionName);


        List<RssComponentPropRecord> rssComponentPropPage = queryRssComponentPropDao.findRssComponentPropPage(page, record, tag, search, orderBy, null, null);


        page.setRecords(rssComponentPropPage);

        return SuccessTip.create(page);
    }
}

