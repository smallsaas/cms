package com.jfeat.am.module.survey.services.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-10-15
 */
@TableName("t_survey_answer")
public class SurveyAnswer extends Model<SurveyAnswer> {

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
     * 作者ID
     */
	@TableField("author_id")
	private Long authorId;
    /**
     * 作者信息
     */
	@TableField("author_message")
	private String authorMessage;
    /**
     * 内容：题号+答案(json保存问题及答案)
     */
	private String content;


	public Long getId() {
		return id;
	}

	public SurveyAnswer setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getSurveyId() {
		return surveyId;
	}

	public SurveyAnswer setSurveyId(Long surveyId) {
		this.surveyId = surveyId;
		return this;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public SurveyAnswer setAuthorId(Long authorId) {
		this.authorId = authorId;
		return this;
	}

	public String getAuthorMessage() {
		return authorMessage;
	}

	public SurveyAnswer setAuthorMessage(String authorMessage) {
		this.authorMessage = authorMessage;
		return this;
	}

	public String getContent() {
		return content;
	}

	public SurveyAnswer setContent(String content) {
		this.content = content;
		return this;
	}

	public static final String ID = "id";

	public static final String SURVEY_ID = "survey_id";

	public static final String AUTHOR_ID = "author_id";

	public static final String AUTHOR_MESSAGE = "author_message";

	public static final String CONTENT = "content";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "SurveyAnswer{" +
			"id=" + id +
			", surveyId=" + surveyId +
			", authorId=" + authorId +
			", authorMessage=" + authorMessage +
			", content=" + content +
			"}";
	}
}
