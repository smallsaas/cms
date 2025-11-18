package com.jfeat.am.module.advertisement.api.pub;

import com.jfeat.am.module.advertisement.services.service.AdService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
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

    // @Resource
    // QueryAdDao queryAdDao;

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

    @GetMapping("/group/{group}")
    @ApiOperation("根据分组标识获取轮播图")
    public Tip getAdByGroupId(@PathVariable String group) {
        return SuccessTip.create(adService.getAdRecordsByGroup(group));
    }

    // @ApiOperation("按组获取轮播图 group=1 首页轮播图")
    // @GetMapping("/records/{group}")
    // public Tip Ad(@PathVariable String group,
    //               @RequestParam(value = "enabled", required = false) Integer enabled) {
    //     return SuccessTip.create(queryAdLibraryDao.getAdRecordsByGroup(group,null, enabled));
    // }


    // @GetMapping("/group/{group}")
    // @ApiOperation("按组获取轮播图 [带组信息]")
    // public Tip getAdGroup(@PathVariable String group) {
    //     AdGroupedModel model = adService.getAdRecordsByGroup(group);
    //     if(model != null && model.getAds() != null) {
    //         List<Ad> temp = model.getAds().stream().sorted((t1, t2) -> t1.getType().compareTo(t2.getType())).collect(Collectors.toList());
    //         model.setAds(temp);
    //     }
    //     return SuccessTip.create(model);
    // }



    /**
     * 属前置管理，暂不开放
     * 
     * */

    // @GetMapping("/adList/{appid}")
    // @ApiOperation("广告列表带社区")
    // public Tip queryAdLibraryiesByappidAndOrgId(Page<AdRecord> page,
    //                                             @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
    //                                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    //                                             @RequestParam(name = "search", required = false) String search,
    //                                             @RequestParam(name = "enabled", required = false) Integer enabled,
    //                                             @RequestParam(name = "groupId", required = false) Long groupId,
    //                                             @RequestParam(name = "orderBy", required = false) String orderBy,
    //                                             @RequestParam(name = "sort", required = false) String sort,
    //                                             @RequestParam(name = "identifier", required = false) String identifier,
    //                                             @PathVariable("appid") String appid) {
    //     Long userId = JWTKit.getUserId();
    //     if (userId==null){
    //         throw new BusinessException(BusinessCode.NoPermission,"没有登录");
    //     }
        
    //     Long currentOrgId = null;

    //     UserAccount userAccount = userAccountMapper.selectById(userId);
    //     if (userAccount==null){
    //         throw new BusinessException(BusinessCode.UserNotExisted,"用户不存在");
    //     }
    //     if (userAccount.getCurrentOrgId()!=null){
    //         currentOrgId = userAccount.getCurrentOrgId();
    //     }else if (userAccount.getOrgId()!=null){
    //         currentOrgId = userAccount.getOrgId();
    //     }else {
    //         throw new BusinessException(BusinessCode.CodeBase,"没有找到该社区信息");
    //     }

    //     if (orderBy != null && orderBy.length() > 0) {
    //         if (sort != null && sort.length() > 0) {
    //             String pattern = "(ASC|DESC|asc|desc)";
    //             if (!sort.matches(pattern)) {
    //                 throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
    //             }
    //         } else {
    //             sort = "ASC";
    //         }
    //         orderBy = "`" + orderBy + "`" + " " + sort;
    //     }


    //     page.setCurrent(pageNum);
    //     page.setSize(pageSize);


    //     AdRecord record = new AdRecord();
    //     record.setGroupId(groupId);
    //     record.setName(search);
    //     record.setEnabled(enabled);
    //     record.setIdentifier(identifier);
    //     record.setOrgId(currentOrgId);

    //     page.setRecords(queryAdDao.findAdPageByAppid(page,record,orderBy,search,appid));

    //     return SuccessTip.create(page);
    // }



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
