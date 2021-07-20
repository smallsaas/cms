package com.jfeat.am.module.survey.services.crud.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jfeat.am.module.survey.services.define.SurveyItemType;
import com.jfeat.am.module.survey.services.domain.dao.QuerySurveyDao;
import com.jfeat.am.module.survey.services.model.SurveyItemModel;
import com.jfeat.am.module.survey.services.model.SurveyModel;
import com.jfeat.am.module.survey.services.persistence.dao.SurveyItemMapper;
import com.jfeat.am.module.survey.services.persistence.dao.SurveyItemOptionMapper;
import com.jfeat.am.module.survey.services.persistence.model.Survey;
import com.jfeat.am.module.survey.services.persistence.dao.SurveyMapper;


import com.jfeat.am.module.survey.services.crud.service.CRUDSurveyService;
import com.jfeat.am.module.survey.services.persistence.model.SurveyItem;
import com.jfeat.am.module.survey.services.persistence.model.SurveyItemOption;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * implementation
 * </p>
 * CRUDSurveyService
 *
 * @author Code Generator
 * @since 2018-10-15
 */

@Service
public class CRUDSurveyServiceImpl extends CRUDServiceOnlyImpl<Survey> implements CRUDSurveyService {


    @Resource
    protected SurveyMapper surveyMapper;

    @Resource
    SurveyItemMapper itemMapper;

    @Resource
    SurveyItemOptionMapper optionMapper;

    @Resource
    QuerySurveyDao querySurveyDao;

    @Override
    protected BaseMapper<Survey> getMasterMapper() {
        return surveyMapper;
    }


    @Transactional
    public Integer addItemIntoSurvey(Long surveyId, List<SurveyItemModel> items) {

        int affected = 0;
        for (SurveyItemModel item : items) {
            if (item.getType().compareTo(SurveyItemType.RADIO.toString()) == 0
                    || item.getType().compareTo(SurveyItemType.MULTI.toString()) == 0) {
                //单选或者多选，必须有两个或以上的选项
                if (item.getItemOptions() == null || item.getItemOptions().size() < 2) {
                    throw new BusinessException(2000, "Data Error:" + "题目编号" + "\"" + item.getQuestionNum() + "\"" + "为选择题，必须有两个或以上的选项!");
                } else {
                    item.setSurveyId(surveyId);
                    affected += itemMapper.insert(item);
                    for (SurveyItemOption option : item.getItemOptions()) {

                        if ((option.getContent() == null || option.getContent().length() <= 0)
                                && (option.getImageUrl() == null || option.getImageUrl().length() <= 0)) {

                        } else {
                            option.setItemId(item.getId());
                            affected += optionMapper.insert(option);
                        }
                    }
                }
            } else {
                if (item.getItemOptions() != null && item.getItemOptions().size() > 0) {
                    throw new BusinessException(2000, "Data Error:" + "\"" + "题目编号" + "\"" + item.getQuestionNum() + "\"" + "为填空题,无须添加选项");
                }
                item.setSurveyId(surveyId);
                affected += itemMapper.insert(item);
            }
        }
        return affected;
    }

    @Transactional
    public void deleteItemAndOptions(Long surveyId) {

        List<SurveyItem> items = itemMapper.selectList(
                new QueryWrapper<SurveyItem>()
                        .eq(SurveyItem.SURVEY_ID, surveyId));
        if (items == null || items.size() == 0) {

        } else {
            for (SurveyItem item : items) {
                optionMapper.delete(
                        new QueryWrapper<SurveyItemOption>()
                                .eq(SurveyItemOption.ITEM_ID, item.getId()));
                itemMapper.deleteById(item.getId());
            }
        }
    }

    @Transactional
    public SurveyModel createSurvey(SurveyModel model) {
        surveyMapper.insert(model);
        return model;

/*        if (model.getSurveyItems() == null || model.getSurveyItems().size() == 0) {

            affected += surveyMapper.insert(model);
            return model;
        } else {
            affected += surveyMapper.insert(model);
            affected += addItemIntoSurvey(model.getId(), model.getSurveyItems());
        }
        return model;*/
    }

