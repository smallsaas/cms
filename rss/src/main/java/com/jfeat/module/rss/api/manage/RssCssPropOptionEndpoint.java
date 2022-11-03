
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
import com.jfeat.module.rss.services.domain.dao.QueryRssCssPropOptionDao;
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
import com.jfeat.module.rss.services.domain.model.RssCssPropOptionRecord;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssPropOption;

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
@Api("RssCssPropOption")
@RequestMapping("/api/crud/rss/rssCssPropOption/rssCssPropOptions")
public class RssCssPropOptionEndpoint {

    @Resource
    RssCssPropOptionService rssCssPropOptionService;

    @Resource
    QueryRssCssPropOptionDao queryRssCssPropOptionDao;


    @BusinessLog(name = "RssCssPropOption", value = "create RssCssPropOption")
    @Permission(RssCssPropOptionPermission.RSSCSSPROPOPTION_NEW)
    @PostMapping
    @ApiOperation(value = "新建 RssCssPropOption", response = RssCssPropOption.class)
    public Tip createRssCssPropOption(@RequestBody RssCssPropOption entity) {
        Integer affected = 0;
        try {
            affected = rssCssPropOptionService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(RssCssPropOptionPermission.RSSCSSPROPOPTION_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 RssCssPropOption", response = RssCssPropOption.class)
    public Tip getRssCssPropOption(@PathVariable Long id) {
        return SuccessTip.create(rssCssPropOptionService.queryMasterModel(queryRssCssPropOptionDao, id));
    }

    @BusinessLog(name = "RssCssPropOption", value = "update RssCssPropOption")
    @Permission(RssCssPropOptionPermission.RSSCSSPROPOPTION_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 RssCssPropOption", response = RssCssPropOption.class)
    public Tip updateRssCssPropOption(@PathVariable Long id, @RequestBody RssCssPropOption entity) {
        entity.setId(id);
        return SuccessTip.create(rssCssPropOptionService.updateMaster(entity));
    }

    @BusinessLog(name = "RssCssPropOption", value = "delete RssCssPropOption")
    @Permission(RssCssPropOptionPermission.RSSCSSPROPOPTION_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 RssCssPropOption")
    public Tip deleteRssCssPropOption(@PathVariable Long id) {
        return SuccessTip.create(rssCssPropOptionService.deleteMaster(id));
    }

    @Permission(RssCssPropOptionPermission.RSSCSSPROPOPTION_VIEW)
    @ApiOperation(value = "RssCssPropOption 列表信息", response = RssCssPropOptionRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "css_namePropsId", dataType = "Long"),
            @ApiImplicitParam(name = "propName", dataType = "String"),
            @ApiImplicitParam(name = "propOption", dataType = "String"),
            @ApiImplicitParam(name = "note", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryRssCssPropOptionPage(Page<RssCssPropOptionRecord> page,
                                         @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                         // for tag feature query
                                         @RequestParam(name = "tag", required = false) String tag,
                                         // end tag
                                         @RequestParam(name = "search", required = false) String search,

                                         @RequestParam(name = "cssNamePropsId", required = false) Long cssNamePropsId,

                                         @RequestParam(name = "propName", required = false) String propName,

                                         @RequestParam(name = "propOption", required = false) String propOption,

                                         @RequestParam(name = "note", required = false) String note,
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

        RssCssPropOptionRecord record = new RssCssPropOptionRecord();
        record.setCssNamePropsId(cssNamePropsId);
        record.setPropName(propName);
        record.setPropOption(propOption);
        record.setNote(note);


        List<RssCssPropOptionRecord> rssCssPropOptionPage = queryRssCssPropOptionDao.findRssCssPropOptionPage(page, record, tag, search, orderBy, null, null);


        page.setRecords(rssCssPropOptionPage);

        return SuccessTip.create(page);
    }
}

