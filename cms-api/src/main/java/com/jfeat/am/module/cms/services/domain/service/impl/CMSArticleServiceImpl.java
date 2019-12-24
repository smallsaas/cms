package com.jfeat.am.module.cms.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.cms.services.crud.filter.ArticleFilter;
import com.jfeat.am.module.cms.services.crud.service.CRUDArticleService;
import com.jfeat.am.module.cms.services.crud.service.impl.CRUDArticleServiceImpl;
import com.jfeat.am.module.cms.services.definition.ArticleStatus;
import com.jfeat.am.module.cms.services.definition.ArticleType;
import com.jfeat.am.module.cms.services.domain.model.ArticleModel;
import com.jfeat.am.module.cms.services.domain.service.CMSArticleService;
import com.jfeat.am.module.cms.services.persistence.dao.ArticleContentMapper;
import com.jfeat.am.module.cms.services.persistence.dao.ArticleImageMapper;
import com.jfeat.am.module.cms.services.persistence.dao.ArticleMapper;
import com.jfeat.am.module.cms.services.persistence.dao.ArticleProductRelationMapper;
import com.jfeat.am.module.cms.services.persistence.model.Article;
import com.jfeat.am.module.cms.services.persistence.model.ArticleContent;
import com.jfeat.am.module.cms.services.persistence.model.ArticleProductRelation;
import com.jfeat.am.module.evaluation.services.domain.service.StockEvaluationService;
import com.jfeat.am.module.ff.services.domain.service.StockFavoriteService;
import com.jfeat.am.module.ff.services.domain.service.StockFlowerService;
import com.jfeat.am.module.ff.services.persistence.dao.StockFavoriteMapper;
import com.jfeat.am.module.ff.services.persistence.dao.StockFlowerMapper;
import com.jfeat.am.module.ff.services.persistence.model.StockFavorite;
import com.jfeat.am.module.ff.services.persistence.model.StockFlower;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("CMSArticleService")
public class CMSArticleServiceImpl extends CRUDArticleServiceImpl implements CMSArticleService {
    @Resource
    StockFavoriteMapper stockFavoriteMapper;
    @Resource
    StockFlowerService stockFlowerService;
    @Resource
    StockFavoriteService stockFavoriteService;
    @Resource
    StockEvaluationService stockEvaluationService;
    @Resource
    StockFlowerMapper stockFlowerMapper;
    @Resource
    ArticleMapper articleMapper;
    @Resource
    ArticleContentMapper articleContentMapper;
    @Resource
    ArticleImageMapper imageMapper;
    @Resource
    CRUDArticleService crudArticleService;
/*    @Resource
    StockImagesService stockImagesService;
    @Resource
    StockImagesMapper stockImagesMapper;
    @Resource
    StockTagRelationService stockTagRelationService;*/
    @Resource
    ArticleProductRelationMapper articleProductRelationMapper;
  /*  @Resource
    SystemConfigService systemConfigService;*/

    @Transactional
    public Article createArticle(ArticleModel model) {

        Integer affected = 0;
        ArticleFilter filter = new ArticleFilter();

        ArticleContent content = new ArticleContent();
        content.setContent(model.getContent());
        model.setUpdateTime(new Date());    // 修改更新时间1

     //日志审核 涉及到systemConfig 目前先直接审核通过
        if(model.getType().compareTo(ArticleType.Diary.toString()) == 0) {
            model.setStatus(ArticleStatus.PublishArticle.toString());
        }
     /*   if(model.getType().compareTo(ArticleType.Diary.toString()) == 0) {

            SystemConfig config = systemConfigService.getSystemConfigByKey("DIARY_AUDIT_TYPE");

            if (config.getDataValue().compareTo("AUTO_AUDIT") == 0) {
                model.setStatus(ArticleStatus.PublishArticle.toString());
            } else {
                model.setStatus(ArticleStatus.Wait_Audit.toString());
            }
        }*/
        affected += crudArticleService.createMaster(model, filter, null, null);
        Long modelId = (Long) filter.result().get("id") == null ? null : (Long) filter.result().get("id");
        model.setId(modelId);
        content.setArticleId(modelId);

        // 插入图片
      /*  List<StockImages> articleImages = model.getImages();
        if(articleImages != null && !articleImages.isEmpty()) {

            if (articleImages.size() > 9) {

                throw new BusinessException(5050,"只允许上传最多9张照片");
            }

            for(StockImages image : articleImages) {
                image.setStockId(modelId);
                image.setStockType(model.getType());
            }
            affected += stockImagesService.uploadImages(articleImages);
        }*/


        // 插入文章内容
        affected += articleContentMapper.insert(content);

        // 插入标签
      /*  List<StockTagRelation> tagRelations = model.getStockTagRelation();
        if(tagRelations != null && !tagRelations.isEmpty()) {
            for(StockTagRelation tagRelation : tagRelations) {
                tagRelation.setStockType(model.getType());
                tagRelation.setStockId(modelId);
            }
            affected += stockTagRelationService.stockTagRelations(tagRelations);
        }*/

        //插入关联产品
        List<ArticleProductRelation> productRelations = model.getProductRelations();
        if(productRelations != null && !productRelations.isEmpty()) {
            for(ArticleProductRelation productRelation : productRelations) {
                productRelation.setArticleId(modelId);
                productRelation.setTargetType(model.getType()==null?"":model.getType());
                affected += articleProductRelationMapper.insert(productRelation);
            }
        }


        return model;
    }

