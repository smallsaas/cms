package com.jfeat.am.module.icon.services.domain.service.impl;

import com.jfeat.am.module.icon.services.domain.service.FrontIconService;
import com.jfeat.am.module.icon.services.gen.crud.service.impl.CRUDFrontIconServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("frontIconService")
public class FrontIconServiceImpl extends CRUDFrontIconServiceImpl implements FrontIconService {

    @Override
    protected String entityName() {
        return "icon";
    }

}
