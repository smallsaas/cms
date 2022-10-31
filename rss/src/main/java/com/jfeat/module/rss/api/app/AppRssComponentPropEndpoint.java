package com.jfeat.module.rss.api.app;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.common.annotation.Permission;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.module.rss.api.permission.RssComponentPropPermission;
import com.jfeat.module.rss.services.domain.dao.QueryRssComponentPropDao;
import com.jfeat.module.rss.services.domain.model.RssComponentPropRecord;
import com.jfeat.module.rss.services.domain.service.RssComponentPropService;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponentProp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Api("AppRssComponentProp")
@RequestMapping("/api/u/rss/componentProp")
public class AppRssComponentPropEndpoint {
    @Resource
    RssComponentPropService rssComponentPropService;

    @Resource
    QueryRssComponentPropDao queryRssComponentPropDao;


    @BusinessLog(name = "RssComponentProp", value = "create RssComponentProp")
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

    @BusinessLog(name = "RssComponentProp", value = "create RssComponentProp")
    @PostMapping("/batch")
    @ApiOperation(value = "新建 RssComponentProp", response = RssComponentProp.class)
    public Tip BatchCreateRssComponentProp(@RequestBody List<RssComponentProp> entity) {
        return SuccessTip.create( queryRssComponentPropDao.batchAdd(entity));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查看 RssComponentProp", response = RssComponentProp.class)
    public Tip getRssComponentProp(@PathVariable Long id) {
        return SuccessTip.create(rssComponentPropService.queryMasterModel(queryRssComponentPropDao, id));
    }

    @BusinessLog(name = "RssComponentProp", value = "update RssComponentProp")
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 RssComponentProp", response = RssComponentProp.class)
    public Tip updateRssComponentProp(@PathVariable Long id, @RequestBody RssComponentProp entity) {
        entity.setId(id);
        return SuccessTip.create(rssComponentPropService.updateMaster(entity));
    }

    @BusinessLog(name = "RssComponentProp", value = "delete RssComponentProp")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 RssComponentProp")
    public Tip deleteRssComponentProp(@PathVariable Long id) {
        return SuccessTip.create(rssComponentPropService.deleteMaster(id));
    }

}
