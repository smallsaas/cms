package com.jfeat.am.module.advertisement.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdLibraryDao;
import com.jfeat.am.module.advertisement.services.domain.model.AdImage;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdLibraryRecord;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdGroupMapper;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroup;
import com.jfeat.am.module.advertisement.services.persistence.model.AdGroupedModel;
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
@Api("轮播图-Banner")
public class AdEndpoint  {

    @Resource
    private AdService adService;
    @Resource
    private AdGroupMapper adGroupMapper;

    @Resource
    QueryAdLibraryDao queryAdLibraryDao;
    @Resource
    QueryAdDao queryAdDao;

    @PostMapping("/ad")
    @ApiOperation("添加轮播图")
    public Tip createAd(@RequestBody AdRecord entity) {
        entity.setEnabled(1);
        //处理图片
       /* if(entity.getImages()!=null&&entity.getImages().size()>0){
            entity.setImage(entity.getImages().get(0).getUrl());
        }*/

        return SuccessTip.create(adService.createMaster(entity));
    }

    @PostMapping("/ad/{groupId}")
    @ApiOperation("根据GroupId 添加轮播图")
    public Tip createAd(@PathVariable Long groupId, @RequestBody AdRecord entity) {
        entity.setEnabled(1);
        entity.setGroupId(groupId);
     /*   //处理图片
        if(entity.getImages()!=null&&entity.getImages().size()>0){
            entity.setImage(entity.getImages().get(0).getUrl());
        }*/

        return SuccessTip.create(adService.createMaster(entity));
    }

    @PostMapping("/ad/id/{identifier}")
    @ApiOperation("根据identifier添加轮播图")
    public Tip createAdByIdentifier(@PathVariable String identifier, @RequestBody Ad entity) {
        entity.setEnabled(1);
        AdGroup adGroup = new AdGroup();
        adGroup.setIdentifier(identifier);
        AdGroup query = adGroupMapper.selectOne(new LambdaQueryWrapper<>(adGroup));
        entity.setGroupId(query == null ? null : query.getId());
        return SuccessTip.create(adService.createMaster(entity));
    }

    @PutMapping("/ad/{id}")
    @ApiOperation("更新轮播图")
    public Tip updateAd(@PathVariable Long id, @RequestBody AdRecord entity) {
     /*   //处理图片
        if(entity.getImages().size()!=0){
            entity.setImage(entity.getImages().get(0).getUrl());
        }*/
        //处理strategyList 拼接& 存入实体
        entity.setId(id);
        StringBuilder strategys=new StringBuilder();
        if(entity.getStrategyArray()!=null&&entity.getStrategyArray().length>0){
            int num=0;
            for (String strategy:entity.getStrategyArray()) {
                strategys.append(strategy);
                strategys.append("&");
                num+=1;
            }
            //两个字段以上 去掉尾部&
            if(num>1) {
                strategys.deleteCharAt(strategys.length() - 1);
            }
        }

        entity.setStrategy(strategys.toString());

        return SuccessTip.create(adService.updateMaster(entity,false));
    }

    @DeleteMapping("/ad/{id}")
    @ApiOperation("删除轮播图")
    public Tip deleteAd(@PathVariable Long id) {
        return SuccessTip.create(adService.deleteMaster(id));
    }

    @PutMapping("/ad/{id}/enable")
    @ApiOperation("轮播图启用")
    public Tip enableAd(@PathVariable Long id) {
        Ad ad = new Ad();
        ad.setEnabled(1);
        ad.setId(id);
        return SuccessTip.create(adService.updateMaster(ad,false));
    }

    @PutMapping("/ad/{id}/disable")
    @ApiOperation("轮播图禁用")
    public Tip disableAd(@PathVariable Long id) {
        Ad ad = new Ad();
        ad.setId(id);
        ad.setEnabled(0);
        return SuccessTip.create(adService.updateMaster(ad,false));
    }

    @ApiOperation("按组获取广告组")
    @GetMapping("/pub/ad/allGroup/{groupId}")
    public Tip getAdsFromGroup(
            @RequestParam(name = "search", required = false) String search,
            @PathVariable Integer groupId) {

        if(search!=null && search.trim().length()>0){
            return SuccessTip.create(queryAdDao.selectList(new QueryWrapper<Ad>().eq("group_id",groupId)
                    .like("name",search)
                    .or()
                    .like("type",search)
                    .eq("group_id",groupId)
            ));
        }else  return SuccessTip.create(queryAdDao.selectList(new QueryWrapper<Ad>().eq("group_id",groupId)));

    }
}
