package com.jfeat.am.module.advertisement.api.pub;

import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdLibraryDao;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
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
@RequestMapping("/api/pub/cms")
@Api("AD-轮播图")
public class PubAppidAdEndpoint {

    @Resource
    private AdService adService;
    @Resource
    QueryAdLibraryDao queryAdLibraryDao;

    @GetMapping("/ad/group/{group}/{appid}")
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
    @GetMapping("/ad/records/{group}/{appid}")
    public Tip Ad(@PathVariable String group,
                 @RequestParam(value = "enabled", required = false) Integer enabled,@PathVariable String appid) {
        return SuccessTip.create(queryAdLibraryDao.getAdRecordsByGroup(group,appid, enabled));
    }

}
