package com.jfeat.am.module.advertisement.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdLibraryDao;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdGroupMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import com.jfeat.am.module.advertisement.services.service.AdGroupService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
@Api("后台轮播图管理-分组管理")
public class AdGroupEndpoint {

    @Resource
    private AdGroupService adGroupService;

    @Resource
    private AdGroupMapper adGroupMapper;

    @Resource
    private QueryAdDao queryAdDao;

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


    // @GetMapping
    // @ApiOperation("获取轮播图分组列表")
    // public Tip listAdGroups(Page<AdGroup> page,
    //                         @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
    //                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    //                         @RequestParam(name = "search", required = false) String search) {
    //     page.setCurrent(pageNum);
    //     page.setSize(pageSize);
    //     page.setRecords(adGroupService.getAllAdGroup(search));

    //     return SuccessTip.create(page);
    // }

    @GetMapping("/all")
    @ApiOperation("获取轮播图分组列表")
    public Tip allListAdGroups(Page<AdGroup> page,
                            @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "search", required = false) String search) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(adGroupService.getAllAdGroup(search));
 
        return SuccessTip.create(page);
    }


    @GetMapping
    @ApiOperation("轮播图分组列表")
    public Tip queryAdGroups(Page<AdGroup> page,
                                 @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "search", required = false) String search,
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

        AdGroup record = new AdGroup();

        page.setRecords(queryAdDao.findAdGroupPage(page,record,orderBy,search));

        return SuccessTip.create(page);
    }

    
//    @GetMapping
//    @ApiOperation("获取轮播图分组分页列表")
//    public Tip listAdGroups(Page<AdGroup> page,
//                            @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
//                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
//                            @RequestParam(name = "search", required = false) String search) {
//        Long userId = JWTKit.getUserId();
//        if (userId==null){
//            throw new BusinessException(BusinessCode.NoPermission,"没有登录");
//        }
//        page.setCurrent(pageNum);
//        page.setSize(pageSize);
//        Long orgId = tenantUtilsService.getCurrentOrgId(userId);

// //        大匠
//        List<AdGroup> house = adGroupService.getCurrentAdGroup(orgId, "1");

// //        小匠
//        QueryWrapper<AdGroup> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(AdGroup.APPID,"2");
//        List<AdGroup> rentAdGroups = adGroupMapper.selectList(queryWrapper);

//        house.addAll(rentAdGroups);

//        page.setRecords(house);

//        return SuccessTip.create(page);
//    }


    @GetMapping("/data")
    @ApiOperation("获取轮播图所有分类数据")
    public Tip getAllGroupData() {
        return SuccessTip.create(adGroupService.getAllGroupData());
    }

}
