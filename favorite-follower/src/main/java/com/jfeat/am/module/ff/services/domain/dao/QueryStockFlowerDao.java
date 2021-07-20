package com.jfeat.am.module.ff.services.domain.dao;


import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.ff.services.domain.model.record.StockFlowerRecord;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
public interface QueryStockFlowerDao extends BaseMapper<StockFlowerRecord> {
    List<StockFlowerRecord> findStockFlowerPage(Page<StockFlowerRecord> page, @Param("record") StockFlowerRecord record, @Param("orderBy") String orderBy);
}