package com.jfeat.am.module.survey.services.persistence.model;

import java.io.Serializable;

import java.util.Date;
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
@TableName("t_survey")
public class Survey extends Model<Survey> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;
    /**
     * 作者
     */
	private String author;
    /**
     * 标题
     */
	private String title;
    /**
     * 类型
     */
	private String type;
    /**
     * 状态
     */
	private String status;
    /**
     * 是否启用 0-否 1-是
     */
	private Integer enabled;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	private Date updateTime;

	/**
	 * 是否启用 0-否 1-是
	 */
	@TableField("sort_num")
	private Integer sortNum;


	public Long getId() {
		return id;
	}

	public Survey setId(Long id) {
		this.id = id;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public Survey setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Survey setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getType() {
		return type;
	}

	public Survey setType(String type) {
		this.type = type;
		return this;
	}

	public String getStatus() {
		return status;
	}

	public Survey setStatus(String status) {
		this.status = status;
		return this;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public Survey setEnabled(Integer enabled) {
		this.enabled = enabled;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Survey setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Survey setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}


	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public static final String ID = "id";

	public static final String AUTHOR = "author";

	public static final String TITLE = "title";

	public static final String TYPE = "type";

	public static final String STATUS = "status";

	public static final String ENABLED = "enabled";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Survey{" +
			"id=" + id +
			", author=" + author +
			", title=" + title +
			", type=" + type +
			", status=" + status +
			", enabled=" + enabled +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
