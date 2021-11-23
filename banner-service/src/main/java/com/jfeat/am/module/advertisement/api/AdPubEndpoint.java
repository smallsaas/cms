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

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2017-09-20
 */
@RestController
@RequestMapping("/openapi/cms")
@Api("轮播图-Banner")
public class AdPubEndpoint {

    @Resource
    private AdService adService;
    @Resource
    QueryAdLibraryDao queryAdLibraryDao;
    @Resource
    QueryAdDao queryAdDao;

    @Resource
    private AdGroupService adGroupService;

    @GetMapping("/ad/groups")
    @ApiOperation("获取广告组列表")
    public Tip listAdGroups(Page<AdGroup> page,
                            @RequestParam(name = "current", required = false, defaultValue = "1") Integer pageNum,
                            @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                            @RequestParam(name = "search", required = false) String search) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        page.setRecords(adGroupService.getAllAdGroup(search));

        return SuccessTip.create(page);
    }

    @GetMapping("/ad/groups/{id}")
    @ApiOperation("获取轮播图分类详情")
    public Tip getAdGroups(@PathVariable Long id) {
        return SuccessTip.create(adGroupService.retrieveMaster(id));
    }

    @GetMapping("/ad/group/{group}")
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
    @GetMapping("/ad/records/{group}")
    public Tip Ad(@PathVariable String group,
                 @RequestParam(value = "enabled", required = false) Integer enabled) {
        return SuccessTip.create(queryAdLibraryDao.getAdRecordsByGroup(group, enabled));
    }

    @GetMapping("/ad/{id}")
    @ApiOperation("轮播图详情")
    public Tip getAdInfo(@PathVariable Long id) {
        //新建对象 进行封装image
        List<AdImage> images=new ArrayList<>();
        AdImage image= new AdImage();
        AdRecord adRecord=new AdRecord();
        adRecord = adService.getAdRecord(id);
        image.setUrl(adRecord.getImage());
        images.add(image);
        /*adRecord.setImages(images);*/
        return SuccessTip.create(adRecord);
    }

    @ApiOperation("按组获取广告组")
    @GetMapping("/ad/allGroup/{groupId}")
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

    @GetMapping("/ad")
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

    @GetMapping("/ad/libraries")
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
