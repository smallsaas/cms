package com.jfeat.module.album.services.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.CRUDObject;
import com.jfeat.crud.plus.DefaultFilterResult;
import com.jfeat.module.album.services.domain.dao.QueryRssImageNameDao;
import com.jfeat.module.album.services.domain.model.RssImageNameRecord;
import com.jfeat.module.album.services.domain.service.RssImageNameOverModelService;
import com.jfeat.module.album.services.gen.crud.model.RssImageNameModel;
import com.jfeat.module.album.services.gen.crud.service.impl.CRUDRssImageNameOverModelServiceImpl;
import com.jfeat.module.album.services.gen.persistence.model.RssImageProp;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("rssImageNameService")
public class RssImageNameOverModelServiceImpl extends CRUDRssImageNameOverModelServiceImpl implements RssImageNameOverModelService {

    @Resource
    QueryRssImageNameDao queryRssImageNameDao;

    @Override
    protected String entityName() {
        return "RssImageName";
    }


    @Override
    public Integer createRssImageNameItem(RssImageNameModel entity) {
        Integer affected = 0;

        List<RssImageProp> rssImageProps = new ArrayList<>();
        if (entity.getImages() != null && !entity.getImages().equals("")) {
            JSONArray jsonArray = JSON.parseArray(entity.getImages());
            for (int i=0;i<jsonArray.size();i++){
                RssImageProp rssImageProp = new RssImageProp();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                rssImageProp.setImagePath(jsonObject.getString("url"));
                rssImageProps.add(rssImageProp);
            }
        }

        entity.setItems(rssImageProps);

        try {
            DefaultFilterResult filterResult = new DefaultFilterResult();
            affected = createMaster(entity, filterResult, null, null);
            return affected;
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }

    }

    @Override
    public RssImageNameModel getRssImageNameModelById(Long id) {
        CRUDObject<RssImageNameModel> entity = registerQueryMasterDao(queryRssImageNameDao)
                .retrieveMaster(id, null, null, null);
        if (entity != null) {
            JSONObject jsonObject = entity.toJSONObject();
            RssImageNameModel rssImageNameModel = jsonObject.toJavaObject(RssImageNameModel.class);
            if (rssImageNameModel.getItems()!=null && rssImageNameModel.getItems().size()>0){
                JSONArray jsonArray = new JSONArray();
                for (RssImageProp rssImageProp:rssImageNameModel.getItems()){
                    JSONObject url = new JSONObject();
                    url.put("url",rssImageProp.getImagePath());
                    jsonArray.add(url);
                }

                if (jsonArray!=null && jsonArray.size()>0){
                    rssImageNameModel.setImages(jsonArray.toJSONString());
                }
            }
            return rssImageNameModel;
        }

        return null;

    }

    @Override
    public Map<String, String> getAllRssImageToMap() {
        RssImageNameRecord rssImageNameRecord = new RssImageNameRecord();
        List<RssImageNameRecord> rssImageNameRecordList =  queryRssImageNameDao.findRssImageNamePageWithItem(null,rssImageNameRecord,null,null,null,null,null);
        Map<String,String> map = new HashMap<>();
        if (rssImageNameRecordList!=null && rssImageNameRecordList.size()>0){

            for (RssImageNameRecord record:rssImageNameRecordList){
                if (record.getName()!=null && !record.getName().equals("")){
                    if (record.getRssImagePropList()!=null && record.getRssImagePropList().size()>0){

                        JSONArray jsonArray = new JSONArray();
                        for (RssImageProp rssImageProp:record.getRssImagePropList()){
                            JSONObject jsonObject = new JSONObject();
                            if (rssImageProp.getImagePath()!=null){
                                jsonObject.put("url",rssImageProp.getImagePath());
                                jsonArray.add(jsonObject);
                            }

                        }

                        map.put(record.getName(),jsonArray.toJSONString());


                    }else {
                        map.put(record.getName(),"");
                    }
                }

            }

        }
        return map;
    }

    @Override
    public Map<String, List<JSONObject>> getAllRssImageToList() {
        RssImageNameRecord rssImageNameRecord = new RssImageNameRecord();
        List<RssImageNameRecord> rssImageNameRecordList =  queryRssImageNameDao.findRssImageNamePageWithItem(null,rssImageNameRecord,null,null,null,null,null);
        Map<String,List<JSONObject>> map = new HashMap<>();
        if (rssImageNameRecordList!=null && rssImageNameRecordList.size()>0){

            for (RssImageNameRecord record:rssImageNameRecordList){
                if (record.getName()!=null && !record.getName().equals("")){
                    if (record.getRssImagePropList()!=null && record.getRssImagePropList().size()>0){

                        List<JSONObject> jsonObjects = new ArrayList<>();
                        for (RssImageProp rssImageProp:record.getRssImagePropList()){
                            JSONObject jsonObject = new JSONObject();
                            if (rssImageProp.getImagePath()!=null){
                                jsonObject.put("url",rssImageProp.getImagePath());
                                jsonObjects.add(jsonObject);
                            }

                        }

                        map.put(record.getName(),jsonObjects);


                    }else {
                        map.put(record.getName(),null);
                    }
                }

            }

        }
        return map;
    }
}
