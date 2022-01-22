package com.jfeat.am.module.termconfig.api;

import com.jfeat.am.module.termconfig.services.crud.service.TermConfigService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @since 2017-12-09
 */
@Api("cms-系统规则配置")
@RestController
@RequestMapping("/api/u/cms/term/config")
public class UserEndTermConfigEndpoint {
    @Resource
    TermConfigService termConfigService;

    @ApiOperation(value = "通过规则类型，获取具体规则配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "规则类型/名称", required = true, dataType = "String", paramType = "query")})
    @GetMapping
    public Tip getUserEndTermConfigByType(@RequestParam(name = "type", required = true) String type) {
        return SuccessTip.create(termConfigService.getTermConfigByType(type));
    }
}
