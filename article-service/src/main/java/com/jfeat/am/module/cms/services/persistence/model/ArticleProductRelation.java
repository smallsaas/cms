package com.jfeat.am.module.cms.services.persistence.model;

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
 * @since 2018-09-06
 */
@TableName("article_product_relation")
public class ArticleProductRelation extends Model<ArticleProductRelation> {

    private static final long serialVersionUID = 1L;

    /**
     * 资讯ID
     */
    @TableId("article_id")
	private Long articleId;
    /**
     * 产品ID
     */
	@TableField("target_id")
	private Long targetId;
    /**
     * 类型
     */
	@TableField("target_type")
	private String targetType;


	public Long getArticleId() {
		return articleId;
	}

	public ArticleProductRelation setArticleId(Long articleId) {
		this.articleId = articleId;
		return this;
	}

	public Long getTargetId() {
		return targetId;
	}

	public ArticleProductRelation setTargetId(Long targetId) {
		this.targetId = targetId;
		return this;
	}

	public String getTargetType() {
		return targetType;
	}

	public ArticleProductRelation setTargetType(String targetType) {
		this.targetType = targetType;
		return this;
	}

	public static final String ARTICLE_ID = "article_id";

	public static final String TARGET_ID = "target_id";

	public static final String TARGET_TYPE = "target_type";

	@Override
	protected Serializable pkVal() {
		return this.articleId;
	}

	@Override
	public String toString() {
		return "ArticleProductRelation{" +
			"articleId=" + articleId +
			", targetId=" + targetId +
			", targetType=" + targetType +
			"}";
	}
}
