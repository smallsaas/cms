package com.jfeat.module.rss.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.rss.services.gen.persistence.model.Rss;
import com.jfeat.module.rss.services.gen.persistence.dao.RssMapper;
import com.jfeat.module.rss.services.gen.persistence.dao.RssItemMapper;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
    
    import com.jfeat.module.rss.services.gen.crud.service.CRUDRssOverModelService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.module.rss.services.gen.crud.model.RssModel;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDRssOverModelService
 * @author Code generator
 * @since 2022-09-26
 */

@Service
public class CRUDRssOverModelServiceImpl  extends CRUDServiceOverModelImpl<Rss,RssModel> implements CRUDRssOverModelService {









    @Resource
    protected RssMapper rssMapper;

    
    @Override
    protected BaseMapper<Rss> getMasterMapper() {
        return rssMapper;
    }

    @Override
    protected Class<Rss> masterClassName() {
        return Rss.class;
    }

    @Override
    protected Class<RssModel> modelClassName() {
        return RssModel.class;
    }



    
    @Resource
    private RssItemMapper rssItemMapper;

                        private final static String rssItemFieldName = "pid";
    
        private final static String rssItemKeyName = "items";
    
                        
    

    
    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                                             rssItemKeyName
                                             };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {

        
                                                
            if (field.compareTo(rssItemKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(rssItemFieldName);
            _field.setItemClassName(RssItem.class);
            _field.setItemMapper(rssItemMapper);
            
            return _field;
        }


            
        throw new BusinessException(BusinessCode.BadRequest);
    }


    @Override
    protected String[] childFieldNames() {
        return new String[]{
                                };
    }

    @Override
    protected FIELD onChildFieldItem(String field) {
                
        throw new BusinessException(BusinessCode.BadRequest);
    }




}


