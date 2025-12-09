package com.jfeat.am.module.advertisement.api;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.core.jwt.JWTKit;
import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdDao;
import com.jfeat.am.module.advertisement.services.domain.model.record.AdRecord;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author admin
 * @since 2025-12
 */
@RestController
@RequestMapping("/api/cms/ad")
@Api("轮播图-Banner")
public class UserAdEndpoint {

    @Resource
    QueryAdDao queryAdDao;

    @GetMapping("/current")
    @ApiOperation("广告列表带社区")
    public Tip queryAdLibraryiesByappidAndOrgId(Page<AdRecord> page,
                                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                                @RequestParam(name = "search", required = false) String search,
                                                @RequestParam(name = "orderBy", required = false) String orderBy,
                                                @RequestParam(name = "sort", required = false) String sort) {
        Long userId = JWTKit.getUserId();
        if (userId==null){
            throw new BusinessException(BusinessCode.NoPermission,"没有登录");
        }
        Long currentOrgId = JWTKit.getOrgId();
        String appid = JWTKit.getAppid();

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


        AdRecord record = new AdRecord();
        record.setName(search);
        record.setEnabled(1);
        record.setOrgId(currentOrgId);
        record.setAppid(appid);

        page.setRecords(queryAdDao.findAdPage(page,record,orderBy,search));

        return SuccessTip.create(page);
    }
}
