package com.jfeat.am.module.evaluation.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
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
@TableName("t_stock_evaluation_star")
public class StockEvaluationStar extends Model<StockEvaluationStar> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 外键
     */
	@TableField("evaluate_id")
	private Long evaluateId;
    /**
     * 星级名称
     */
	@TableField("star_name")
	private String starName;
    /**
     * 星级值
     */
	@TableField("star_value")
	private Integer starValue;


	public Long getId() {
		return id;
	}

	public StockEvaluationStar setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getEvaluateId() {
		return evaluateId;
	}

	public StockEvaluationStar setEvaluateId(Long evaluateId) {
		this.evaluateId = evaluateId;
		return this;
	}

	public String getStarName() {
		return starName;
	}

	public StockEvaluationStar setStarName(String starName) {
		this.starName = starName;
		return this;
	}

	public Integer getStarValue() {
		return starValue;
	}

	public StockEvaluationStar setStarValue(Integer starValue) {
		this.starValue = starValue;
		return this;
	}

	public static final String ID = "id";

	public static final String EVALUATE_ID = "evaluate_id";

	public static final String STAR_NAME = "star_name";

	public static final String STAR_VALUE = "star_value";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "StockEvaluationStar{" +
			"id=" + id +
			", evaluateId=" + evaluateId +
			", starName=" + starName +
			", starValue=" + starValue +
			"}";
	}
}
