package com.jfeat.images.services.domain.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.images.services.domain.url.UrlRequest;
import com.jfeat.images.services.persistence.model.StockImages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface QueryStockImagesDao extends BaseMapper<StockImages> {
    Integer uploadImages(@Param("stockImages") List<StockImages> stockImages);
}