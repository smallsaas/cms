package com.jfeat.module.rss.services.gen.crud.service.impl;
// ServiceImpl start

            
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.module.rss.services.gen.persistence.model.RssImageName;
import com.jfeat.module.rss.services.gen.persistence.dao.RssImageNameMapper;
import com.jfeat.module.rss.services.gen.persistence.dao.RssImagePropMapper;
import com.jfeat.module.rss.services.gen.persistence.model.RssImageProp;
    
    import com.jfeat.module.rss.services.gen.crud.service.CRUDRssImageNameOverModelService;
import org.springframework.stereotype.Service;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import javax.annotation.Resource;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import com.jfeat.module.rss.services.gen.crud.model.RssImageNameModel;

/**
 * <p>
 *  implementation
 * </p>
 *CRUDRssImageNameOverModelService
 * @author Code generator
 * @since 2022-10-21
 */

@Service
public class CRUDRssImageNameOverModelServiceImpl  extends CRUDServiceOverModelImpl<RssImageName,RssImageNameModel> implements CRUDRssImageNameOverModelService {









    @Resource
    protected RssImageNameMapper rssImageNameMapper;

    
    @Override
    protected BaseMapper<RssImageName> getMasterMapper() {
        return rssImageNameMapper;
    }

    @Override
    protected Class<RssImageName> masterClassName() {
        return RssImageName.class;
    }

    @Override
    protected Class<RssImageNameModel> modelClassName() {
        return RssImageNameModel.class;
    }



    
    @Resource
    private RssImagePropMapper rssImagePropMapper;

                        private final static String rssImagePropFieldName = "pid";
    
        private final static String rssImagePropKeyName = "items";
    
                        
    

    
    @Override
    protected String[] slaveFieldNames() {
        return new String[]{
                                             rssImagePropKeyName
                                             };
    }

    @Override
    protected FIELD onSlaveFieldItem(String field) {

        
                                                
            if (field.compareTo(rssImagePropKeyName) == 0) {
                FIELD _field = new FIELD();
            _field.setItemKeyName(field);
            _field.setItemFieldName(rssImagePropFieldName);
            _field.setItemClassName(RssImageProp.class);
            _field.setItemMapper(rssImagePropMapper);
            
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