    @Transactional
    public Integer updateArticle(Long articleId, ArticleModel model) {
        model.setId(articleId);

        Integer affected = 0;

        ArticleContent content = new ArticleContent();
        content.setArticleId(articleId);
        ArticleContent originArticleContent = articleContentMapper.selectOne(content);
        if (originArticleContent == null) {
            content.setContent(model.getContent());
            affected += articleContentMapper.insert(content);
        } else {
            originArticleContent.setContent(model.getContent());
            affected += articleContentMapper.updateById(originArticleContent);
        }

        model.setUpdateTime(new Date());    // 修改更新时间
        affected += crudArticleService.updateMaster(model, null, null, null);

        // 查询stockType
        Article article = articleMapper.selectById(articleId);

        // 更新图片信息

        // 插入图片
      /*  List<StockImages> articleImages = model.getImages();
        if(articleImages != null && !articleImages.isEmpty()) {
            if (articleImages.size() > 9) {
                throw new BusinessException(5050,"只允许上传最多9张照片");
            }else {
                affected += stockImagesService.updateImage(articleId, article.getType(), articleImages);
            }
        }*/


        // 更新标签
      /*  affected += stockTagRelationService.updateRelations(articleId,
                            article.getType(), model.getStockTagRelation());*/

        //更新关联产品信息
        List<ArticleProductRelation> productRelations = model.getProductRelations();
        if(productRelations != null && !productRelations.isEmpty()) {
            affected += articleProductRelationMapper.delete(new EntityWrapper<ArticleProductRelation>()
                    .eq(ArticleProductRelation.ARTICLE_ID,articleId).eq(ArticleProductRelation.TARGET_TYPE,model.getType()==null?"":model.getType()));

            for(ArticleProductRelation productRelation : productRelations) {
                productRelation.setArticleId(articleId);
                productRelation.setTargetType(model.getType()==null?"":model.getType());
                affected += articleProductRelationMapper.insert(productRelation);
            }
        }else {
            affected += articleProductRelationMapper.delete(new EntityWrapper<ArticleProductRelation>()
                    .eq(ArticleProductRelation.ARTICLE_ID,articleId).eq(ArticleProductRelation.TARGET_TYPE,model.getType()==null?"":model.getType()));
        }

        return affected;
    }


    @Transactional
    public Integer publishArticle(Long id) {
        Article article = articleMapper.selectById(id);
        article.setStatus(ArticleStatus.PublishArticle.toString());
        Integer result = articleMapper.updateById(article);
        return result;
    }

    @Transactional
    public Integer forbiddenArticle(Long id) {
        Article article = articleMapper.selectById(id);
        Integer display = article.getDisplay() == 1 ? 0 : 1;
        article.setDisplay(display);
        return articleMapper.updateById(article);
    }

    @Transactional
    public Integer deprecatedArticle(Long id) {
        Article article = articleMapper.selectById(id);
        article.setStatus(ArticleStatus.Deprecated.toString());
        Integer result = articleMapper.updateById(article);
        return result;
    }

    /**
     * chong xin fa bu wen zhang
     */
    @Transactional
    public Integer rePublishArticle(Long id) {
        Article article = articleMapper.selectById(id);
        if (article.getType().compareTo(ArticleStatus.Forbidden.toString()) == 0) {
            throw new BusinessException(BusinessCode.ErrorStatus);
        }

        article.setStatus(ArticleStatus.PublishArticle.toString());
        article.setUpdateTime(new Date());

        Integer result = articleMapper.updateById(article);
        return result;
    }


