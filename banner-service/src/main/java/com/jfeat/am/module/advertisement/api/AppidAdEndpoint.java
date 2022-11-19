package com.jfeat.am.module.advertisement.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdLibraryDao;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdGroupMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroupedModel;
import com.jfeat.am.module.advertisement.services.service.AdService;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-09-20
 */
@RestController
@RequestMapping("/api/cms")
@Api("AD-轮播图")
public class AppidAdEndpoint {

    @Resource
    private AdService adService;
    @Resource
    private AdGroupMapper adGroupMapper;
    @Resource
    QueryAdLibraryDao queryAdLibraryDao;
    @Resource
    QueryAdDao queryAdDao;

    @GetMapping("/pub/ad/group/{group}/{appid}")
    @ApiOperation("按组获取轮播图 [带组信息]")
    public Tip getAdGroup(@PathVariable String group,@PathVariable String appid) {
        AdGroupedModel model = adService.getAdRecordsByGroup(group,appid);
        if(model != null && model.getAds() != null) {
            List<Ad> temp = model.getAds().stream().sorted((t1, t2) -> t1.getType().compareTo(t2.getType())).collect(Collectors.toList());
            model.setAds(temp);
        }
        return SuccessTip.create(model);
    }

    @ApiOperation("按组获取轮播图 group=1 首页轮播图")
    @GetMapping("/pub/ad/records/{group}/{appid}")
    public Tip Ad(@PathVariable String group,
                 @RequestParam(value = "enabled", required = false) Integer enabled,@PathVariable String appid) {
        return SuccessTip.create(queryAdLibraryDao.getAdRecordsByGroup(group,appid, enabled));
    }



    @PostMapping("/ad/id/{identifier}/{appid}")
    @ApiOperation("根据identifier添加轮播图")
    public Tip createAdByIdentifier(@PathVariable String identifier, @RequestBody Ad entity,@PathVariable("appid")String appid) {
        entity.setEnabled(1);
        AdGroup adGroup = new AdGroup();
        adGroup.setIdentifier(identifier);
        adGroup.setAppid(appid);
        AdGroup query = adGroupMapper.selectOne(new LambdaQueryWrapper<>(adGroup));
        entity.setGroupId(query == null ? null : query.getId());
        return SuccessTip.create(adService.createMaster(entity));
    }



}
