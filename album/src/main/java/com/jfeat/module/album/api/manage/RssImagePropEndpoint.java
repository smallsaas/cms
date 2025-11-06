
package com.jfeat.module.album.api.manage;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.album.api.permission.RssImagePropPermission;
import com.jfeat.module.album.services.domain.dao.QueryRssImagePropDao;
import com.jfeat.module.album.services.domain.model.RssImagePropRecord;
import com.jfeat.module.album.services.domain.service.RssImagePropService;
import com.jfeat.module.album.services.gen.persistence.model.RssImageProp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
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
 * @author Code generator
 * @since 2022-10-21
 */
@RestController
@Api("RssImageProp")
@RequestMapping("/api/adm/album/rssImageProp/rssImageProps")
public class RssImagePropEndpoint {

    @Resource
    RssImagePropService rssImagePropService;

    @Resource
    QueryRssImagePropDao queryRssImagePropDao;


    @BusinessLog(name = "RssImageProp", value = "create RssImageProp")
    @Permission(RssImagePropPermission.RSSIMAGEPROP_NEW)
    @PostMapping
    @ApiOperation(value = "新建 RssImageProp", response = RssImageProp.class)
    public Tip createRssImageProp(@RequestBody RssImageProp entity) {
        Integer affected = 0;
        try {
            affected = rssImagePropService.createMaster(entity);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @Permission(RssImagePropPermission.RSSIMAGEPROP_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 RssImageProp", response = RssImageProp.class)
    public Tip getRssImageProp(@PathVariable Long id) {
        return SuccessTip.create(rssImagePropService.queryMasterModel(queryRssImagePropDao, id));
    }

    @BusinessLog(name = "RssImageProp", value = "update RssImageProp")
    @Permission(RssImagePropPermission.RSSIMAGEPROP_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 RssImageProp", response = RssImageProp.class)
    public Tip updateRssImageProp(@PathVariable Long id, @RequestBody RssImageProp entity) {
        entity.setId(id);
        return SuccessTip.create(rssImagePropService.updateMaster(entity));
    }

    @BusinessLog(name = "RssImageProp", value = "delete RssImageProp")
    @Permission(RssImagePropPermission.RSSIMAGEPROP_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 RssImageProp")
    public Tip deleteRssImageProp(@PathVariable Long id) {
        return SuccessTip.create(rssImagePropService.deleteMaster(id));
    }

    @Permission(RssImagePropPermission.RSSIMAGEPROP_VIEW)
    @ApiOperation(value = "RssImageProp 列表信息", response = RssImagePropRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "pid", dataType = "Long"),
            @ApiImplicitParam(name = "imagePath", dataType = "String"),
            @ApiImplicitParam(name = "note", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryRssImagePropPage(Page<RssImagePropRecord> page,
                                     @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                     @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                     // for tag feature query
                                     @RequestParam(name = "tag", required = false) String tag,
                                     // end tag
                                     @RequestParam(name = "search", required = false) String search,

                                     @RequestParam(name = "pid", required = false) Long pid,

                                     @RequestParam(name = "imagePath", required = false) String imagePath,

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

        RssImagePropRecord record = new RssImagePropRecord();
        record.setPid(pid);
        record.setImagePath(imagePath);
        record.setNote(note);


        List<RssImagePropRecord> rssImagePropPage = queryRssImagePropDao.findRssImagePropPage(page, record, tag, search, orderBy, null, null);


        page.setRecords(rssImagePropPage);

        return SuccessTip.create(page);
    }
}

