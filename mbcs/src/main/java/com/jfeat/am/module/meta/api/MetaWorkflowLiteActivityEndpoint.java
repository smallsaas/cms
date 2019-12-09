package com.jfeat.am.module.meta.api;


import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.meta.services.domain.dao.QueryMetaWorkflowLiteActivityDao;
import com.jfeat.am.module.meta.services.domain.service.MetaWorkflowLiteActivityService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * <p>
 * 工作流记录表 api
 * </p>
 *
 * @author Code Generator
 * @since 2019-01-29
 */
@RestController

@Api("MetaWorkflowLiteActivity")
@RequestMapping("/api/meta/workflow")
public class MetaWorkflowLiteActivityEndpoint {


    @Resource
    MetaWorkflowLiteActivityService metaWorkflowLiteActivityService;

    @Resource
    QueryMetaWorkflowLiteActivityDao queryMetaWorkflowLiteActivityDao;

    @BusinessLog(name = "MetaWorkflowLiteActivity", value = "获取工作流")
    @GetMapping("/entity/{entity}/entities/{entityId}")
    @ApiOperation("获取工作流")
    public Tip getMetaWorkflowLiteActivityByEntity(@PathVariable(name = "entity") String entity,
                                                         @PathVariable(name = "entityId") Long entityId) {
        return SuccessTip.create(metaWorkflowLiteActivityService.getListByEntity(entity, entityId));
    }

    @BusinessLog(name = "MetaWorkflowLiteActivity", value = "delete MetaWorkflowLiteActivity")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 MetaWorkflowLiteActivity")
    public Tip deleteMetaWorkflowLiteActivity(@PathVariable Long id) {
        return SuccessTip.create(metaWorkflowLiteActivityService.deleteMaster(id));
    }
}
