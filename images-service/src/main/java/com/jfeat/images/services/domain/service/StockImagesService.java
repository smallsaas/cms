package com.jfeat.images.services.domain.service;

import com.jfeat.crud.base.request.Ids;
import com.jfeat.images.services.crud.service.CRUDStockImagesService;
import com.jfeat.images.services.persistence.model.StockImages;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface StockImagesService extends CRUDStockImagesService {
        Integer bulkDelete(Ids ids);
        @Transactional
        Integer uploadImages(List<StockImages> images);
//        Integer bulkInsert(UrlRequest urlRequest);
        List<StockImages> findOwnerImages(Long ownerId, String ownerType);
        @Transactional
        Integer updateImage(Long stockId,String stockType,List<StockImages> images);

        @Transactional
        Integer deleteImages(Long stockId, String stockType);
}