package com.jfeat.am.module.advertisement.api;

import com.jfeat.am.module.advertisement.services.domain.dao.QueryAdLibraryDao;
import com.jfeat.am.module.advertisement.services.service.AdLibraryService;
import com.jfeat.am.module.advertisement.services.persistence.model.AdLibrary;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.base.request.Ids;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * <p>
 * api
 * </p>
 *
 * @author Code Generator
 * @since 2018-12-13
 */
@RestController
@Api("AD-轮播图")
@RequestMapping("/api/adm/cms/ad/libraries")
public class AdLibraryEndpoint  {

    @Resource
    AdLibraryService adLibraryService;

    @Resource
    QueryAdLibraryDao queryAdLibraryDao;

    @PostMapping
    public Tip createAdLibrary(@RequestBody AdLibrary entity) {

        Integer affected = 0;
        try {
            affected = adLibraryService.createMaster(entity);

        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
    }

    @GetMapping("/{id}")
    public Tip getAdLibrary(@PathVariable Long id) {
        return SuccessTip.create(adLibraryService.retrieveMaster(id));
    }

    @PutMapping("/{id}")
    public Tip updateAdLibrary(@PathVariable Long id, @RequestBody AdLibrary entity) {
        entity.setId(id);
        return SuccessTip.create(adLibraryService.updateMaster(entity));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("图库图片删除")
    public Tip deleteAdLibrary(@PathVariable Long id) {
        return SuccessTip.create(adLibraryService.deleteMaster(id));
    }


    @PostMapping("/bulk/delete")
    @ApiOperation("图库图片批量删除")
    public Tip deleteList(@RequestBody Ids ids) {
        return SuccessTip.create(adLibraryService.bulkDelete(ids));
    }

}
