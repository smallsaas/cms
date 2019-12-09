package com.jfeat.am.module.cms.services.domain.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.cms.services.crud.service.CRUDArticleService;
import com.jfeat.am.module.cms.services.definition.ArticleStatus;
import com.jfeat.am.module.cms.services.domain.model.ArticleModel;
import com.jfeat.am.module.cms.services.domain.model.record.ArticleRecord;
import com.jfeat.am.module.cms.services.persistence.model.Article;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface CMSArticleService extends CRUDArticleService{


    @Transactional
    Article createArticle(ArticleModel model);

    @Transactional
    Integer updateArticle(Long articleId,ArticleModel model);


    Integer publishArticle(Long id);
    Integer forbiddenArticle(Long id);



    /**
     *
     *  文章 详情 包括 收藏数 以及 点赞数
     * */
    ArticleModel showArticle(Long id,Long userId);

    /**
     * 我发表的文章or others
     * */
    List<Article> myArticle(Page<Article> page, Long userId, String typeName);


    /**
     * xia jia wen zhang
     * */
    @Transactional
    Integer deprecatedArticle(Long id);

    /**
     *  chong xin fa bu wen zhang
     * */
    @Transactional
    Integer rePublishArticle(Long id);


    /**
     * 我发表的 Diary
     */
    List<ArticleModel> myDiaryRecords(Page<ArticleModel> page, Long userId);


    /**
     * 置顶文章
     * */
    public Integer stickyArticle(Long articleId);


    /**
     * 审核拒绝
     * */
    Integer auditRejectedArticle(Long articleId);

}