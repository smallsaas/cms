package com.jfeat.module.rss.services.domain.service.impl;
import com.jfeat.module.rss.services.domain.service.RssImagePropService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssImagePropServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssImagePropService")
public class RssImagePropServiceImpl extends CRUDRssImagePropServiceImpl implements RssImagePropService {

    @Override
    protected String entityName() {
        return "RssImageProp";
    }


                            }
