package com.jfeat.am.module.evaluation.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-16
 */
@TableName("t_stock_evaluation_image")
public class StockEvaluationImage extends Model<StockEvaluationImage> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 外键
     */
	@TableField("evaluate_id")
	private Long evaluateId;
    /**
     * 图片
     */
	private String url;
    /**
     * 状态
     */
	private String status;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 外键
     */
	@TableField("evaluation_addition_id")
	private Long evaluationAdditionId;


	public Long getId() {
		return id;
	}

	public StockEvaluationImage setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getEvaluateId() {
		return evaluateId;
	}

	public StockEvaluationImage setEvaluateId(Long evaluateId) {
		this.evaluateId = evaluateId;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public StockEvaluationImage setStatus(String status) {
		this.status = status;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public StockEvaluationImage setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Long getEvaluationAdditionId() {
		return evaluationAdditionId;
	}

	public StockEvaluationImage setEvaluationAdditionId(Long evaluationAdditionId) {
		this.evaluationAdditionId = evaluationAdditionId;
		return this;
	}

	public static final String ID = "id";

	public static final String EVALUATE_ID = "evaluate_id";

	public static final String URL = "url";

	public static final String STATUS = "status";

	public static final String CREATE_TIME = "create_time";

	public static final String EVALUATION_ADDITION_ID = "evaluation_addition_id";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "StockEvaluationImage{" +
			"id=" + id +
			", evaluateId=" + evaluateId +
			", url=" + url +
			", status=" + status +
			", createTime=" + createTime +
			", evaluationAdditionId=" + evaluationAdditionId +
			"}";
	}
}
