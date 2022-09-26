
package com.jfeat.module.rss.api.manage;


import com.jfeat.crud.plus.META;
import com.jfeat.am.core.jwt.JWTKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.module.rss.services.domain.dao.QueryRssItemDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.module.rss.api.permission.*;
import com.jfeat.am.common.annotation.Permission;
import java.math.BigDecimal;

import com.jfeat.module.rss.services.domain.service.*;
import com.jfeat.module.rss.services.domain.model.RssItemRecord;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;

    import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import com.alibaba.fastjson.JSONArray;
/**
 * <p>
 *  api
 * </p>
 *
 * @author Code generator
 * @since 2022-09-26
 */
    @RestController
    @Api("RssItem")
            @RequestMapping("/api/crud/rss/rssItem/rssItems")
    public class RssItemEndpoint {

    @Resource
    RssItemService rssItemService;

    @Resource
    QueryRssItemDao queryRssItemDao;



    @BusinessLog(name = "RssItem", value = "create RssItem")
    @Permission(RssItemPermission.RSSITEM_NEW)
    @PostMapping
    @ApiOperation(value = "新建 RssItem",response = RssItem.class)
    public Tip createRssItem(@RequestBody RssItem entity){
        Integer affected=0;
        try{
                affected= rssItemService.createMaster(entity);
            }catch(DuplicateKeyException e){
             throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
}

    @Permission(RssItemPermission.RSSITEM_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 RssItem",response = RssItem.class)
    public Tip getRssItem(@PathVariable Long id){
                        return SuccessTip.create(rssItemService.queryMasterModel(queryRssItemDao, id));
            }

    @BusinessLog(name = "RssItem", value = "update RssItem")
    @Permission(RssItemPermission.RSSITEM_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 RssItem",response = RssItem.class)
    public Tip updateRssItem(@PathVariable Long id,@RequestBody RssItem entity){
        entity.setId(id);
                return SuccessTip.create(rssItemService.updateMaster(entity));
            }

    @BusinessLog(name = "RssItem", value = "delete RssItem")
    @Permission(RssItemPermission.RSSITEM_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 RssItem")
    public Tip deleteRssItem(@PathVariable Long id){
            return SuccessTip.create(rssItemService.deleteMaster(id));
        }

    @Permission(RssItemPermission.RSSITEM_VIEW)
    @ApiOperation(value = "RssItem 列表信息",response = RssItemRecord.class)
    @GetMapping
    @ApiImplicitParams({
        @ApiImplicitParam(name= "pageNum", dataType = "Integer"),
        @ApiImplicitParam(name= "pageSize", dataType = "Integer"),
        @ApiImplicitParam(name= "search", dataType = "String"),
                                                                                        @ApiImplicitParam(name = "id", dataType = "Long"),
                                                                                            @ApiImplicitParam(name = "pid", dataType = "Long"),
                                                                                    @ApiImplicitParam(name = "title", dataType = "String"),
                                                                                    @ApiImplicitParam(name = "pictures", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "status", dataType = "Integer"),
                                                                                                    @ApiImplicitParam(name = "sortNumber", dataType = "Integer"),
                                                                                    @ApiImplicitParam(name = "note", dataType = "String"),
                                                                                                    @ApiImplicitParam(name = "createTime", dataType = "Date"),
                                                                                                    @ApiImplicitParam(name = "updateTime", dataType = "Date") ,
                @ApiImplicitParam(name = "orderBy", dataType = "String"),
                @ApiImplicitParam(name = "sort", dataType = "String")
            })
    public Tip queryRssItemPage(Page<RssItemRecord> page,
    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    // for tag feature query
    @RequestParam(name = "tag" , required = false)String tag,
    // end tag
    @RequestParam(name = "search", required = false) String search,
                                                                                                                                        
                                                                                                                                    @RequestParam(name = "pid", required = false) Long pid,
                    
                                                                                                                            @RequestParam(name = "title", required = false) String title,
                    
                                                                                                                            @RequestParam(name = "pictures", required = false) String pictures,
                    
                                                                                                                                    @RequestParam(name = "status", required = false) Integer status,
                    
                                                                                                                                            @RequestParam(name = "sortNumber", required = false) Integer sortNumber,
                    
                                                                                                                            @RequestParam(name = "note", required = false) String note,
                    
                                                                                                                    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                                @RequestParam(name = "createTime", required = false) Date createTime,
                    
                                                                                                                    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
                                                @RequestParam(name = "updateTime", required = false) Date updateTime,
        @RequestParam(name = "orderBy", required = false) String orderBy,
        @RequestParam(name = "sort", required = false)  String sort) {
                    
            if(orderBy!=null&&orderBy.length()>0){
        if(sort!=null&&sort.length()>0){
        String sortPattern = "(ASC|DESC|asc|desc)";
        if(!sort.matches(sortPattern)){
        throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
        }
        }else{
        sort = "ASC";
        }
        orderBy = "`"+orderBy+"`" +" "+sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

    RssItemRecord record = new RssItemRecord();
                                                                                                                                                                                        record.setPid(pid);
                                                                                                                record.setTitle(title);
                                                                                                                record.setPictures(pictures);
                                                                                                                record.setStatus(status);
                                                                                                                        record.setSortNumber(sortNumber);
                                                                                                                record.setNote(note);
                                                                                                                        record.setCreateTime(createTime);
                                                                                                                        record.setUpdateTime(updateTime);
                        
                                

    List<RssItemRecord> rssItemPage = queryRssItemDao.findRssItemPage(page, record, tag, search, orderBy, null, null);

        
        page.setRecords(rssItemPage);

        return SuccessTip.create(page);
    }
}

