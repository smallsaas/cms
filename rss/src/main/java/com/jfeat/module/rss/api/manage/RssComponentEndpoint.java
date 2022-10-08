
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
import com.jfeat.module.rss.services.domain.dao.QueryRssComponentDao;
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
import com.jfeat.module.rss.services.domain.model.RssComponentRecord;
import com.jfeat.module.rss.services.gen.persistence.model.RssComponent;

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
 * @since 2022-09-30
 */
    @RestController
    @Api("RssComponent")
            @RequestMapping("/api/crud/rss/rssComponent/rssComponents")
    public class RssComponentEndpoint {

    @Resource
    RssComponentService rssComponentService;

    @Resource
    QueryRssComponentDao queryRssComponentDao;



    @BusinessLog(name = "RssComponent", value = "create RssComponent")
    @Permission(RssComponentPermission.RSSCOMPONENT_NEW)
    @PostMapping
    @ApiOperation(value = "新建 RssComponent",response = RssComponent.class)
    public Tip createRssComponent(@RequestBody RssComponent entity){
        Integer affected=0;
        try{
                affected= rssComponentService.createMaster(entity);
            }catch(DuplicateKeyException e){
             throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
}

    @Permission(RssComponentPermission.RSSCOMPONENT_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 RssComponent",response = RssComponent.class)
    public Tip getRssComponent(@PathVariable Long id){
                        return SuccessTip.create(rssComponentService.queryMasterModel(queryRssComponentDao, id));
            }

    @BusinessLog(name = "RssComponent", value = "update RssComponent")
    @Permission(RssComponentPermission.RSSCOMPONENT_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 RssComponent",response = RssComponent.class)
    public Tip updateRssComponent(@PathVariable Long id,@RequestBody RssComponent entity){
        entity.setId(id);
                return SuccessTip.create(rssComponentService.updateMaster(entity));
            }

    @BusinessLog(name = "RssComponent", value = "delete RssComponent")
    @Permission(RssComponentPermission.RSSCOMPONENT_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 RssComponent")
    public Tip deleteRssComponent(@PathVariable Long id){
            return SuccessTip.create(rssComponentService.deleteMaster(id));
        }

    @Permission(RssComponentPermission.RSSCOMPONENT_VIEW)
    @ApiOperation(value = "RssComponent 列表信息",response = RssComponentRecord.class)
    @GetMapping
    @ApiImplicitParams({
        @ApiImplicitParam(name= "pageNum", dataType = "Integer"),
        @ApiImplicitParam(name= "pageSize", dataType = "Integer"),
        @ApiImplicitParam(name= "search", dataType = "String"),
                                                                                        @ApiImplicitParam(name = "id", dataType = "Long"),
                                                                                                            @ApiImplicitParam(name = "rssItemId", dataType = "Long"),
                                                                                    @ApiImplicitParam(name = "name", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "componentName", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "componentType", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "componentOption", dataType = "String"),
                                                                                                    @ApiImplicitParam(name = "component_formInputOption", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "componentTag", dataType = "String") ,
                @ApiImplicitParam(name = "orderBy", dataType = "String"),
                @ApiImplicitParam(name = "sort", dataType = "String")
            })
    public Tip queryRssComponentPage(Page<RssComponentRecord> page,
    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    // for tag feature query
    @RequestParam(name = "tag" , required = false)String tag,
    // end tag
    @RequestParam(name = "search", required = false) String search,
                                                                                                                                        
                                                                                                                                                    @RequestParam(name = "rssItemId", required = false) Long rssItemId,
                    
                                                                                                                            @RequestParam(name = "name", required = false) String name,
                    
                                                                                                                                    @RequestParam(name = "componentName", required = false) String componentName,
                    
                                                                                                                                    @RequestParam(name = "componentType", required = false) String componentType,
                    
                                                                                                                                    @RequestParam(name = "componentOption", required = false) String componentOption,
                    
                                                                                                                                                    @RequestParam(name = "componentFormInputOption", required = false) String componentFormInputOption,
                    
                                                                                                                                    @RequestParam(name = "componentTag", required = false) String componentTag,
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

    RssComponentRecord record = new RssComponentRecord();
                                                                                                                                                                                                        record.setRssItemId(rssItemId);
                                                                                                                record.setName(name);
                                                                                                                        record.setComponentName(componentName);
                                                                                                                        record.setComponentType(componentType);
                                                                                                                        record.setComponentOption(componentOption);
                                                                                                                                        record.setComponentFormInputOption(componentFormInputOption);
                                                                                                                        record.setComponentTag(componentTag);
                        
                                

    List<RssComponentRecord> rssComponentPage = queryRssComponentDao.findRssComponentPage(page, record, tag, search, orderBy, null, null);

        
        page.setRecords(rssComponentPage);

        return SuccessTip.create(page);
    }
}

