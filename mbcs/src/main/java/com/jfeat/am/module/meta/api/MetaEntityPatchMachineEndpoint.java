package com.jfeat.am.module.meta.api;


import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.meta.services.domain.service.MetaEntityPatchMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEntityPatchMachine;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2019-01-07
 */
@RestController

@Api("MetaEntityPatchMachine")
@RequestMapping("/api/meta/patch")
public class MetaEntityPatchMachineEndpoint {


    @Resource
    MetaEntityPatchMachineService metaEntityPatchMachineService;

    //@BusinessLog(name = "MetaEntityPatchMachine", value = "get MetaEntityPatchMachine")
    @ApiOperation(value = "查看meta entity patch配置")
    @GetMapping("/config/records/{entity}")

    public Tip getMetaEntityPatchMachines(@PathVariable(name = "entity") String entity,
                                          @RequestParam(name = "entityTableName", required = false) String entityTableName,
                                          @RequestParam(name = "entityFieldName", required = false) String entityFieldName,
                                          @RequestParam(name = "entityFieldType", required = false) String entityFieldType) {

        MetaEntityPatchMachine queryEntity = new MetaEntityPatchMachine();
        queryEntity.setEntity(entity);
        queryEntity.setEntityTableName(entityTableName);
        queryEntity.setEntityFieldName(entityFieldName);
        queryEntity.setEntityFieldType(entityFieldType);
        return SuccessTip.create(metaEntityPatchMachineService.findMetaEntityPatchMachines(queryEntity));
    }

    //@BusinessLog(name = "MetaEntityPatchMachine", value = "create MetaEntityPatchMachine")
    @PostMapping("/config/records")
    @ApiOperation(value = "添加meta entity patch配置")
    public Tip createMetaEntityPatchMachine(@RequestBody MetaEntityPatchMachine entity) {
        return SuccessTip.create(metaEntityPatchMachineService.createMeta(entity));
    }

    //@BusinessLog(name = "MetaEntityPatchMachine", value = "update entity")
    @PostMapping("/entity/{entity}/entities/{id}")
    @ApiOperation(value = "更新实体")
    public Tip updateEntity(@PathVariable(name = "id") Long id,
                            @PathVariable(name = "entity") String entity,
                            @RequestBody Map<String, String> params) {
        return SuccessTip.create(metaEntityPatchMachineService.updateEntity(entity, id, params));
    }

    //@BusinessLog(name = "MetaEntityPatchMachine", value = "bulk update entity")
    @PostMapping("/entity/{entity}/entities/bulk")
    @ApiOperation(value = "批量更新实体")
    public Tip bulkUpdateEntity(@PathVariable(name = "entity") String entity,
                                @RequestBody List<Map<String, String>> params) {
        return SuccessTip.create(metaEntityPatchMachineService.bulkUpdateEntity(entity, params));
    }

    //@BusinessLog(name = "MetaEntityPatchMachine", value = "bulk delete entity")
    @PostMapping("/entity/{entity}/entities/bulk/delete")
    @ApiOperation(value = "批量删除实体")
    public Tip bulkDeleteEntity(@PathVariable(name = "entity") String entity,
                                @RequestBody Ids ids) {
        if (null == ids || CollectionUtils.isEmpty(ids.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "ids[]不能为空");
        }
        return SuccessTip.create(metaEntityPatchMachineService.bulkDeleteEntity(entity, ids.getIds()));
    }

    //@BusinessLog(name = "排序", value = "列表记录上移内容")
    @PostMapping("/entity/{entity}/action/moveup/row/{id}/row/{nextId}")
    @ApiOperation(value = "上移实体排序")
    public Tip moveUpEntity(@PathVariable(name = "entity") String entity,
                            @PathVariable(name = "id") Long id,
                            @PathVariable(name = "nextId") Long nextId) {
        return SuccessTip.create(metaEntityPatchMachineService.moveUpEntity(entity, id, nextId));
    }

    //@BusinessLog(name = "排序", value = "列表记录下移内容")
    @PostMapping("/entity/{entity}/action/movedown/row/{id}/row/{nextId}")
    @ApiOperation(value = "下移实体排序")
    public Tip moveDownEntity(@PathVariable(name = "entity") String entity,
                            @PathVariable(name = "id") Long id,
                            @PathVariable(name = "nextId") Long nextId) {
        return SuccessTip.create(metaEntityPatchMachineService.moveDownEntity(entity, id, nextId));
    }

    //@BusinessLog(name = "MetaEntityPatchMachine", value = "逻辑删除实体")
    @PostMapping("/entity/{entity}/entities/{id}/action/logicDelete")
    @ApiOperation(value = "逻辑删除实体")
    public Tip logicDeleteEntity(@PathVariable(name = "id") Long id,
                            @PathVariable(name = "entity") String entity) {
        return SuccessTip.create(metaEntityPatchMachineService.handleLogicDelete(entity, id, false));
    }

    //@BusinessLog(name = "MetaEntityPatchMachine", value = "恢复逻辑删除的实体")
    @PostMapping("/entity/{entity}/entities/{id}/action/logicDelete/recovery")
    @ApiOperation(value = "恢复逻辑删除的实体")
    public Tip recoveryLogicDeleteEntity(@PathVariable(name = "id") Long id,
                                 @PathVariable(name = "entity") String entity) {
        return SuccessTip.create(metaEntityPatchMachineService.handleLogicDelete(entity, id, true));
    }

    //@BusinessLog(name = "MetaEntityPatchMachine", value = "批量逻辑删除的实体")
    @PostMapping("/entity/{entity}/entities/action/bulk/logicDelete")
    @ApiOperation(value = "批量逻辑删除的实体")
    public Tip bulkLogicDeleteEntity(@PathVariable(name = "entity") String entity,
                                             @RequestBody Ids ids) {
        if (null == ids|| CollectionUtils.isEmpty(ids.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "ids[]不能为空");
        }
        return SuccessTip.create(
                metaEntityPatchMachineService.handleBulkLogicDelete(entity, ids.getIds(), false));
    }

    //@BusinessLog(name = "MetaEntityPatchMachine", value = "批量恢复逻辑删除的实体")
    @PostMapping("/entity/{entity}/entities/action/bulk/logicDelete/recovery")
    @ApiOperation(value = "批量恢复逻辑删除的实体")
    public Tip bulkRecoveryLogicDeleteEntity(@PathVariable(name = "entity") String entity,
                                     @RequestBody Ids ids) {
        if (null == ids|| CollectionUtils.isEmpty(ids.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "ids[]不能为空");
        }
        return SuccessTip.create(
                metaEntityPatchMachineService.handleBulkLogicDelete(entity, ids.getIds(), true));
    }
}
