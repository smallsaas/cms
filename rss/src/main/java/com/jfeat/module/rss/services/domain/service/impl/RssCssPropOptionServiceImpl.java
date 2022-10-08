package com.jfeat.module.rss.services.domain.service.impl;
import com.jfeat.module.rss.services.domain.service.RssCssPropOptionService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssCssPropOptionServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssCssPropOptionService")
public class RssCssPropOptionServiceImpl extends CRUDRssCssPropOptionServiceImpl implements RssCssPropOptionService {

    @Override
    protected String entityName() {
        return "RssCssPropOption";
    }


                            }
