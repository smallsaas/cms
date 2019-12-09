package com.jfeat.am.module.cms.services.domain.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.cms.services.domain.model.record.ArticleRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Code Generator on 2018-07-11
 */
public interface QueryArticleDao extends BaseMapper<ArticleRecord> {
    List<ArticleRecord> findArticlePage(Page<ArticleRecord> page, @Param("record") ArticleRecord record, @Param("ownerId") Long ownerId, @Param("orderBy") String orderBy);
/*    List<ArticleRecord> findDiaryPage(Page<ArticleRecord> page, @Param("record") ArticleRecord record,@Param("ownerId") Long ownerId,
                                      @Param("viewForbidden") Integer viewForbidden, @Param("orderBy") String orderBy);*/
    List<ArticleRecord> findArticlePageByIdsWithInfo(Page<ArticleRecord> page, @Param("ids") List<Long> ids);
    List<ArticleRecord> findArticlePageByIds(Page<ArticleRecord>page, @Param("ids") List<Long> ids);

    List<ArticleRecord> findTrendPage(Page<ArticleRecord> page, @Param("record") ArticleRecord record, @Param("viewForbidden") Integer viewForbidden);

}