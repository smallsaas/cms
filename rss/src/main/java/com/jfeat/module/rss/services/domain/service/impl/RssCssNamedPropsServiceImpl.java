package com.jfeat.module.rss.services.domain.service.impl;
import com.jfeat.module.rss.services.domain.service.RssCssNamedPropsService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssCssNamedPropsServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssCssNamedPropsService")
public class RssCssNamedPropsServiceImpl extends CRUDRssCssNamedPropsServiceImpl implements RssCssNamedPropsService {

    @Override
    protected String entityName() {
        return "RssCssNamedProps";
    }


                            }
