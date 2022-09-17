package com.jfeat.am.module.notice.services.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

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

    @ApiModelProperty(value = "命名")
    private String name;
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 到期时间
     */
    @TableField("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
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

    private Date startTime;

    private Integer periodType;

    private Integer viewNumber;

    private String tag;

    private String reference;

    private Integer contentType;

    private Integer noticeType;

    private Integer stick;

    public Integer getStick() {
        return stick;
    }

    public void setStick(Integer stick) {
        this.stick = stick;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Integer getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    public Integer getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(Integer viewNumber) {
        this.viewNumber = viewNumber;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getPeriodType() {
        return periodType;
    }

    public void setPeriodType(Integer periodType) {
        this.periodType = periodType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public static final String NAME = "name";

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

    public static final String CONTENT_PATH = "content_Path";

    public static final String PICTURE_URL = "picture_url";


    public static final Integer PERIOD_TYPE_NOT_SET = 0;

    public static final Integer PERIOD_TYPE_EVERY_DAY = 1;

    public static final Integer PERIOD_TYPE_FOREVER = 2;

    public static final Integer NOTICE_Type_NOTICE=0;

    public static final Integer NOTICE_TYPE_PRODUCE=1;

    public static final Integer CONTENT_TYPE_CURRENT=0;

    public static final Integer CONTENT_TYPE_REFERENCE=1;

    public static final Integer STICK_NOT_TOP=0;

    public static final Integer STICK_TOP=1;


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