    /**
     * 文章 详情 包括 收藏数 以及 点赞数
     */
    public ArticleModel showArticle(Long id, Long userId) {


        Article article = articleMapper.selectById(id);

        // 点赞 总数
        Integer followerCount = stockFlowerMapper.selectCount(
                new EntityWrapper<StockFlower>().eq(StockFlower.STOCK_ID, id).eq(StockFlower.STOCK_TYPE, article.getType()));

        article.setLikeCount(followerCount);

        // 收藏 总数
        Integer favoriteCount = stockFavoriteMapper.selectCount(
                new EntityWrapper<StockFavorite>().eq(StockFavorite.STOCK_ID, id).eq(StockFavorite.STOCK_TYPE, article.getType()));

        article.setFavoriteCount(favoriteCount);

        //内容
        ArticleContent content = new ArticleContent();
        content.setArticleId(id);

        //图片和tags先null处理

    /*    //images
        List<StockImages> images = stockImagesService.findOwnerImages(id,article.getType());
        //标签
        List<StockTag> tags = stockTagRelationService.stockTag(id,article.getType());*/

        //资讯关联产品
        List<ArticleProductRelation> productRelations = articleProductRelationMapper.selectList(new EntityWrapper<ArticleProductRelation>()
        .eq(ArticleProductRelation.ARTICLE_ID,id).eq(ArticleProductRelation.TARGET_TYPE,article.getType()==null?"":article.getType()));

        JSONObject object = JSON.parseObject(JSON.toJSONString(article));

/*        // 评论 总数
        Integer evaluationCount = stockEvaluationService.stockEvaluationCount(id, article.getType());
        object.put("evaluationCount", evaluationCount == null ? 0 : evaluationCount);*/
        // fixed  null point exception
        object.put("content", articleContentMapper.selectOne(content) == null ? null : articleContentMapper.selectOne(content).getContent());

        object.put("isFlower", stockFlowerService.isFlowered(userId, id, article.getType()));

        object.put("isFavorite", stockFavoriteService.isFavorited(userId, id, article.getType()));

        /**
         * for front-end componment
         * */
        //
      /*  object.put("stockTagRelation",stockTagRelationService.relations(id,article.getType()));*/

        //image
      /*  object.put("images", images);*/
        //资讯关联产品
        object.put("productRelations", productRelations);
        //tag
      /*  object.put("tags", tags);*/
        Integer count = article.getVisitCount();
        article.setVisitCount(++count);
        article.setId(id);
        articleMapper.updateById(article);


        ArticleModel model = JSONObject.parseObject(JSON.toJSONString(object), ArticleModel.class);
        return model;
    }


    /**
     * 我发表的文章 or others
     */
    public List<Article> myArticle(Page<Article> page, Long userId, String stockType) {
        List<Article> articles = articleMapper.selectPage(page, new EntityWrapper<Article>()
                .eq(Article.USER_ID, userId).eq(Article.TYPE, stockType).orderBy(Article.CREATED_TIME, false));
        return articles;
    }

    /**
     * 我发表的 Diary
     */
    public List<ArticleModel> myDiaryRecords(Page<ArticleModel> page, Long userId) {
        List<Article> articles = articleMapper.selectPage(page, new EntityWrapper<Article>()
                .eq(Article.USER_ID, userId).eq(Article.TYPE, ArticleType.Diary.toString()).orderBy(Article.CREATED_TIME, false));
        List<ArticleModel> models = new ArrayList<>();
        if (articles!=null&&articles.size()>0){
            for (Article article : articles){

                JSONObject articleObj = JSON.parseObject(JSON.toJSONString(article));
                ArticleContent content = articleContentMapper.selectById(article.getId());
                articleObj.put("content",content==null?null:content.getContent());
                ArticleModel model = JSON.parseObject(JSON.toJSONString(articleObj),ArticleModel.class);
                models.add(model);
            }
        }
        return models;
    }


    /**
     * 置顶文章
     * */
    public Integer stickyArticle(Long articleId){

        Article article = articleMapper.selectById(articleId);
        Integer sticky = article.getSticky() == 1 ? 0 : 1;
        article.setSticky(sticky);
        return articleMapper.updateById(article);
    }

    /**
     * 审核拒绝
     * */
    public Integer auditRejectedArticle(Long articleId){

        Article article = articleMapper.selectById(articleId);

        article.setStatus(ArticleStatus.Audit_Rejected.toString());
        return articleMapper.updateById(article);
    }

    public ArticleModel selectArticleModel(Long articleId){
        ArticleModel article = articleMapper.selectArticleModel(articleId);
        return article;
    };

}
