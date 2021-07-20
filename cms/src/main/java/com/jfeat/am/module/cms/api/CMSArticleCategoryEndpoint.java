package com.jfeat.am.module.cms.api;

import com.jfeat.am.common.annotation.BusinessLog;
import com.jfeat.am.module.cms.services.domain.dao.QueryCategoryDao;
import com.jfeat.am.module.cms.services.domain.model.CategoryModel;
import com.jfeat.am.module.cms.services.domain.model.record.CategoryRecord;
import com.jfeat.am.module.cms.services.domain.service.CMSCategoryService;
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
import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Date;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-11
 */
@RestController
@Api("CMS-文章类别管理")
@RequestMapping("/api/cms/article/categories")
public class CMSArticleCategoryEndpoint  {


    @Resource
    CMSCategoryService categoryService;

    @Resource
    QueryCategoryDao queryCategoryDao;

    @BusinessLog(name = "Category", value = "create Category")
    @PostMapping
    @ApiOperation("新建 类别")
    public Tip createCategory(@RequestBody CategoryModel entity) {

        Integer affected = 0;
        try {
            affected = categoryService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    @ApiOperation("查看 类别")
    public Tip getCategory(@PathVariable Long id) {
        return SuccessTip.create(categoryService.retrieveMaster(id));
    }

    @BusinessLog(name = "Category", value = "update Category")
    @PutMapping("/{id}")
    @ApiOperation("修改 类别")
    public Tip updateCategory(@PathVariable Long id, @RequestBody CategoryModel entity) {
        entity.setId(id);
        return SuccessTip.create(categoryService.updateMaster(entity));
    }

    @BusinessLog(name = "Category", value = "delete Category")
    @DeleteMapping("/{id}")
    @ApiOperation("删除 类别")
    public Tip deleteCategory(@PathVariable Long id) {
        return SuccessTip.create(categoryService.deleteMaster(id));
    }

    @GetMapping
    @ApiOperation("获取所有 类别")
    public Tip queryCategoryies(Page<CategoryRecord> page,
                                @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                @RequestParam(name = "id", required = false) Long id,
                                @RequestParam(name = "typeId", required = false) Long typeId,
                                @RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "cover", required = false) String cover,
                                @RequestParam(name = "allowImage", required = false) Integer allowImage,
                                @RequestParam(name = "fastEntry", required = false) Integer fastEntry,
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

        CategoryRecord record = new CategoryRecord();
        record.setId(id);
        record.setTypeId(typeId);
        record.setName(name);
        record.setCover(cover);
        record.setAllowImage(allowImage);
        record.setFastEntry(fastEntry);

        page.setRecords(queryCategoryDao.findCategoryPage(page, record, orderBy));

        return SuccessTip.create(page);
    }


}