    public SurveyModel showSurvey(Long id, boolean result,String type) {

        // 通过 result 为 true 还是 false 去进行不同的索引，true item.enabled=1 else item.enabled=0
        Survey survey = surveyMapper.selectById(id);
        JSONObject surveyObject = JSON.parseObject(JSON.toJSONString(survey));

        List<SurveyItemModel> models = new ArrayList<SurveyItemModel>();
        if (result == true) {

            List<SurveyItem> items = itemMapper
                    .selectList(new QueryWrapper<SurveyItem>()
                            .eq(SurveyItem.SURVEY_ID, id)
                            //.orderBy("sort_num",false)
                            );
            if (items == null || items.size() == 0) {
                surveyObject.put("surveyItems", models);
                SurveyModel model = JSON.parseObject(JSON.toJSONString(surveyObject), SurveyModel.class);
                return model;
            }
            for (SurveyItem item : items) {
                JSONObject itemObject = JSON.parseObject(JSON.toJSONString(item));
                List<SurveyItemOption> options = optionMapper
                                .selectList(new QueryWrapper<SurveyItemOption>()
                                .eq(SurveyItemOption.ITEM_ID, item.getId()));

                itemObject.put("itemOptions", options);
                SurveyItemModel model = JSON.parseObject(JSON.toJSONString(itemObject), SurveyItemModel.class);
                models.add(model);
            }

            surveyObject.put("surveyItems", models);

            SurveyModel model = JSON.parseObject(JSON.toJSONString(surveyObject), SurveyModel.class);
            return model;
        } else {
            List<SurveyItem> items = itemMapper.selectList(
                    new QueryWrapper<SurveyItem>()
                            .eq(SurveyItem.SURVEY_ID, id)
                            //.orderBy("sort_num",false)
                        );
            if (items == null || items.size() == 0) {
                surveyObject.put("surveyItems", models);
                SurveyModel model = JSON.parseObject(JSON.toJSONString(surveyObject), SurveyModel.class);
                return model;
            }
            for (SurveyItem item : items) {
                // if needs  enhance , using sql search instead of this way.
                JSONObject itemObject = JSON.parseObject(JSON.toJSONString(item));
                List<SurveyItemOption> options = optionMapper.selectList(new QueryWrapper<SurveyItemOption>().eq(SurveyItemOption.ITEM_ID, item.getId()));

                itemObject.put("itemOptions", options);
                SurveyItemModel model = JSON.parseObject(JSON.toJSONString(itemObject), SurveyItemModel.class);
                models.add(model);
            }

            surveyObject.put("surveyItems", models);

            SurveyModel model = JSON.parseObject(JSON.toJSONString(surveyObject), SurveyModel.class);
            return model;
        }
    }


    @Transactional
    public Integer deleteSurvey(Long id) {

        int affected = 0;
        deleteItemAndOptions(id);
        return surveyMapper.deleteById(id);
    }


    @Transactional
    public Integer updateSurvey(Long id, SurveyModel model) {

        int affected = 0;
        if (model.getSurveyItems() == null || model.getSurveyItems().size() == 0) {
            model.setId(id);
            deleteItemAndOptions(id);
            return surveyMapper.updateById(model);
        } else {
            model.setId(id);
            surveyMapper.updateById(model);
            deleteItemAndOptions(id);
            affected += addItemIntoSurvey(id, model.getSurveyItems());
            return affected;
        }

    }


    /**
     * 设定某个问卷为 C、IPAD 端默认问卷
     */
    @Transactional
    public Integer setDefaultSurvey(Long surveyId) {
        Survey survey = surveyMapper.selectById(surveyId);
        if (survey==null){
            throw new BusinessException(5200,"无该问卷记录，请核准并重新提交");
        }
        querySurveyDao.updateEnabled(survey.getType());
        survey.setEnabled(1);
        survey.setId(surveyId);
        return surveyMapper.updateById(survey);
    }

    /**
     * default survey
     */
    public SurveyModel showDefaultSurveyForCustomer(String type) {
        Survey survey = new Survey();
        survey.setEnabled(1);
        survey.setType(type);
        Survey origin = surveyMapper.selectOne(new LambdaQueryWrapper<>(survey));
        if (origin == null) {
            return null;
        }
        return showSurvey(origin.getId(), true,type);

    }

    /**
     * 批量删除问卷
     */
    public Integer bulkDel(List<Long> ids) {
        int affected = 0;
        if (ids == null || ids.size() == 0) {
            return null;
        }

        for (Long id : ids) {
            affected += deleteSurvey(id);
        }
        return affected;
    }

}


