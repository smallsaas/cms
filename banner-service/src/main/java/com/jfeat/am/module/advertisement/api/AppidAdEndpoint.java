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
@RequestMapping("/api/adm/cms")
@Api("AD-轮播图")
public class AppidAdEndpoint {

    @Resource
    private AdService adService;
    @Resource
    private AdGroupMapper adGroupMapper;

    @PostMapping("/ad/id/{identifier}/{appid}")
    @ApiOperation("根据identifier分类添加轮播图")
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
