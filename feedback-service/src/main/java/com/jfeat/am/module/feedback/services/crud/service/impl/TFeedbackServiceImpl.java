package com.jfeat.am.module.feedback.services.crud.service.impl;
        
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jfeat.am.module.feedback.services.domain.dao.QueryTFeedbackDao;
import com.jfeat.am.module.feedback.services.domain.model.TFeedbackModel;
import com.jfeat.am.module.feedback.services.persistence.dao.TFeedbackImageMapper;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedback;
import com.jfeat.am.module.feedback.services.persistence.dao.TFeedbackMapper;
import com.jfeat.am.module.feedback.services.crud.service.TFeedbackService;
import com.jfeat.am.module.feedback.services.persistence.model.TFeedbackImage;
import com.jfeat.crud.plus.FIELD;
import com.jfeat.crud.plus.impl.CRUDServiceOverModelImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */
@Service
public class TFeedbackServiceImpl extends CRUDServiceOverModelImpl<TFeedback,TFeedbackModel> implements TFeedbackService {


    @Resource
    private TFeedbackMapper tFeedbackMapper;
    @Resource
    private TFeedbackImageMapper tFeedbackImageMapper;
    @Resource
    private QueryTFeedbackDao queryTFeedbackDao;

    private static final String ItemKeyName = "images";
    private static final String ItemFieldName = "feedback_id";



    @Override
    protected BaseMapper<TFeedback> getMasterMapper() {
        return tFeedbackMapper;
    }

    @Override
    protected String[] slaveFieldNames() {
        return new String[]{ItemKeyName};
    }

    @Override
    protected String[] childFieldNames() {
        return new String[0];
    }

    @Override
    protected FIELD onSlaveFieldItem(String s) {
        FIELD _field = new FIELD();
        _field.setItemKeyName(s);
        _field.setItemFieldName(ItemFieldName);
        _field.setItemClassName(TFeedbackImage.class);
        _field.setItemMapper(tFeedbackImageMapper);
        return _field;
    }

    @Override
    protected FIELD onChildFieldItem(String s) {
        return null;
    }

    @Override
    protected Class<TFeedback> masterClassName() {
        return TFeedback.class;
    }

    @Override
    protected Class<TFeedbackModel> modelClassName() {
        return TFeedbackModel.class;
    }

    @Override
    public List<TFeedbackModel> findFeedback(Page<TFeedbackModel> page, String name, Integer unread){

        List<TFeedback> model = tFeedbackMapper.selectList(new QueryWrapper<TFeedback>().eq("unread",unread).like("create_name",name));
        List<TFeedbackModel> models = new ArrayList<>();
        for(TFeedback feedback : model){
            TFeedbackModel feedbackModel = new TFeedbackModel();
            List<TFeedbackImage> images = tFeedbackImageMapper.selectList(new QueryWrapper<TFeedbackImage>().eq("feedback_id",feedback.getId()));
            feedbackModel.setImages(images);
            models.add(feedbackModel);
        }
        return models;
    }


    // List  include createUser name
    public List<TFeedbackModel> findTFeedbacks(Page page,Integer unread, String createName, String startTime, String endTime) {
        List<TFeedbackModel> models = queryTFeedbackDao.findTFeedbacks(page,unread,createName,startTime,endTime);
        return models;
    }
}


