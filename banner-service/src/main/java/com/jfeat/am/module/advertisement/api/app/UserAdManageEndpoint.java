package com.jfeat.am.module.advertisement.api.app;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdLibraryDao;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdGroupMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import com.jfeat.am.module.advertisement.services.service.AdGroupService;
import com.jfeat.am.module.advertisement.services.service.AdService;
import com.jfeat.am.module.advertisement.services.service.TenantUtilsService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/u/cms/manage")
@Api("轮播图管理-Banner")
public class UserAdManageEndpoint {

    @Resource
    private AdService adService;
    @Resource
    QueryAdLibraryDao queryAdLibraryDao;
    @Resource
    QueryAdDao queryAdDao;

    @Resource
    AdGroupMapper adGroupMapper;

    @Resource
    TenantUtilsService tenantUtilsService;

    @Resource
    private AdGroupService adGroupService;

/*    @Resource
    Authentication authentication;*/

//    @GetMapping("/ad/groups")
//    @ApiOperation("获取广告组列表")
//    public Tip listAdGroups(Page<AdGroup> page,
//                            @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
//                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
//                            @RequestParam(name = "search", required = false) String search) {
//
//
//        Long userId = JWTKit.getUserId();
//        if (userId==null){
//            throw new BusinessException(BusinessCode.NoPermission,"没有登录");
//        }
//        page.setCurrent(pageNum);
//        page.setSize(pageSize);
//        Long orgId = tenantUtilsService.getCurrentOrgId(userId);
////        大匠
//        List<AdGroup> house = adGroupService.getCurrentAdGroup(orgId,"1");
//
////        小匠
//        QueryWrapper<AdGroup> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq(AdGroup.APPID,"2");
//        List<AdGroup> rentAdGroups = adGroupMapper.selectList(queryWrapper);
//
//        house.addAll(rentAdGroups);
//
//        page.setRecords(house);
//
//        return SuccessTip.create(page);
//    }
//
//    @PostMapping("/ad/groups")
//    @ApiOperation("添加轮播图分类")
//    public Tip createAdGroup(@RequestBody AdGroup entity) {
//         /*
//        验证用户是否是运营身份
//         */
//        if (JWTKit.getUserId() == null) {
//            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
//        }
//
//      /*  if (!authentication.verifyOperation(JWTKit.getUserId())) {
//            throw new BusinessException(BusinessCode.NoPermission, "该用户没有权限");
//        }*/
//        return SuccessTip.create(adGroupService.createMaster(entity));
//    }

    @DeleteMapping("/ad/{id}")
    @ApiOperation("删除轮播图")
    public Tip deleteAd(@PathVariable Long id) {
        return SuccessTip.create(adService.deleteMaster(id));
    }

    @ApiOperation("按组获取广告组")
    @GetMapping("/ad/allGroup")
    public Tip getAdsFromGroup(
            @RequestParam(name = "search", required = false) String search,
            @RequestParam(name = "groupId", required = true) Integer groupId) {
         /*
        验证用户是否是运营身份
         */
        if (JWTKit.getUserId() == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }

      /*  if (!authentication.verifyOperation(JWTKit.getUserId())) {
            throw new BusinessException(BusinessCode.NoPermission, "该用户没有权限");
        }
*/
        if(search!=null && search.trim().length()>0){
            return SuccessTip.create(queryAdDao.selectList(new QueryWrapper<Ad>().eq("group_id",groupId)
                    .like("name",search)
                    .or()
                    .like("type",search)
                    .eq("group_id",groupId)
            ));
        }else  return SuccessTip.create(queryAdDao.selectList(new QueryWrapper<Ad>().eq("group_id",groupId)));

    }



    @PostMapping("/ad/{groupId}")
    @ApiOperation("根据GroupId 添加轮播图")
    public Tip createAd(@PathVariable Long groupId, @RequestBody AdRecord entity) {
         /*
        验证用户是否是运营身份
         */
        if (JWTKit.getUserId() == null) {
            throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
        }

       /* if (!authentication.verifyOperation(JWTKit.getUserId())) {
            throw new BusinessException(BusinessCode.NoPermission, "该用户没有权限");
        }*/
        entity.setEnabled(1);
        entity.setGroupId(groupId);
     /*   //处理图片
        if(entity.getImages()!=null&&entity.getImages().size()>0){
            entity.setImage(entity.getImages().get(0).getUrl());
        }*/

        return SuccessTip.create(adService.createMaster(entity));
    }
}
