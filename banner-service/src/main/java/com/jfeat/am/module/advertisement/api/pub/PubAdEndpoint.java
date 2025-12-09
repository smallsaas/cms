package com.jfeat.am.module.advertisement.api.pub;

import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.service.AdService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author admin
 * @since 2017-09-20
 */
@RestController
@RequestMapping("/api/pub/cms/ad")
@Api("轮播图-Banner")
public class PubAdEndpoint {

    @Resource
    AdService adService;

    // @Resource
    // AdGroupMapper adGroupMapper;

    // @Resource
    // AdGroupService adGroupService;

     @Resource
     QueryAdDao queryAdDao;

    // @Resource
    // QueryAdLibraryDao queryAdLibraryDao;


    // @PostMapping("/{groupId}")
    // @ApiOperation("根据分组添加轮播图")
    // public Tip createAd(@PathVariable Long groupId, @RequestBody AdRecord entity) {
    //     entity.setEnabled(1);
    //     entity.setGroupId(groupId);

    //     /*   
    //     //处理图片
    //     if(entity.getImages()!=null&&entity.getImages().size()>0){
    //         entity.setImage(entity.getImages().get(0).getUrl());
    //     }*/

    //     return SuccessTip.create(adService.createMaster(entity));
    // }

    // @PostMapping("/id/{identifier}")
    // @ApiOperation("根据identifier添加轮播图")
    // public Tip createAdByIdentifier(@PathVariable String identifier, @RequestBody Ad entity) {
    //     entity.setEnabled(1);
    //     AdGroup adGroup = new AdGroup();
    //     adGroup.setIdentifier(identifier);
    //     AdGroup query = adGroupMapper.selectOne(new LambdaQueryWrapper<>(adGroup));
    //     entity.setGroupId(query == null ? null : query.getId());
    //     return SuccessTip.create(adService.createMaster(entity));
    // }

    @GetMapping("/group/{group_name}")
    @ApiOperation("根据分组标识获取轮播图")
    public Tip getAdByGroupId(@PathVariable String group_name) {
        return SuccessTip.create(adService.getAdRecordsByGroup(group_name));
    }

    // @ApiOperation("按组获取轮播图分组")
    // @GetMapping("/allGroup")
    // public Tip getAdsFromGroup(
    //         @RequestParam(name = "search", required = false) String search,
    //         @RequestParam(name = "groupId", required = true) Integer groupId) {
    //     /*
    //     验证用户是否是运营身份
    //      */
    //     if (JWTKit.getUserId() == null) {
    //         throw new BusinessException(BusinessCode.NoPermission, "用户未登录");
    //     }

    //     /*  if (!authentication.verifyOperation(JWTKit.getUserId())) {
    //         throw new BusinessException(BusinessCode.NoPermission, "该用户没有权限");
    //     }
    //     */
    //     if(search!=null && search.trim().length()>0){
    //         return SuccessTip.create(queryAdDao.selectList(new QueryWrapper<Ad>().eq("group_id",groupId)
    //                 .like("name",search)
    //                 .or()
    //                 .like("type",search)
    //                 .eq("group_id",groupId)
    //         ));
    //     }else  return SuccessTip.create(queryAdDao.selectList(new QueryWrapper<Ad>().eq("group_id",groupId)));
    // }

}
