package com.jfeat.am.module.advertisement.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdLibraryDao;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdLibraryRecord;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.am.module.advertisement.services.persistence.model.Ad;
import com.jfeat.am.module.advertisement.services.service.AdService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
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
@RequestMapping("/api/adm/cms/ad")
@Api("后台轮播图管理-Banner")
public class AdEndpoint  {

    @Resource
    private AdService adService;


    @Resource
    QueryAdLibraryDao queryAdLibraryDao;

    @Resource
    QueryAdDao queryAdDao;


    @PostMapping
    @ApiOperation("添加轮播图")
    public Tip createAd(@RequestBody AdRecord entity) {
        entity.setEnabled(1);
        //处理图片
       /* if(entity.getImages()!=null&&entity.getImages().size()>0){
            entity.setImage(entity.getImages().get(0).getUrl());
        }*/

        return SuccessTip.create(adService.createMaster(entity));
    }

    
    @GetMapping("/{id}")
    @ApiOperation("获取轮播图详情")
    public Tip getAdGroups(@PathVariable Long id) {
        return SuccessTip.create(adService.retrieveMaster(id));
    }

    // @GetMapping("/{id}")
    // @ApiOperation("获取轮播图详情")
    // public Tip getAdInfo(@PathVariable Long id) {
    //     //新建对象 进行封装image
    //     List<AdImage> images=new ArrayList<>();
    //     AdImage image= new AdImage();
    //     AdRecord adRecord=new AdRecord();
    //     adRecord = adService.getAdRecord(id);
    //     image.setUrl(adRecord.getImage());
    //     images.add(image);
    //     /*adRecord.setImages(images);*/
    //     return SuccessTip.create(adRecord);
    // }

    @PutMapping("/{id}")
    @ApiOperation("更新轮播图")
    public Tip updateAd(@PathVariable Long id, @RequestBody AdRecord entity) {
         /*   
         //处理图片
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

    @DeleteMapping("/{id}")
    @ApiOperation("删除轮播图")
    public Tip deleteAd(@PathVariable Long id) {
        return SuccessTip.create(adService.deleteMaster(id));
    }

    /**
     * 依据组标识为前端提供 轮播图
     * @param group
     * @return
     */
    @GetMapping("/group/{groupId}")
    @ApiOperation("根据分组标识获取轮播图")
    public Tip getAdByGroupId(@PathVariable Long groupId) {
        return SuccessTip.create(adService.getAdRecordsByGroupId(groupId));
    }


    @GetMapping
    @ApiOperation("轮播图列表")
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
    

    /**
     * 图库列表
     * @param page
     * @param pageNum
     * @param pageSize
     * @param id
     * @param url
     * @param orderBy
     * @param sort
     * @return
     */
    @GetMapping("/libraries")
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

    /**
     * 轮播图操作
     * @param id
     * @return
     */


    @PutMapping("/{id}/enable")
    @ApiOperation("轮播图启用")
    public Tip enableAd(@PathVariable Long id) {
        Ad ad = new Ad();
        ad.setEnabled(1);
        ad.setId(id);
        return SuccessTip.create(adService.updateMaster(ad,false));
    }

    @PutMapping("/{id}/disable")
    @ApiOperation("轮播图禁用")
    public Tip disableAd(@PathVariable Long id) {
        Ad ad = new Ad();
        ad.setId(id);
        ad.setEnabled(0);
        return SuccessTip.create(adService.updateMaster(ad,false));
    }

}
