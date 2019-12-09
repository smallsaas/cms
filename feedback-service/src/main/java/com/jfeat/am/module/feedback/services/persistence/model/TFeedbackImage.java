package com.jfeat.am.module.feedback.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */
@TableName("t_feedback_image")
public class TFeedbackImage extends Model<TFeedbackImage> {

    private static final long serialVersionUID = 1L;

	private Long id;
	@TableField("feedback_id")
	private Long feedbackId;
	private String url;


	public Long getId() {
		return id;
	}

	public TFeedbackImage setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getFeedbackId() {
		return feedbackId;
	}

	public TFeedbackImage setFeedbackId(Long feedbackId) {
		this.feedbackId = feedbackId;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public TFeedbackImage setUrl(String url) {
		this.url = url;
		return this;
	}

	public static final String ID = "id";

	public static final String FEEDBACK_ID = "feedback_id";

	public static final String URL = "url";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TFeedbackImage{" +
			"id=" + id +
			", feedbackId=" + feedbackId +
			", url=" + url +
			"}";
	}
}
