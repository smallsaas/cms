package com.jfeat.am.module.advertisement.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import com.jfeat.am.module.advertisement.services.service.AdGroupService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  运维API, 不提供配置
 * </p>
 *
 * @author admin
 * @since 2017-09-20
 */
@RestController
@RequestMapping("/api/cms")
@Api("AD-轮播图")
public class AdGroupEndpoint {

    @Resource
    private AdGroupService adGroupService;

    @PostMapping("/adm/ad/groups")
    @ApiOperation("添加轮播图分类")
    public Tip createAdGroup(@RequestBody AdGroup entity) {
        return SuccessTip.create(adGroupService.createMaster(entity));
    }

    @PutMapping("/adm/ad/groups/{id}")
    @ApiOperation("更新轮播图分类")
    public Tip updateAdGroup(@PathVariable Long id, @RequestBody AdGroup entity) {
        entity.setId(id);
        return SuccessTip.create(adGroupService.updateMaster(entity));
    }

    @ApiOperation("删除轮播图分类")
    @DeleteMapping("/adm/ad/groups/{id}")
    public Tip deleteAdGroup(@PathVariable Long id) {
        return SuccessTip.create(adGroupService.deleteMaster(id));
    }
}
