
package com.jfeat.images.services.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.images.services.domain.url.UrlRequest;
import com.jfeat.images.services.persistence.model.StockImages;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author Code Generator
 * @since 2018-08-07
 */
public interface StockImagesMapper extends BaseMapper<StockImages> {
   // Integer insertBatch(@Param("stock_images") List<StockImages> stock_images);
}