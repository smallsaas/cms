package com.jfeat.am.module.cms.services.persistence.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.cms.services.domain.model.ArticleModel;
import com.jfeat.am.module.cms.services.persistence.model.Article;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2018-03-27
 */
public interface ArticleMapper extends BaseMapper<Article> {

    ArticleModel selectArticleModel(@Param("id") Long id);
}
