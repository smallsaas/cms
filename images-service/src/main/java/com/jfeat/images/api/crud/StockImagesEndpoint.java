package com.jfeat.images.api.crud;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.images.services.domain.dao.QueryStockImagesDao;
import com.jfeat.images.services.domain.service.StockImagesService;
import com.jfeat.images.services.domain.url.UrlRequest;
import com.jfeat.images.services.persistence.model.StockImages;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 *  api
 * </p>
 *
 * @author Code Generator
 * @since 2018-08-07
 */
@RestController
@RequestMapping("/api/stock/images")
public class StockImagesEndpoint {
    @Resource
    StockImagesService stockImagesService;

    @Resource
    QueryStockImagesDao queryStockImagesDao;

    @PostMapping
    @ApiOperation("上传图片")
    public Tip createImagesService(@RequestBody StockImages entity) {

        Integer affected = 0;
        try{
                affected = stockImagesService.createMaster(entity);

        }catch (DuplicateKeyException e){
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation("获取图片信息")
    public Tip getImagesService(@PathVariable Long id) {
            return SuccessTip.create(stockImagesService.retrieveMaster(id));
        }

//    @PutMapping("/{id}")
//    public Tip updateImagesService(@RequestBody List<StockImages> entity) {
//        return SuccessTip.create(stockImagesService.updateArticleImage(entity));
//    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除图片")
    public Tip deleteImagesService(@PathVariable Long id) {
            return SuccessTip.create(stockImagesService.deleteMaster(id));
        }

    @DeleteMapping("/bulk")
    @ApiOperation("批量删除图片")
    public Tip deleteList(@RequestBody Ids ids) {
            return SuccessTip.create(stockImagesService.bulkDelete(ids));
    }

    @PostMapping("/bulk")
    @ApiOperation("批量上传图片")
    public Tip createList(@RequestBody List<StockImages> stockImagesList) {
        return  SuccessTip.create(queryStockImagesDao.uploadImages(stockImagesList));
    }



    @GetMapping("/owner")
    public Tip findOwnerImages(@RequestParam(value="ownerId") Long ownerId,
                               @RequestParam(value="ownerType", required = false) String ownerType) {
        return SuccessTip.create(stockImagesService.findOwnerImages(ownerId, ownerType));
    }
}
