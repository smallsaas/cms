package com.jfeat.images.services.domain.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.images.services.crud.service.impl.CRUDStockImagesServiceImpl;
import com.jfeat.images.services.domain.dao.QueryStockImagesDao;
import com.jfeat.images.services.domain.service.StockImagesService;
import com.jfeat.images.services.domain.url.UrlRequest;
import com.jfeat.images.services.persistence.dao.StockImagesMapper;
import com.jfeat.images.services.persistence.model.StockImages;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service("stockImagesService")
public class StockImagesServiceImpl extends CRUDStockImagesServiceImpl implements StockImagesService {
    @Resource
    private StockImagesMapper stockImagesMapper;

    @Resource
    QueryStockImagesDao queryStockImagesDao;

    /**
     * 添加单张
     *
     * @Param ownerId, ownerId, List<String> urls;
     */
    @Transactional
    public Integer uploadImages(List<StockImages> images) {
        return queryStockImagesDao.uploadImages(images);
    }


    /**
     * 属主的所有的图片
     *
     * @Param Long ownerId,String ownerType
     */
    public List<StockImages> findOwnerImages(Long stockId, String stockType) {

        List<StockImages> images = stockImagesMapper.selectList(new EntityWrapper<StockImages>()
                .eq(StockImages.STOCK_ID, stockId)
                .eq(StockImages.STOCK_TYPE, stockType));

        return images;
    }

    @Override
    public Integer bulkDelete(Ids ids) {
        Integer success = stockImagesMapper.deleteBatchIds(ids.getIds());
        return success;
    }

    @Transactional
    public Integer updateImage(Long stockId, String stockType, List<StockImages> images) {
        int effect = 0;
        effect += stockImagesMapper.delete(new EntityWrapper<StockImages>()
                .eq(StockImages.STOCK_ID, stockId)
                .eq(StockImages.STOCK_TYPE, stockType));
        if (images != null && !images.isEmpty()) {
            for (StockImages image : images) {
                image.setStockType(stockType);
                image.setStockId(stockId);
            }
            effect += queryStockImagesDao.uploadImages(images);
        }

        return effect;
    }

    @Transactional
    public Integer deleteImages(Long stockId, String stockType) {
        return stockImagesMapper.delete(new EntityWrapper<StockImages>()
                .eq(StockImages.STOCK_ID, stockId).eq(StockImages.STOCK_TYPE, stockType));
    }

}
