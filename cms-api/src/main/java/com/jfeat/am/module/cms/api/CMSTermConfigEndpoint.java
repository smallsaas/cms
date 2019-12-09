package com.jfeat.am.module.cms.api;

import com.jfeat.am.module.termconfig.services.crud.service.TermConfigService;
import com.jfeat.am.module.termconfig.services.persistence.model.TermConfig;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2017-12-09
 */
@Api("cms-系统规则配置")
@RestController
@RequestMapping("/api/cms/term/config")
public class CMSTermConfigEndpoint  {

    @Resource
    TermConfigService termConfigService;

    @ApiOperation(value = "通过规则类型，获取具体规则配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "规则类型/名称", required = true, dataType = "String", paramType = "query")})
    @GetMapping
    public Tip getTermConfigByType(@RequestParam(name = "type", required = true) String type) {
        return SuccessTip.create(termConfigService.getTermConfigByType(type));
    }

    @ApiOperation(value = "创建规则配置", response = TermConfig.class)
    @PostMapping
    public Tip createTermConfig(@RequestBody TermConfig entity) {
        Date now = new Date();
        entity.setCreatedTime(now);
        entity.setPreviousModifiedTime(now);
        entity.setLastModifiedTime(now);
        return SuccessTip.create(termConfigService.createMaster(entity));
    }

    @ApiOperation("获取规则配置详情")
    @GetMapping("/{id}")
    public Tip getTermConfig(@PathVariable Long id) {
        return SuccessTip.create(termConfigService.retrieveMaster(id));
    }

    @ApiOperation(value = "更新规则配置", response = TermConfig.class)
    @PutMapping("/{id}")
    public Tip updateTermConfig(@PathVariable Long id, @RequestBody TermConfig entity) {
        TermConfig termConfig = termConfigService.retrieveMaster(id);
        entity.setId(id);
        entity.setPreviousModifiedTime(termConfig.getLastModifiedTime());
        entity.setLastModifiedTime(new Date());
        return SuccessTip.create(termConfigService.updateMaster(entity));
    }

    @ApiOperation("删除规则配置")
    @DeleteMapping("/{id}")
    public Tip deleteTermConfig(@PathVariable Long id) {
        return SuccessTip.create(termConfigService.deleteMaster(id));
    }


  /*  @ApiOperation(value = "获取全部规则配置")
    @GetMapping
    public Tip getTermConfigByType() {
        return SuccessTip.create(termConfigService.getTermConfig());
    }*/


}
