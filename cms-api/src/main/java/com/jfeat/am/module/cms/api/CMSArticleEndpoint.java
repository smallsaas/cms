package com.jfeat.am.module.cms.api;

import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.cms.services.definition.ArticleType;
import com.jfeat.am.module.cms.services.domain.dao.QueryArticleDao;
import com.jfeat.am.module.cms.services.domain.service.CMSArticleService;
import com.jfeat.am.module.cms.services.persistence.model.Article;
import com.jfeat.am.module.cms.services.request.IdsRequest;
import com.jfeat.am.module.notification.services.crud.service.SubscriptionService;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.am.module.log.annotation.BusinessLog;

import com.jfeat.am.module.cms.services.domain.model.record.ArticleRecord;
import com.jfeat.am.module.cms.services.domain.model.ArticleModel;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-11
 */
@RestController
@Api("CMS-文章管理")
@RequestMapping("/api/cms")
public class CMSArticleEndpoint  {
/*
    @Resource
    SystemConfigService systemConfigService;
*/

    @Resource
    CMSArticleService articleService;
    @Resource
    SubscriptionService subscriptionService;

    @Resource
    QueryArticleDao queryArticleDao;

    @PostMapping("/one")
    @ApiOperation("新发表文章，不做中间处理，值完全由传入参数控制")
    public Tip createOne(@RequestBody ArticleModel entity) {
        // author由gw传入
        if(entity.getAuthor() == null || ("").equals(entity.getAuthor())) {
            entity.setAuthor(JWTKit.getAccount());
        }
        entity.setUserId(JWTKit.getUserId());
        return SuccessTip.create(articleService.createArticle(entity));
    }


