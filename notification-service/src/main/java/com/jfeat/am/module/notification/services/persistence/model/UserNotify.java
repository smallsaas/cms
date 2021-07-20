package com.jfeat.am.module.notification.services.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("user_notify")
public class UserNotify extends Model<UserNotify> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	private Long id;
    /**
     * 用户ID
     */
	@TableField("user_id")
	private Long userId;
    /**
     * 提醒ID
     */
	@TableField("notify_id")
	private Long notifyId;
    /**
     * 是否阅读:0-未读,1-已读
     */
	@TableField("is_read")
	private Integer isRead;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public UserNotify setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getUserId() {
		return userId;
	}

	public UserNotify setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public Long getNotifyId() {
		return notifyId;
	}

	public UserNotify setNotifyId(Long notifyId) {
		this.notifyId = notifyId;
		return this;
	}

	public Integer getIsRead() {
		return isRead;
	}

	public UserNotify setIsRead(Integer isRead) {
		this.isRead = isRead;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public UserNotify setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public static final String ID = "id";

	public static final String USER_ID = "user_id";

	public static final String NOTIFY_ID = "notify_id";

	public static final String IS_READ = "is_read";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "UserNotify{" +
			"id=" + id +
			", userId=" + userId +
			", notifyId=" + notifyId +
			", isRead=" + isRead +
			", createTime=" + createTime +
			"}";
	}
}
