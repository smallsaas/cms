package com.jfeat.am.module.advertisement.api;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
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
@RequestMapping("/api/cms")
@Api("AD-轮播图")
public class AdEndpoint  {

    @Resource
    private AdService adService;
    @Resource
    private AdGroupMapper adGroupMapper;
    @Resource
    QueryAdLibraryDao queryAdLibraryDao;
    @Resource
    QueryAdDao queryAdDao;

    @GetMapping("/pub/ad/group/{group}")
    @ApiOperation("按组获取轮播图 [带组信息]")
    public Tip getAdGroup(@PathVariable String group) {
        AdGroupedModel model = adService.getAdRecordsByGroup(group);
        if(model != null && model.getAds() != null) {
            List<Ad> temp = model.getAds().stream().sorted((t1, t2) -> t1.getType().compareTo(t2.getType())).collect(Collectors.toList());
            model.setAds(temp);
        }
        return SuccessTip.create(model);
    }

    @ApiOperation("按组获取轮播图 group=1 首页轮播图")
    @GetMapping("/pub/ad/records/{group}")
    public Tip Ad(@PathVariable String group,
                 @RequestParam(value = "enabled", required = false) Integer enabled) {
        return SuccessTip.create(queryAdLibraryDao.getAdRecordsByGroup(group, enabled));
    }

    @PostMapping("/ad")
    @ApiOperation("添加轮播图")
    public Tip createAd(@RequestBody AdRecord entity) {
        entity.setEnabled(1);
        //处理图片
        if(entity.getImages()!=null&&entity.getImages().size()>0){
            entity.setImage(entity.getImages().get(0).getUrl());
        }

        return SuccessTip.create(adService.createMaster(entity));
    }

    @PostMapping("/ad/{groupId}")
    @ApiOperation("根据GroupId 添加轮播图")
    public Tip createAd(@PathVariable Long groupId, @RequestBody AdRecord entity) {
        entity.setEnabled(1);
        entity.setGroupId(groupId);
        //处理图片
        if(entity.getImages()!=null&&entity.getImages().size()>0){
            entity.setImage(entity.getImages().get(0).getUrl());
        }

        return SuccessTip.create(adService.createMaster(entity));
    }

    @PostMapping("/ad/id/{identifier}")
    @ApiOperation("根据identifier添加轮播图")
    public Tip createAdByIdentifier(@PathVariable String identifier, @RequestBody Ad entity) {
        entity.setEnabled(1);
        AdGroup adGroup = new AdGroup();
       /* adGroup.setIdentifier(identifier);*/
        AdGroup query = adGroupMapper.selectOne(adGroup);
        entity.setGroupId(query == null ? null : query.getId());
        return SuccessTip.create(adService.createMaster(entity));
    }

    @PutMapping("/ad/{id}")
    @ApiOperation("更新轮播图")
    public Tip updateAd(@PathVariable Long id, @RequestBody AdRecord entity) {
        //处理图片
        if(entity.getImages().size()!=0){
            entity.setImage(entity.getImages().get(0).getUrl());
        }
        //处理strategyList 拼接& 存入实体
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

        return SuccessTip.create(adService.updateMaster(entity));
    }

    @GetMapping("/pub/ad/{id}")
    @ApiOperation("轮播图详情")
    public Tip getAdInfo(@PathVariable Long id) {
        //新建对象 进行封装image
        List<AdImage> images=new ArrayList<>();
        AdImage image= new AdImage();
        AdRecord adRecord=new AdRecord();
        adRecord = adService.getAdRecord(id);
        image.setUrl(adRecord.getImage());
        images.add(image);
        adRecord.setImages(images);
        return SuccessTip.create(adRecord);
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
            return SuccessTip.create(queryAdDao.selectList(new EntityWrapper<Ad>().eq("group_id",groupId)
                    .like("name",search)
                    .or()
                    .like("type",search)
                    .eq("group_id",groupId)
            ));
        }else  return SuccessTip.create(queryAdDao.selectList(new EntityWrapper<Ad>().eq("group_id",groupId)));

    }

    @GetMapping("/pub/ad/")
    @ApiOperation("广告列表")
    public Tip queryAdLibraryies(Page<AdRecord> page,
                                 @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "search", required = false) String search,
                                 @RequestParam(name = "enabled", required = false) Integer enabled,
                                 @RequestParam(name = "groupId", required = false) Long groupId,
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

        AdRecord record = new AdRecord();
        record.setGroupId(groupId);
        record.setName(search);
        record.setEnabled(enabled);

        page.setRecords(queryAdDao.findAdPage(page,record,orderBy,search));

        return SuccessTip.create(page);
    }

    @GetMapping("/pub/ad/libraries")
    @ApiOperation("图库列表")
    public Tip queryAdLibraryies(Page<AdLibraryRecord> page,
                                 @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                 @RequestParam(name = "id", required = false) Long id,
                                 @RequestParam(name = "url", required = false) String url,
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

        AdLibraryRecord record = new AdLibraryRecord();
        record.setId(id);
        record.setUrl(url);

        page.setRecords(queryAdLibraryDao.findAdLibraryPage(page, record, orderBy));

        return SuccessTip.create(page);
    }
}