    @BusinessLog(name = "Article", value = "create Article")
    @PostMapping("/articles")
    @ApiOperation("新发表文章")
    public Tip createArticle(@RequestBody ArticleModel entity) {
        Integer affected = 0;
        Long userId = JWTKit.getUserId();
        // author由gw传入
        if(entity.getAuthor() == null || ("").equals(entity.getAuthor())) {
            entity.setAuthor(JWTKit.getAccount());
        }
        //设置图片
        if(entity.getImage()!=null&&entity.getImage().size()>0){
            entity.setCover(entity.getImage().get(0).getUrl());
        }
        entity.setType(ArticleType.Article.toString());
        entity.setUserId(userId);
        try {
            articleService.createArticle(entity);
            // 插入消息通知
            List<String> actions = new ArrayList<>();
            actions.add("Evaluation");
            actions.add("Flower");
            actions.add("Favorite");
            actions.add("UnFavorite");
            actions.add("UnFlower");
            subscriptionService.subscribe(userId,entity.getId(),ArticleType.Article.toString(),actions);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "Article", value = "create Article")
    @PostMapping("/informations")
    @ApiOperation("新发表资讯")
    public Tip createInformation(@RequestBody ArticleModel entity) {

        Integer affected = 0;
        Long userId = JWTKit.getUserId();
        String author = JWTKit.getAccount();
        entity.setType(ArticleType.Information.toString());
        entity.setAuthor(author);
        entity.setUserId(userId);
        try {
            articleService.createArticle(entity);
            // 插入消息通知
            /*List<String> actions = new ArrayList<>();
            actions.add("Evaluation");
            actions.add("Flower");
            actions.add("Favorite");
            subscriptionService.subscribe(userId,entity.getId(),ArticleType.Article.toString(),actions);*/

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @BusinessLog(name = "Article", value = "create Article")
    @PostMapping("/diary")
    @ApiOperation("新发表日记")
    public Tip createDiary(@RequestBody ArticleModel entity) {

        Integer affected = 0;
        Long userId = JWTKit.getUserId();
        // author由gw传入
        if(entity.getAuthor() == null || ("").equals(entity.getAuthor())) {
            entity.setAuthor(JWTKit.getAccount());
        }
        entity.setType(ArticleType.Diary.toString());
        entity.setTitle("Diary");
        entity.setUserId(userId);
        try {
            articleService.createArticle(entity);
            // 插入消息通知
            List<String> actions = new ArrayList<>();
            actions.add("Evaluation");
            actions.add("Flower");
            actions.add("Favorite");
            actions.add("UnFavorite");
            actions.add("UnFlower");
            subscriptionService.subscribe(userId,entity.getId(),ArticleType.Diary.toString(),actions);


        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(entity);
    }


    @GetMapping("/articles/{id}")
    @ApiOperation("查看 文章 或 资讯 或 日记(包括点赞数，收藏数等所有的文章属性)")
    public Tip getArticle(@PathVariable Long id) {
        Long userId = JWTKit.getUserId();
        ArticleModel articleModel = articleService.showArticle(id, userId);
        //ArticleModel articleModel = articleService.selectArticleModel(id);
        //处理图片
            if(articleModel.getCover()!=null){
                List<com.jfeat.crud.base.request.Image> images=new ArrayList<>();
                com.jfeat.crud.base.request.Image image=new com.jfeat.crud.base.request.Image();
                image.setUrl(articleModel.getCover());
                images.add(image);
                articleModel.setImage(images);
             }
        return SuccessTip.create(articleModel);
    }

    @BusinessLog(name = "Article", value = "update Article")
    @PutMapping("/articles/{id}")
    @ApiOperation("update 文章 或 资讯 或 日记")
    public Tip updateArticle(@PathVariable Long id, @RequestBody ArticleModel entity) {
        entity.setId(id);
        //设置图片
        if(entity.getImage()!=null&&entity.getImage().size()>0){
            entity.setCover(entity.getImage().get(0).getUrl());
        }
        return SuccessTip.create(articleService.updateArticle(id,entity));
    }

    @BusinessLog(name = "Article", value = "delete Article")
    @ApiOperation("删除 文章 或 资讯 或 日记")
    @DeleteMapping("/articles/{id}")
    public Tip deleteArticle(@PathVariable Long id) {
        return SuccessTip.create(articleService.deleteMaster(id));
    }

    @GetMapping("/diarys")
    @ApiOperation("查询日记列表(日记列表较文章列表新增日记内容、表签、图片列表等),  ?title=x 搜索，匹配日记内容")
    public Tip queryDiarys(Page<ArticleRecord> page,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "categoryId", required = false) Long categoryId,
                             @RequestParam(name = "userId", required = false) Long userId,
                             @RequestParam(name = "subTitle", required = false) String subTitle,
                             @RequestParam(name = "subHead", required = false) String subHead,
                             @RequestParam(name = "summary", required = false) String summary,
                             @RequestParam(name = "status", required = false) String status,
                             @RequestParam(name = "isOwner", required = false, defaultValue = "0")Integer isOwner,
                             @RequestParam(name = "content", required = false)String content,
                             @RequestParam(name = "author", required = false)String author,
                             @RequestParam(name = "viewForbidden", required = true, defaultValue = "0") Integer viewForbidden
                           ) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        Long ownerId = null;
        if(isOwner == 1) {
            ownerId = JWTKit.getUserId();
        }
        ArticleRecord record = new ArticleRecord();
        record.setCategoryId(categoryId);
        record.setUserId(userId);
        record.setSubTitle(subTitle);
        record.setSubHead(subHead);
        record.setSummary(summary);
        record.setStatus(status);
        record.setType(ArticleType.Diary.toString());
        record.setContent(content);
        record.setAuthor(author);
      /*  SystemConfig config = systemConfigService.getSystemConfigByKey("DIARY_SHOW_WAY");*/
    /*    String showWay = config == null? null : config.getDataValue();*/

        //涉及到SystemConfig 此处先null处理
        String showWay=null;
       /* page.setRecords(queryArticleDao.findDiaryPage(page, record, ownerId, viewForbidden,showWay));*/
        return SuccessTip.create(page);
    }

    @PostMapping("/articleWithInfo/ids")
    @ApiOperation("根据Ids查询日记/资讯列表(含content,images等")
    public Tip queryDiarysByIds(@RequestBody IdsRequest request) {
        Page<ArticleRecord> page = new Page<>();
        Integer pageSize = request.getPageSize() == null ? 10 : request.getPageSize();
        Integer pageNum = request.getPageNum() == null ? 1 : request.getPageNum();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        if(request.getIds() != null && !request.getIds().isEmpty()) {
            page.setRecords(queryArticleDao.findArticlePageByIdsWithInfo(page, request.getIds()));
        }
        return SuccessTip.create(page);
    }

    @PostMapping("/articles/ids")
    @ApiOperation("根据ids查询Article")
    public Tip queryArticles(@RequestBody IdsRequest request) {
        Page<ArticleRecord> page = new Page<>();
        Integer pageSize = request.getPageSize() == null ? 10 : request.getPageSize();
        Integer pageNum = request.getPageNum() == null ? 1 : request.getPageNum();
        page.setSize(pageSize);
        page.setCurrent(pageNum);
        if(request.getIds() != null && !request.getIds().isEmpty()) {
            page.setRecords(queryArticleDao.findArticlePageByIds(page, request.getIds()));
        }
        return SuccessTip.create(page);
    }



    @GetMapping("/app/diarys")
    @ApiOperation("我发表的日记")
    public Tip myDiarys(Page<ArticleModel> page,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Long userId = JWTKit.getUserId();
        page.setRecords(articleService.myDiaryRecords(page,userId));

        return SuccessTip.create(page);
    }



    @GetMapping("/articles")
    @ApiOperation("根据type[Article|Diary]和categoryId查询Article")
    public Tip queryArticles(Page<ArticleRecord> page,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                             @RequestParam(name = "id", required = false) Long id,
                             @RequestParam(name = "type", required = false) String type,
                             @RequestParam(name = "categoryId", required = false) Long categoryId,
                             @RequestParam(name = "userId", required = false) Long userId,
                             @RequestParam(name = "title", required = false) String title,
                             @RequestParam(name = "subTitle", required = false) String subTitle,
                             @RequestParam(name = "subHead", required = false) String subHead,
                             @RequestParam(name = "summary", required = false) String summary,
                             @RequestParam(name = "createdTime", required = false) Date createdTime,
                             @RequestParam(name = "sticky", required = false) Integer sticky,
                             @RequestParam(name = "recommended", required = false) Integer recommended,
                             @RequestParam(name = "visitCount", required = false) Integer visitCount,
                             @RequestParam(name = "likeCount", required = false) Integer likeCount,
                             @RequestParam(name = "favoriteCount", required = false) Integer favoriteCount,
                             @RequestParam(name = "status", required = false) String status,
                             @RequestParam(name = "orderBy", required = false) String orderBy,
                             @RequestParam(name = "sort", required = false) String sort,
                             @RequestParam(name = "isOwner", required = false, defaultValue = "0")Integer isOwner ) {
        if (orderBy != null && orderBy.length() > 0) {
            if (sort != null && sort.length() > 0) {
                String pattern = "(ASC|DESC|asc|desc)";
                if (!sort.matches(pattern)) {
                    throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
                }
            } else {
                sort = "ASC";
            }
            orderBy = "`" + orderBy + "`" + " " + sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        ArticleRecord record = new ArticleRecord();
        record.setId(id);
        record.setType(type);
        record.setCategoryId(categoryId);
        record.setUserId(userId);
        record.setTitle(title);
        record.setSubTitle(subTitle);
        record.setSubHead(subHead);
        record.setSummary(summary);
        record.setCreatedTime(createdTime);
        record.setSticky(sticky);
        record.setRecommended(recommended);
        record.setVisitCount(visitCount);
        record.setLikeCount(likeCount);
        record.setFavoriteCount(favoriteCount);
        record.setStatus(status);

        Long ownerId = null;
        if(isOwner == 1) {
            ownerId = JWTKit.getUserId();
        }
        List<ArticleRecord> articlePage = queryArticleDao.findArticlePage(page, record, ownerId, orderBy);
        //处理图片
        for (ArticleRecord articleRecord:articlePage) {
            if(articleRecord.getCover()!=null){
                List<com.jfeat.crud.base.request.Image> images=new ArrayList<>();
                com.jfeat.crud.base.request.Image image=new com.jfeat.crud.base.request.Image();
                image.setUrl(articleRecord.getCover());
                images.add(image);
                articleRecord.setImage(images);
            }
        }
        page.setRecords(articlePage);

        return SuccessTip.create(page);
    }

    @GetMapping("/app/articles")
    @ApiOperation("我发表的文章")
    public Tip myArticles(Page<Article> page,
                             @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        page.setCurrent(pageNum);
        page.setSize(pageSize);

        Long userId = JWTKit.getUserId();
        page.setRecords(articleService.myArticle(page,userId,ArticleType.Article.toString()));

        return SuccessTip.create(page);
    }

    @PostMapping("/article/{id}/action/stick")
    @ApiOperation("文章置顶/取消置顶")
    public Tip stickOrNot(@PathVariable Long id) {
        return SuccessTip.create(articleService.stickyArticle(id));
    }

    @PostMapping("/article/{id}/action/forbidden")
    @ApiOperation("文章屏蔽/取消屏蔽")
    public Tip forbidOrNot(@PathVariable Long id) {
        return SuccessTip.create(articleService.forbiddenArticle(id));
    }


}
