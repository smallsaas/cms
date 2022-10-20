package com.jfeat.module.rss.services.domain.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.module.rss.services.domain.dao.QueryRssCssNamedPropsDao;
import com.jfeat.module.rss.services.domain.model.RssCssNamedPropsRecord;
import com.jfeat.module.rss.services.domain.service.RssCssNamedPropsService;
import com.jfeat.module.rss.services.domain.service.RssCssPropOptionService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssCssNamedPropsServiceImpl;
import com.jfeat.module.rss.services.gen.persistence.dao.RssCssPropOptionMapper;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssNamedProps;
import com.jfeat.module.rss.services.gen.persistence.model.RssItem;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

@Service("rssCssNamedPropsService")
public class RssCssNamedPropsServiceImpl extends CRUDRssCssNamedPropsServiceImpl implements RssCssNamedPropsService {


    @Resource
    QueryRssCssNamedPropsDao queryRssCssNamedPropsDao;

    @Resource
    RssCssPropOptionService rssCssPropOptionService;


    @Override
    protected String entityName() {
        return "RssCssNamedProps";
    }


    @Override
    public Map<String, JSONObject> getAllCssJson() {

        Map<String, JSONObject> map = new HashMap<>();
        RssCssNamedPropsRecord record = new RssCssNamedPropsRecord();
        List<RssCssNamedProps> rssCssNamedPropsList = queryRssCssNamedPropsDao.findRssCssNamedPropsWithItems(null, record, null, null, null, null, null);

        if (rssCssNamedPropsList != null && rssCssNamedPropsList.size() > 0) {

            for (RssCssNamedProps cssNamedPropsRecord : rssCssNamedPropsList) {
                if (cssNamedPropsRecord.getName() != null && !cssNamedPropsRecord.getName().equals("") && cssNamedPropsRecord.getRssCssPropOptionList() != null && cssNamedPropsRecord.getRssCssPropOptionList().size() > 0) {

                    JSONObject json = rssCssPropOptionService.rssCssPropOptionToJson(cssNamedPropsRecord.getRssCssPropOptionList());
                    map.put(cssNamedPropsRecord.getName(), json);
                }
            }
        }
        return map;
    }

    @Override
    @Transactional
    public Integer deleteRssNameProp(Long id) {

        Integer affected = 0;
        affected+=deleteRssNameProp(id);
        affected = rssCssPropOptionService.deleteRssCssPropOptionByCssNameId(id);
        return affected;
    }

    @Override
    @Transactional
    public Integer updateRssNameProp(RssCssNamedProps rssCssNamedProps) {
        Integer affected = 0;
        affected = rssCssPropOptionService.deleteRssCssPropOptionByCssNameId(rssCssNamedProps.getId());

        affected+=rssCssNamedPropsMapper.updateById(rssCssNamedProps);

        if (rssCssNamedProps.getRssCssPropOptionList()!=null && rssCssNamedProps.getRssCssPropOptionList().size()>0){
            affected+=rssCssPropOptionService.batchRssCssPropOption(rssCssNamedProps.getRssCssPropOptionList());
        }

        return affected;
    }

    @Override
    @Transactional
    public Integer createRssNameProp(RssCssNamedProps rssCssNamedProps) {
        Integer affected = 0;
        try {
            affected = createMaster(rssCssNamedProps);
        } catch (DuplicateKeyException e) {
            throw new BusinessException(BusinessCode.DuplicateKey);
        }
        if (affected>0 && rssCssNamedProps.getRssCssPropOptionList()!=null && rssCssNamedProps.getRssCssPropOptionList().size()>0){
            affected+=rssCssPropOptionService.batchRssCssPropOption(rssCssNamedProps.getRssCssPropOptionList());
        }
        return affected;
    }
}
