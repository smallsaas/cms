package com.jfeat.am.module.meta.api;


import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.am.module.meta.services.domain.model.BulkMetaEnable;
import com.jfeat.am.module.meta.services.domain.model.ValueModel;
import com.jfeat.am.module.meta.services.domain.service.MetaEnableMachineService;
import com.jfeat.am.module.meta.services.gen.persistence.model.MetaEnableMachine;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
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


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2019-01-05
 */
@RestController

@Api("MetaEnableMachine")
@RequestMapping("/api/meta")
public class MetaEnableMachineEndpoint {


    @Resource
    MetaEnableMachineService metaEnableMachineService;

    @BusinessLog(name = "MetaEnableMachine", value = "查看 有效机")
    @GetMapping("/validation/config/records/{entity}")
    @ApiOperation(value = "查看有效机")
    public Tip getMetaEnableMachine(@PathVariable(name = "entity") String entity,
                                    @RequestParam(name = "entityTableName", required = false) String entityTableName,
                                    @RequestParam(name = "entityFieldName", required = false) String entityFieldName) {
        MetaEnableMachine queryEntity = new MetaEnableMachine();
        queryEntity.setEntity(entity);
        queryEntity.setEntityTableName(entityTableName);
        queryEntity.setEntityFieldName(entityFieldName);
        return SuccessTip.create(metaEnableMachineService.findMetaEnableMachine(queryEntity));
    }

    @BusinessLog(name = "MetaEnableMachine", value = "add 有效机")
    @PostMapping("/validation/config/records")
    @ApiOperation(value = "增加有效机配置")
    public Tip createMetaEnableMachine(@RequestBody MetaEnableMachine metaEnableMachine) {
        return SuccessTip.create(metaEnableMachineService.createMaster(metaEnableMachine));
    }

    @BusinessLog(name = "MetaEnableMachine", value = "更新为有效状态")
    @PostMapping("/entity/{entity}/entities/{id}/action/enable")
    @ApiOperation(value = "更新为有效")
    public Tip enableEntity(@PathVariable(name = "entity") String entity,
                            @PathVariable(name = "id") Long id) {
        return SuccessTip.create(metaEnableMachineService.enableEntity(id, entity));
    }

    @BusinessLog(name = "MetaEnableMachine", value = "更新为无效状态")
    @PostMapping("/entity/{entity}/entities/{id}/action/disable")
    @ApiOperation(value = "更新为无效")
    public Tip disableEntity(@PathVariable(name = "entity") String entity,
                            @PathVariable(name = "id") Long id) {
        return SuccessTip.create(metaEnableMachineService.disableEntity(id, entity));
    }

    @BusinessLog(name = "MetaEnableMachine", value = "更新为其他状态")
    @PostMapping("/entity/{entity}/entities/{id}/action/validate")
    @ApiOperation(value = "更新为其他状态")
    public Tip validateEntity(@PathVariable(name = "entity") String entity,
                              @PathVariable(name = "id") Long id,
                              @RequestBody ValueModel valueModel) {
        if (null == valueModel || null == valueModel.getValue()) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "缺失value参数");
        }
        return SuccessTip.create(metaEnableMachineService.validateEntity(id, entity, valueModel.getValue()));
    }

    @BusinessLog(name = "MetaEnableMachine", value = "批量更新为有效状态")
    @PostMapping("/entity/{entity}/entities/action/bulk/enable")
    @ApiOperation(value = "批量更新为有效")
    public Tip bulkEnableEntity(@PathVariable(name = "entity") String entity,
                                @RequestBody Ids ids) {
        if (null == ids || CollectionUtils.isEmpty(ids.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "缺失ids[]参数");
        }
        return SuccessTip.create(metaEnableMachineService.bulkEnableEntity(ids.getIds(), entity));
    }

    @BusinessLog(name = "MetaEnableMachine", value = "批量更新为无效状态")
    @PostMapping("/entity/{entity}/entities/action/bulk/disable")
    @ApiOperation(value = "批量更新为无效")
    public Tip bulkDisableEntity(@PathVariable(name = "entity") String entity,
                                 @RequestBody Ids ids) {
        if (null == ids || CollectionUtils.isEmpty(ids.getIds())) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "缺失ids[]参数");
        }
        return SuccessTip.create(metaEnableMachineService.bulkDisableEntity(ids.getIds(), entity));
    }

    @BusinessLog(name = "MetaEnableMachine", value = "批量更新为其他状态")
    @PostMapping("/entity/{entity}/entities/action/bulk/validate")
    @ApiOperation(value = "批量更新为其他状态")
    public Tip bulkValidateEntity(@PathVariable(name = "entity") String entity,
                                  @RequestBody BulkMetaEnable bulkMetaEnable) {
        if (null == bulkMetaEnable || null == bulkMetaEnable.getIds() || null == bulkMetaEnable.getValue()) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "缺失ids[]或value");
        }
        return SuccessTip.create(
                metaEnableMachineService.bulkValidateEntity(bulkMetaEnable.getIds(), entity, bulkMetaEnable.getValue()));
    }
}
