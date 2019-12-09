package com.jfeat.am.module.cms.api;

import com.baomidou.mybatisplus.plugins.Page;
import com.jfeat.am.module.cms.services.definition.ArticleType;
import com.jfeat.am.module.cms.services.domain.dao.QueryArticleDao;
import com.jfeat.am.module.cms.services.domain.model.record.ArticleRecord;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zy on 2018/12/10.
 */
@RestController
@Api("CMS-文章管理")
@RequestMapping("/api/pub/cms")
public class CMSPublicEndpoint {
    @Resource
    QueryArticleDao queryArticleDao;

    @GetMapping("/trends")
    @ApiOperation("查询动态列表")
    public Tip queryTrends(Page<ArticleRecord> page,
                           @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                           @RequestParam(name = "title", required = false) String title,
                           @RequestParam(name = "status", required = false) String status,
                           @RequestParam(name = "viewForbidden", required = true, defaultValue = "0") Integer viewForbidden
    ) {
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        ArticleRecord record = new ArticleRecord();
        record.setStatus(status);
        record.setType(ArticleType.Trend.toString());
        record.setTitle(title);
        page.setRecords(queryArticleDao.findTrendPage(page, record, viewForbidden));
        return SuccessTip.create(page);
    }
}
