package com.jfeat.am.module.notification.services.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-04-14
 */
public class Notify extends Model<Notify> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 发送者ID
     */
	@TableField("sender_id")
	private Long senderId;
	/**
	 * 头像
	 */
	private String avatar;
	/**
	 * 姓名
	 */
	private String name;
    /**
     * 内容提醒
     */
	private String content;
    /**
     * 类型
     */
	private String type;
    /**
     * 目标ID
     */
	@TableField("target_id")
	private Long targetId;
    /**
     * 目标类型:topic essay
     */
	@TableField("target_type")
	private String targetType;
    /**
     * 操作:like comment
     */
	private String action;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;

	/**
	 * 最上层评论的对象type
	 */
	@TableField("origin_type")
	private String originType;

	/**
	 * 最上层评论的对象id
	 */
	@TableField("origin_id")
	private Long originId;

	/**
	 * 来源对象id
	 */
	@TableField("source_id")
	private Long sourceId;
	/**
	 * 来源对象type
	 */
	@TableField("source_type")
	private String sourceType;

	public Long getSourceId() {
		return sourceId;
	}

	public Notify setSourceId(Long sourceId) {
		this.sourceId = sourceId;
		return this;
	}

	public String getSourceType() {
		return sourceType;
	}

	public Notify setSourceType(String sourceType) {
		this.sourceType = sourceType;
		return this;
	}

	public Long getId() {
		return id;
	}

	public Notify setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getSenderId() {
		return senderId;
	}

	public Notify setSenderId(Long senderId) {
		this.senderId = senderId;
		return this;
	}

	public String getOriginType() {
		return originType;
	}

	public Notify setOriginType(String originType) {
		this.originType = originType;
		return this;
	}

	public Long getOriginId() {
		return originId;
	}

	public Notify setOriginId(Long originId) {
		this.originId = originId;
		return this;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public Notify setContent(String content) {
		this.content = content;
		return this;
	}

	public String getType() {
		return type;
	}

	public Notify setType(String type) {
		this.type = type;
		return this;
	}

	public Long getTargetId() {
		return targetId;
	}

	public Notify setTargetId(Long targetId) {
		this.targetId = targetId;
		return this;
	}

	public String getTargetType() {
		return targetType;
	}

	public Notify setTargetType(String targetType) {
		this.targetType = targetType;
		return this;
	}

	public String getAction() {
		return action;
	}

	public Notify setAction(String action) {
		this.action = action;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Notify setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public static final String ID = "id";

	public static final String SENDER_ID = "sender_id";

	public static final String AVATAR = "avatar";

	public static final String NAME = "name";

	public static final String CONTENT = "content";

	public static final String TYPE = "type";

	public static final String TARGET_ID = "target_id";

	public static final String TARGET_TYPE = "target_type";

	public static final String ACTION = "action";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Notify{" +
			"id=" + id +
			", senderId=" + senderId +
			", content=" + content +
			", type=" + type +
			", targetId=" + targetId +
			", targetType=" + targetType +
			", action=" + action +
			", createTime=" + createTime +
			", avatar=" + avatar +
			", name=" + name +
			"}";
	}
}
