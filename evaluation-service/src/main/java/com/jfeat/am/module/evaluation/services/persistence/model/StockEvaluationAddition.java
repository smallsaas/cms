package com.jfeat.am.module.evaluation.services.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-16
 */
@TableName("t_stock_evaluation_addition")
public class StockEvaluationAddition extends Model<StockEvaluationAddition> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 外键
     */
	@TableField("evaluate_id")
	private Long evaluateId;
    /**
     * 评价信息
     */
	private String content;
    /**
     * 状态
     */
	private String status;
    /**
     * 默认显示
     */
	@TableField("is_display")
	private Integer isDisplay;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;

	@TableField("star")
	private Integer star;

	@TableField("dislike")
	private Integer dislike;

	public Integer getStar() {
		return star;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public Integer getDislike() {
		return dislike;
	}

	public void setDislike(Integer dislike) {
		this.dislike = dislike;
	}

	public Long getId() {
		return id;
	}

	public StockEvaluationAddition setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getEvaluateId() {
		return evaluateId;
	}

	public StockEvaluationAddition setEvaluateId(Long evaluateId) {
		this.evaluateId = evaluateId;
		return this;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public StockEvaluationAddition setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getIsDisplay() {
		return isDisplay;
	}

	public StockEvaluationAddition setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public StockEvaluationAddition setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public static final String ID = "id";

	public static final String EVALUATE_ID = "evaluate_id";

	public static final String CONTENT = "content";

	public static final String STATUS = "status";

	public static final String IS_DISPLAY = "is_display";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "StockEvaluationAddition{" +
			"id=" + id +
			", evaluateId=" + evaluateId +
			", content=" + content +
			", status=" + status +
			", isDisplay=" + isDisplay +
			", createTime=" + createTime +
			"}";
	}
}
