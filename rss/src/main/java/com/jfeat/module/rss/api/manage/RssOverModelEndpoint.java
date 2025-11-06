
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
import com.jfeat.module.rss.services.domain.dao.QueryRssDao;
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
import com.jfeat.module.rss.services.domain.model.RssRecord;
import com.jfeat.module.rss.services.gen.crud.model.RssModel;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;

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
 * @since 2022-09-26
 */
@RestController
@Api("Rss")
@RequestMapping("/api/adm/cms/rss/rss/rsses")
public class RssOverModelEndpoint {

    @Resource
    RssOverModelService rssOverModelService;

    @Resource
    QueryRssDao queryRssDao;


    // 要查询[从表]关联数据，取消下行注释
    // @Resource
    // QueryRssItemDao queryRssItemDao;

    @BusinessLog(name = "Rss", value = "create Rss")
    @Permission(RssPermission.RSS_NEW)
    @PostMapping
    @ApiOperation(value = "新建 Rss", response = RssModel.class)
    public Tip createRss(@RequestBody RssModel entity) {
        Integer affected = 0;
        try {
            DefaultFilterResult filterResult = new DefaultFilterResult();
            affected = rssOverModelService.createMaster(entity, filterResult, null, null);
            if (affected > 0) {
                return SuccessTip.create(filterResult.result());
            }
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "Rss", value = "查看 RssModel")
    @Permission(RssPermission.RSS_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 Rss", response = RssModel.class)
    public Tip getRss(@PathVariable Long id) {
        CRUDObject<RssModel> entity = rssOverModelService
                .registerQueryMasterDao(queryRssDao)
                // 要查询[从表]关联数据，取消下行注释
                //.registerQuerySlaveModelListDao(RssItem.class, queryRssItemDao)
                .retrieveMaster(id, null, null, null);

        // sample query for registerQueryMasterDao
        // e.g. <select id="queryMasterModel" resultType="PlanModel">
        //       SELECT t_plan_model.*, t_org.name as orgName
        //       FROM t_plan_model
        //       LEFT JOIN t_org ON t_org.id==t_plan_model.org_id
        //       WHERE t_plan_model.id=#{id}
        //       GROUP BY t_plan_model.id
        //    </select>

        if (entity != null) {
            return SuccessTip.create(entity.toJSONObject());
        } else {
            return SuccessTip.create();
        }

    }

    @BusinessLog(name = "Rss", value = "update Rss")
    @Permission(RssPermission.RSS_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 Rss", response = RssModel.class)
    public Tip updateRss(@PathVariable Long id, @RequestBody RssModel entity) {
        entity.setId(id);
        // use update flags
        int newOptions = META.UPDATE_CASCADING_DELETION_FLAG;  //default to delete not exist items
        // newOptions = FlagUtil.setFlag(newOptions, META.UPDATE_ALL_COLUMNS_FLAG);

        return SuccessTip.create(rssOverModelService.updateMaster(entity, null, null, null, newOptions));
    }

    @BusinessLog(name = "Rss", value = "delete Rss")
    @Permission(RssPermission.RSS_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 Rss")
    public Tip deleteRss(@PathVariable Long id) {
        return SuccessTip.create(rssOverModelService.deleteMaster(id));
    }

    @Permission(RssPermission.RSS_VIEW)
    @ApiOperation(value = "Rss 列表信息", response = RssRecord.class)
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
    public Tip queryRssPage(Page<RssRecord> page,
                            @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            // for tag feature query
                            @RequestParam(name = "tag", required = false) String tag,
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


        List<RssRecord> rssPage = queryRssDao.findRssPage(page, record, tag, search, orderBy, null, null);


        page.setRecords(rssPage);

        return SuccessTip.create(page);
    }
}

