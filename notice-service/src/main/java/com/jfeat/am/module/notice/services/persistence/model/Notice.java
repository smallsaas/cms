package com.jfeat.am.module.notice.services.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.*;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-20
 */
@TableName("t_notice")
public class Notice extends Model<Notice> {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
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
     * 内容
     */
	private String content;
    /**
     * 类型
     */
	private String type;
    /**
     * 是否启用 0-否 1-是 2-过期
     */
	private Integer enabled;
    /**
     * 创建时间
     */
	@TableField("create_time")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date createTime;
    /**
     * 更新时间
     */
	@TableField("update_time")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date updateTime;
    /**
     * 到期时间
     */
	@TableField("end_time")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	private Date endTime;
	/**
	 * 排序号
	 */
	@TableField("sort_num")
	private Integer sortNum;
	/**
	 * 状态
	 */
	private String status;


	private Long orgId;

	private String contentPath;

	private String pictureUrl;

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}


	public Long getId() {
		return id;
	}

	public Notice setId(Long id) {
		this.id = id;
		return this;
	}

	public String getAuthor() {
		return author;
	}

	public Notice setAuthor(String author) {
		this.author = author;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Notice setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Notice setContent(String content) {
		this.content = content;
		return this;
	}

	public String getType() {
		return type;
	}

	public Notice setType(String type) {
		this.type = type;
		return this;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public Notice setEnabled(Integer enabled) {
		this.enabled = enabled;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Notice setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Notice setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		return this;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Notice setEndTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}

	public String getContentPath() {
		return contentPath;
	}

	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public static final String CONTENT = "content";

	public static final String TYPE = "type";

	public static final String ENABLED = "enabled";

	public static final String CREATE_TIME = "create_time";

	public static final String UPDATE_TIME = "update_time";

	public static final String END_TIME = "end_time";

	public static final String STATUS = "status";

	public static final String SORT_NUM = "sort_num";

	public static final String ORF_ID = "org_id";

	public static final String CONTENT_PATH ="content_Path";

	public static final String PICTURE_URL="picture_url";


	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Notice{" +
				"id=" + id +
				", author='" + author + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", type='" + type + '\'' +
				", enabled=" + enabled +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", endTime=" + endTime +
				", sortNum=" + sortNum +
				", status='" + status + '\'' +
				", orgId=" + orgId +
				'}';
	}
}
