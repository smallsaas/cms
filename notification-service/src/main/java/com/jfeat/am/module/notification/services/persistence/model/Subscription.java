package com.jfeat.am.module.notification.services.persistence.model;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
public class Subscription extends Model<Subscription> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
	@TableField("user_id")
	private Long userId;
	private Long targetId;
	@TableField("target_type")
	private String targetType;
	private String action;
	@TableField("created_at")
	private Date createdAt;


	public Long getId() {
		return id;
	}

	public Subscription setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getTargetType() {
		return targetType;
	}

	public Subscription setTargetType(String targetType) {
		this.targetType = targetType;
		return this;
	}

	public String getAction() {
		return action;
	}

	public Subscription setAction(String action) {
		this.action = action;
		return this;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public Subscription setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String TARGET_ID = "target_id";

	public static final String TARGET_TYPE = "target_type";

	public static final String ACTION = "action";

	public static final String CREATED_AT = "created_at";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Subscription{" +
			"id=" + id +
			", userId=" + userId +
			", targetId=" + targetId +
			", targetType=" + targetType +
			", action=" + action +
			", createdAt=" + createdAt +
			"}";
	}
}
