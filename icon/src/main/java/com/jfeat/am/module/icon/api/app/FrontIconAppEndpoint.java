package com.jfeat.am.module.icon.api.app;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.am.module.icon.api.permission.FrontIconPermission;
import com.jfeat.am.module.icon.services.domain.dao.QueryFrontIconDao;
import com.jfeat.am.module.icon.services.domain.model.FrontIconRecord;
import com.jfeat.am.module.icon.services.domain.service.FrontIconService;
import com.jfeat.am.module.icon.services.gen.persistence.model.FrontIcon;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
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
 * @since 2022-09-02
 */
@RestController

@Api("FrontIcon")
@RequestMapping("/api/u/frontIcon")
public class FrontIconAppEndpoint {

    @Resource
    FrontIconService frontIconService;


    @Resource
    QueryFrontIconDao queryFrontIconDao;

    @BusinessLog(name = "FrontIcon", value = "create FrontIcon")
    @PostMapping
    @ApiOperation(value = "新建 FrontIcon", response = FrontIcon.class)
    public Tip createFrontIcon(@RequestBody FrontIcon entity) {

        Integer affected = 0;
        try {
            affected = frontIconService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查看 FrontIcon", response = FrontIcon.class)
    public Tip getFrontIcon(@PathVariable Long id) {
        return SuccessTip.create(frontIconService.queryMasterModel(queryFrontIconDao, id));
    }

    @BusinessLog(name = "FrontIcon", value = "update FrontIcon")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 FrontIcon", response = FrontIcon.class)
    public Tip updateFrontIcon(@PathVariable Long id, @RequestBody FrontIcon entity) {
        entity.setId(id);
        return SuccessTip.create(frontIconService.updateMaster(entity));
    }

    @BusinessLog(name = "FrontIcon", value = "delete FrontIcon")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 FrontIcon")
    public Tip deleteFrontIcon(@PathVariable Long id) {
        return SuccessTip.create(frontIconService.deleteMaster(id));
    }

    @ApiOperation(value = "FrontIcon 列表信息", response = FrontIconRecord.class)
    @GetMapping
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", dataType = "Integer"),
            @ApiImplicitParam(name = "search", dataType = "String"),
            @ApiImplicitParam(name = "id", dataType = "Long"),
            @ApiImplicitParam(name = "icon", dataType = "String"),
            @ApiImplicitParam(name = "name", dataType = "String"),
            @ApiImplicitParam(name = "orderBy", dataType = "String"),
            @ApiImplicitParam(name = "sort", dataType = "String")
    })
    public Tip queryFrontIcons(Page<FrontIconRecord> page,
                               @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                               @RequestParam(name = "search", required = false) String search,
                               @RequestParam(name = "id", required = false) Long id,

                               @RequestParam(name = "icon", required = false) String icon,

                               @RequestParam(name = "name", required = false) String name,
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

        FrontIconRecord record = new FrontIconRecord();
        record.setId(id);
        record.setIcon(icon);
        record.setName(name);


        List<FrontIconRecord> frontIconPage = queryFrontIconDao.findFrontIconPage(page, record, search, orderBy, null, null);

        page.setRecords(frontIconPage);

        return SuccessTip.create(page);
    }
}
