
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
import com.jfeat.module.rss.services.domain.dao.QueryRssRulesDao;
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
import com.jfeat.module.rss.services.domain.model.RssRulesRecord;
import com.jfeat.module.rss.services.gen.persistence.model.RssRules;

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
 * @since 2022-10-22
 */
    @RestController
    @Api("RssRules")
            @RequestMapping("/api/crud/rss/rssRules/rssRuleses")
    public class RssRulesEndpoint {

    @Resource
    RssRulesService rssRulesService;

    @Resource
    QueryRssRulesDao queryRssRulesDao;



    @BusinessLog(name = "RssRules", value = "create RssRules")
    @Permission(RssRulesPermission.RSSRULES_NEW)
    @PostMapping
    @ApiOperation(value = "新建 RssRules",response = RssRules.class)
    public Tip createRssRules(@RequestBody RssRules entity){
        Integer affected=0;
        try{
                affected= rssRulesService.createMaster(entity);
            }catch(DuplicateKeyException e){
             throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
}

    @Permission(RssRulesPermission.RSSRULES_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 RssRules",response = RssRules.class)
    public Tip getRssRules(@PathVariable Long id){
                        return SuccessTip.create(rssRulesService.queryMasterModel(queryRssRulesDao, id));
            }

    @BusinessLog(name = "RssRules", value = "update RssRules")
    @Permission(RssRulesPermission.RSSRULES_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 RssRules",response = RssRules.class)
    public Tip updateRssRules(@PathVariable Long id,@RequestBody RssRules entity){
        entity.setId(id);
                return SuccessTip.create(rssRulesService.updateMaster(entity));
            }

    @BusinessLog(name = "RssRules", value = "delete RssRules")
    @Permission(RssRulesPermission.RSSRULES_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 RssRules")
    public Tip deleteRssRules(@PathVariable Long id){
            return SuccessTip.create(rssRulesService.deleteMaster(id));
        }

    @Permission(RssRulesPermission.RSSRULES_VIEW)
    @ApiOperation(value = "RssRules 列表信息",response = RssRulesRecord.class)
    @GetMapping
    @ApiImplicitParams({
        @ApiImplicitParam(name= "pageNum", dataType = "Integer"),
        @ApiImplicitParam(name= "pageSize", dataType = "Integer"),
        @ApiImplicitParam(name= "search", dataType = "String"),
                                                                                        @ApiImplicitParam(name = "id", dataType = "Long"),
                                                                                    @ApiImplicitParam(name = "name", dataType = "String"),
                                                                                    @ApiImplicitParam(name = "symbol", dataType = "String"),
                                                                                    @ApiImplicitParam(name = "decollator", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "handMethod", dataType = "String"),
                                                                                    @ApiImplicitParam(name = "category", dataType = "String"),
                                                                                    @ApiImplicitParam(name = "note", dataType = "String"),
                                                                                                    @ApiImplicitParam(name = "createTime", dataType = "Date"),
                                                                                                    @ApiImplicitParam(name = "updateTime", dataType = "Date") ,
                @ApiImplicitParam(name = "orderBy", dataType = "String"),
                @ApiImplicitParam(name = "sort", dataType = "String")
            })
    public Tip queryRssRulesPage(Page<RssRulesRecord> page,
    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    // for tag feature query
    @RequestParam(name = "tag" , required = false)String tag,
    // end tag
    @RequestParam(name = "search", required = false) String search,
                                                                                                                                        
                                                                                                                            @RequestParam(name = "name", required = false) String name,
                    
                                                                                                                            @RequestParam(name = "symbol", required = false) String symbol,
                    
                                                                                                                            @RequestParam(name = "decollator", required = false) String decollator,
                    
                                                                                                                                    @RequestParam(name = "handMethod", required = false) String handMethod,
                    
                                                                                                                            @RequestParam(name = "category", required = false) String category,
                    
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

    RssRulesRecord record = new RssRulesRecord();
                                                                                                                                                                                        record.setName(name);
                                                                                                                record.setSymbol(symbol);
                                                                                                                record.setDecollator(decollator);
                                                                                                                        record.setHandMethod(handMethod);
                                                                                                                record.setCategory(category);
                                                                                                                record.setNote(note);
                                                                                                                        record.setCreateTime(createTime);
                                                                                                                        record.setUpdateTime(updateTime);
                        
                                

    List<RssRulesRecord> rssRulesPage = queryRssRulesDao.findRssRulesPage(page, record, tag, search, orderBy, null, null);

        
        page.setRecords(rssRulesPage);

        return SuccessTip.create(page);
    }
}

