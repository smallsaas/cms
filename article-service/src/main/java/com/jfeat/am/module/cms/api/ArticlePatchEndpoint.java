package com.jfeat.am.module.cms.api;


import com.jfeat.am.module.cms.services.domain.service.CMSArticleService;
import com.jfeat.am.module.log.annotation.BusinessLog;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api("CMS-文章管理")
@RestController
@RequestMapping("/api/cms/articles")
public class ArticlePatchEndpoint  {

    @Resource
    CMSArticleService articleService;




    @ApiOperation("发布公告/重新发布")
    @PostMapping("/{id}/action/publish")
    public Tip publishNotice(@PathVariable Long id) {
        return SuccessTip.create(articleService.rePublishArticle(id));
    }


    @ApiOperation("下架公告")
    @PostMapping("/{id}/action/deprecate")
    public Tip deprecateNotice(@PathVariable Long id) {
        return SuccessTip.create(articleService.deprecatedArticle(id));
    }


    @BusinessLog(name = "sticky article", value = "action sticky article")
    @ApiOperation("置顶文章")
    @PostMapping("/{id}/action/sticky")
    public Tip stickyArticle(@PathVariable Long id) {
        return SuccessTip.create(articleService.stickyArticle(id));
    }


    @BusinessLog(name = "sticky article", value = "action sticky article")
    @ApiOperation("审核拒绝")
    @PostMapping("/{id}/action/rejected")
    public Tip rejectedArticle(@PathVariable Long id) {
        return SuccessTip.create(articleService.auditRejectedArticle(id));
    }





}
