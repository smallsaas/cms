package com.jfeat.am.module.advertisement.services.service.impl;

import com.jfeat.am.module.advertisement.services.crud.service.impl.CRUDAdLibraryServiceImpl;
import com.jfeat.am.module.advertisement.services.persistence.dao.AdLibraryMapper;
import com.jfeat.am.module.advertisement.services.service.AdLibraryService;
import com.jfeat.crud.base.request.Ids;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */
@Service
public class AdLibraryServiceImpl extends CRUDAdLibraryServiceImpl implements AdLibraryService {
    @Resource
    private AdLibraryMapper adLibraryMapper;

    @Override
    public Integer bulkDelete(Ids ids) {
        Integer success = 0;
        for (Long id : ids.getIds()) {
            success += adLibraryMapper.deleteById(id);
        }
        return success;
    }
}
