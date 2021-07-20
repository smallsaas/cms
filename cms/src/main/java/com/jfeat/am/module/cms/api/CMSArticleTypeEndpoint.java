package com.jfeat.am.module.cms.api;

import com.jfeat.am.common.annotation.BusinessLog;
import com.jfeat.am.module.cms.services.domain.service.CMSTypeService;
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
import com.jfeat.am.module.cms.services.domain.dao.QueryTypeDao;
import com.jfeat.am.module.log.annotation.BusinessLog;

import com.jfeat.am.module.cms.services.domain.model.record.TypeRecord;
import com.jfeat.am.module.cms.services.domain.model.TypeModel;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;



/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-11
 */
@RestController
@Api("CMS-文章类型管理")
@RequestMapping("/api/cms/article/types")
public class CMSArticleTypeEndpoint {


    @Resource
    CMSTypeService typeService;

    @Resource
    QueryTypeDao queryTypeDao;

    @BusinessLog(name = "Type", value = "create Type")
    @PostMapping
    @ApiOperation("新建 类型")
    public Tip createType(@RequestBody TypeModel entity) {

        Integer affected = 0;
        try {
            affected = typeService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation("查看 类型")
    public Tip getType(@PathVariable Long id) {
        return SuccessTip.create(typeService.retrieveMaster(id));
    }

    @BusinessLog(name = "Type", value = "update Type")
    @PutMapping("/{id}")
    @ApiOperation("修改 类型")
    public Tip updateType(@PathVariable Long id, @RequestBody TypeModel entity) {
        entity.setId(id);
        return SuccessTip.create(typeService.updateMaster(entity));
    }

    @BusinessLog(name = "Type", value = "delete Type")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 类型")
    public Tip deleteType(@PathVariable Long id) {
        return SuccessTip.create(typeService.deleteMaster(id));
    }

    @GetMapping
    public Tip queryTypes(Page<TypeRecord> page,
                          @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                          @RequestParam(name = "id", required = false) Long id,
                          @RequestParam(name = "identifier", required = false) Long identifier,
                          @RequestParam(name = "name", required = false) String name,
                          @RequestParam(name = "orderBy", required = false) String orderBy,
                          @RequestParam(name = "sort", required = false) String sort) {
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

        TypeRecord record = new TypeRecord();
        record.setId(id);
        record.setIdentifier(identifier);
        record.setName(name);

        page.setRecords(queryTypeDao.findTypePage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}
