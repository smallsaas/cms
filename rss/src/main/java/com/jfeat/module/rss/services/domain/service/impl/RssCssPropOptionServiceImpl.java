package com.jfeat.module.rss.services.domain.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.module.rss.services.domain.dao.QueryRssCssPropOptionDao;
import com.jfeat.module.rss.services.domain.service.RssCssPropOptionService;
import com.jfeat.module.rss.services.gen.crud.service.impl.CRUDRssCssPropOptionServiceImpl;
import com.jfeat.module.rss.services.gen.persistence.dao.RssCssPropOptionMapper;
import com.jfeat.module.rss.services.gen.persistence.model.RssCssPropOption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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

    @Resource
    RssCssPropOptionMapper rssCssPropOptionMapper;

    @Resource
    QueryRssCssPropOptionDao queryRssCssPropOptionDao;


    @Override
    protected String entityName() {
        return "RssCssPropOption";
    }


    @Override
    public JSONObject rssCssPropOptionToJson(List<RssCssPropOption> rssCssPropOptionList) {
        JSONObject jsonObject = new JSONObject();

        if (rssCssPropOptionList!=null && rssCssPropOptionList.size()>0){
            for (RssCssPropOption rssCssPropOption:rssCssPropOptionList){
                jsonObject.put(rssCssPropOption.getPropName(),rssCssPropOption.getPropOption());
            }
        }

        return jsonObject;
    }

    @Override
    @Transactional
    public Integer deleteRssCssPropOptionByCssNameId(Long id) {
        Integer affect = 0;
        QueryWrapper<RssCssPropOption> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(RssCssPropOption.CSS_NAME_PROPS_ID,id);
        affect+=rssCssPropOptionMapper.delete(queryWrapper);
        return affect;
    }

    @Override
    @Transactional
    public Integer batchRssCssPropOption(List<RssCssPropOption> rssCssPropOptionList) {
        Integer affect =0;
        affect+=queryRssCssPropOptionDao.batchAddRssCssPropOption(rssCssPropOptionList);
        return affect;
    }
}
