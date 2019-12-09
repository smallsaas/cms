package com.jfeat.am.module.cms.services.domain.model.record;


import com.jfeat.am.module.cms.services.persistence.model.Article;
import com.jfeat.am.module.cms.services.persistence.model.ArticleProductRelation;

import java.util.List;

/**
 * Created by Code Generator on 2018-07-11
 */
public class ArticleRecord extends Article {


    // 收藏 总数
    Integer favoriteCount;

    // 点赞 总数
    Integer followerCount;

    //评论总数
    Integer evaluationCount;

    String content;

/*    List<StockImages> images;

    List<StockTagRelation> stockTagRelation;

    List<StockTag> tags;*/

    List<ArticleProductRelation> productRelations;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public List<ArticleProductRelation> getProductRelations() {
        return productRelations;
    }

    public void setProductRelations(List<ArticleProductRelation> productRelations) {
        this.productRelations = productRelations;
    }

    @Override
    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    @Override
    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getEvaluationCount() {
        return evaluationCount;
    }

    public void setEvaluationCount(Integer evaluationCount) {
        this.evaluationCount = evaluationCount;
    }
}
