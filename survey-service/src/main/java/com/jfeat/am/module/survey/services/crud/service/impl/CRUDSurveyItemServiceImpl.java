package com.jfeat.am.module.survey.services.crud.service.impl;

import com.baomidou.mybatisplus.mapper.BaseMapper;


import com.jfeat.am.module.survey.services.crud.service.CRUDSurveyItemService;
import com.jfeat.am.module.survey.services.define.SurveyItemType;
import com.jfeat.am.module.survey.services.model.SurveyItemModel;
import com.jfeat.am.module.survey.services.persistence.dao.SurveyItemMapper;
import com.jfeat.am.module.survey.services.persistence.dao.SurveyItemOptionMapper;
import com.jfeat.am.module.survey.services.persistence.model.SurveyItem;
import com.jfeat.am.module.survey.services.persistence.model.SurveyItemOption;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.crud.plus.impl.CRUDServiceOnlyImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * implementation
 * </p>
 * CRUDSurveyItemService
 *
 * @author Code Generator
 * @since 2018-10-15
 */

@Service
public class CRUDSurveyItemServiceImpl extends CRUDServiceOnlyImpl<SurveyItem> implements CRUDSurveyItemService {


    @Resource
    protected SurveyItemMapper surveyItemMapper;

    @Resource
    SurveyItemOptionMapper optionMapper;

    @Override
    protected BaseMapper<SurveyItem> getMasterMapper() {
        return surveyItemMapper;
    }


    @Transactional
    public Integer addOneItemIntoSurvey(Long surVeyId, SurveyItemModel item) {

        int affected = 0;
        if(item.getType() == null) {
            throw new BusinessException(BusinessCode.BadRequest.getCode(), "题目类型不能为空");
        }
        if (item.getType().compareTo(SurveyItemType.RADIO.toString()) == 0
                || item.getType().compareTo(SurveyItemType.MULTI.toString()) == 0) {
            //单选或者多选，必须有两个或以上的选项
            if (item.getItemOptions() == null || item.getItemOptions().size() < 2) {
                throw new BusinessException(2000, "Data Error:" + "题目编号" + "\"" + item.getQuestionNum() + "\"" + "为选择题，必须有两个或以上的选项!");
            } else {
                item.setSurveyId(surVeyId);
                affected += surveyItemMapper.insert(item);
                for (SurveyItemOption option : item.getItemOptions()) {

                    option.setItemId(item.getId());
                    affected += optionMapper.insert(option);
                }
            }

        } else {
            if (item.getItemOptions() != null && item.getItemOptions().size() > 0) {
                throw new BusinessException(2000, "Data Error:" + "\"" + "题目编号" + "\"" + item.getQuestionNum() + "\"" + "为填空题,无须添加选项");
            }
            item.setSurveyId(surVeyId);
            affected += surveyItemMapper.insert(item);
        }
        return affected;
    }


    public Integer deleteItem(Long itemId){

        return surveyItemMapper.deleteById(itemId);
    }


    public Integer updateItem(Long itemId,SurveyItem item){

        item.setId(itemId);
        return surveyItemMapper.updateById(item);
    }


}


