package com.jfeat.am.module.advertisement.api.app;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdLibraryDao;
import com.jfeat.am.module.advertisement.services.domain.model.AdImage;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdLibraryRecord;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroupedModel;
import com.jfeat.am.module.advertisement.services.service.AdGroupService;
import com.jfeat.am.module.advertisement.services.service.AdService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cms/ad/groups")
@Api("轮播图前置管理-Banner")
public class UserAdGroupEndpoint {

    @Resource
    AdGroupService adGroupService;


    @Resource
    QueryAdDao queryAdDao;

    @Resource
    QueryAdLibraryDao queryAdLibraryDao;


    @PostMapping
    @ApiOperation("添加轮播图分类")
    public Tip createAdGroup(@RequestBody AdGroup entity) {
        /*
        验证用户是否是运营身份
        */
        if (JWTKit.getUserId() == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }

        /*  if (!authentication.verifyOperation(JWTKit.getUserId())) {
            throw new BusinessException(BusinessCode.NoPermission, "该用户没有权限");
        }*/
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

    // @GetMapping("/all")
    // @ApiOperation("获取广告组列表")
    // public Tip allListAdGroups(Page<AdGroup> page,
    //                         @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
    //                         @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    //                         @RequestParam(name = "search", required = false) String search) {
    //     page.setCurrent(pageNum);
    //     page.setSize(pageSize);
    //     page.setRecords(adGroupService.getAllAdGroup(search));

    //     return SuccessTip.create(page);
    // }

   @GetMapping
   @ApiOperation("获取广告组分页列表")
   public Tip listAdGroups(Page<AdGroup> page,
                           @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(name = "search", required = false) String search) {
       Long userId = JWTKit.getUserId();
       if (userId==null){
           throw new BusinessException(BusinessCode.NoPermission,"没有登录");
       }
       page.setCurrent(pageNum);
       page.setSize(pageSize);
       Long orgId = tenantUtilsService.getCurrentOrgId(userId);
//        大匠
       List<AdGroup> house = adGroupService.getCurrentAdGroup(orgId,"1");

//        小匠
       QueryWrapper<AdGroup> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq(AdGroup.APPID,"2");
       List<AdGroup> rentAdGroups = adGroupMapper.selectList(queryWrapper);

       house.addAll(rentAdGroups);

       page.setRecords(house);

       return SuccessTip.create(page);
   }

}
