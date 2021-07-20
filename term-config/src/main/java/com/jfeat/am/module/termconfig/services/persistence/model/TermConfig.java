package com.jfeat.am.module.termconfig.services.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Code Generator
 * @since 2017-12-09
 */
@TableName("t_term_config")
public class TermConfig extends Model<TermConfig> {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 类型 ABOUT_US,NOTICE,CONNECT_US ...
     */
    private String type;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;

    @TableField("org_id")
    private String orgId;

    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;

    /**
     * 上一次修改时间
     */
    @TableField("previous_modified_time")
    private Date previousModifiedTime;

    @TableField("last_modified_time")
    private Date lastModifiedTime;

    public Long getId() {
        return id;
    }

    public TermConfig setId(Long id) {
        this.id = id;
        return this;
    }

    public String getType() {
        return type;
    }

    public TermConfig setType(String type) {
        this.type = type;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TermConfig setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getContent() {
        return content;
    }

    public TermConfig setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public TermConfig setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public Date getPreviousModifiedTime() {
        return previousModifiedTime;
    }

    public void setPreviousModifiedTime(Date previousModifiedTime) {
        this.previousModifiedTime = previousModifiedTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String TITLE = "title";

    public static final String CONTENT = "content";

    public static final String CREATED_TIME = "created_time";

    public static final String PREVIOUS_MODIFIED_TIME = "previous_modified_time";

    public static final String LAST_MODIFIED_TIME = "last_modified_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public String toString() {
        return "TermConfig{" +
                "id=" + id +
                ", type=" + type +
                ", title=" + title +
                ", content=" + content +
                ", createdTime=" + createdTime +
                ", previousModifiedTime=" + previousModifiedTime +
                ", lastModifiedTime=" + lastModifiedTime +
                "}";
    }
}
