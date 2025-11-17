package com.jfeat.am.module.advertisement.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
@RequestMapping("/api/adm/cms/ad/groups")
@Api("轮播图-分组管理")
public class AdGroupEndpoint {

    @Resource
    private AdGroupService adGroupService;
    

    @PostMapping
    @ApiOperation("添加轮播图分组")
    public Tip createAdGroup(@RequestBody AdGroup entity) {
        return SuccessTip.create(adGroupService.createMaster(entity));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取轮播图分类详情")
    public Tip getAdGroups(@PathVariable Long id) {
        return SuccessTip.create(adGroupService.retrieveMaster(id));
    }

    @PutMapping("/{id}")
    @ApiOperation("更新轮播图分组")
    public Tip updateAdGroup(@PathVariable Long id, @RequestBody AdGroup entity) {
        entity.setId(id);
        return SuccessTip.create(adGroupService.updateMaster(entity));
    }

    @ApiOperation("删除轮播图分组")
    @DeleteMapping("/{id}")
    public Tip deleteAdGroup(@PathVariable Long id) {
        return SuccessTip.create(adGroupService.deleteMaster(id));
    }


    @GetMapping
    @ApiOperation("获取轮播图组列表")
    public Tip listAdGroups(Page<AdGroup> page,
                            @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "search", required = false) String search) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(adGroupService.getAllAdGroup(search));

        return SuccessTip.create(page);
    }


    // @GetMapping("/data")
    // @ApiOperation("获取轮播图所有分类数据")
    // public Tip getAllGroupData() {
    //     return SuccessTip.create(adGroupService.getAllGroupData());
    // }

}
