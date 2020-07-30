package com.jfeat.am.module.survey.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Code Generator
 * @since 2018-10-15
 */
@TableName("t_survey_item")
public class SurveyItem extends Model<SurveyItem> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 问卷调查ID
     */
    @TableField("survey_id")
    private Long surveyId;
    /**
     * 题号
     */
    @TableField("question_num")
    private Integer questionNum;
    /**
     * 问题
     */
    private String content;
    /**
     * 类型：RADIO-单选，MULTI-多选，FILL-填空
     */
    private String type;
    /**
     * 是否必填(选)
     */
    @TableField("is_required")
    private Integer isRequired;



    /**
     * 是否启用该题目
     */
    @TableField("enabled")
    private Integer enabled;

    /**
     * 是否启用 0-否 1-是
     */
    private Integer sortNum;


    public Long getId() {
        return id;
    }

    public SurveyItem setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public SurveyItem setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
        return this;
    }

    public Integer getQuestionNum() {
        return questionNum;
    }

    public SurveyItem setQuestionNum(Integer questionNum) {
        this.questionNum = questionNum;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SurveyItem setContent(String content) {
        this.content = content;
        return this;
    }

    public String getType() {
        return type;
    }

    public SurveyItem setType(String type) {
        this.type = type;
        return this;
    }

    public Integer getIsRequired() {
        return isRequired;
    }

    public SurveyItem setIsRequired(Integer isRequired) {
        this.isRequired = isRequired;
        return this;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public static final String ID = "id";

    public static final String SURVEY_ID = "survey_id";

    public static final String QUESTION_NUM = "question_num";

    public static final String CONTENT = "content";

    public static final String TYPE = "type";

    public static final String IS_REQUIRED = "is_required";


    public static final String ENABLED = "enabled";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SurveyItem{" +
                "id=" + id +
                ", surveyId=" + surveyId +
                ", questionNum=" + questionNum +
                ", content=" + content +
                ", type=" + type +
                ", isRequired=" + isRequired +
                ", enabled=" + enabled +
                "}";
    }
}
