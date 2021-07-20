package com.jfeat.am.module.cms.services.persistence.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author admin
 * @since 2018-03-27
 */
public class Article extends Model<Article> {

    private static final long serialVersionUID = 1L;
    private Integer display;
    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 类型ID
     */
    @TableField("type")
    private String type;
    /**
     * 类别ID
     */
    @TableField("category_id")
    private Long categoryId;
    /**
     * 用户ID
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 章节
     */
    @TableField("sub_title")
    private String subTitle;
    /**
     * 原文
     */
    @TableField("sub_head")
    private String subHead;
    /**
     * 内容摘要
     */
    private String summary;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("created_time")
    private Date createdTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 1置顶,0不置顶
     */
    private Integer sticky;
    /**
     * 1精华帖,0普通帖
     */
    private Integer recommended;
    /**
     * 阅读量
     */
    @TableField("visit_count")
    private Integer visitCount;
    /**
     * 点赞数量
     */
    @TableField("like_count")
    private Integer likeCount;
    /**
     * 收藏数量
     */
    @TableField("favorite_count")
    private Integer favoriteCount;
    /**
     * 状态
     */
    private String status;

    /**
     * 状态
     */
    private String author;
    /*
    *  封面
    * */
    private String cover;

    public Integer getDisplay() {
        return display;
    }

    public void setDisplay(Integer display) {
        this.display = display;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSticky() {
        return sticky;
    }

    public void setSticky(Integer sticky) {
        this.sticky = sticky;
    }

    public Integer getRecommended() {
        return recommended;
    }

    public void setRecommended(Integer recommended) {
        this.recommended = recommended;
    }

    public Integer getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(Integer visitCount) {
        this.visitCount = visitCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(Integer favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getSubHead() {
        return subHead;
    }

    public void setSubHead(String subHead) {
        this.subHead = subHead;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public static final String ID = "id";

    public static final String TYPE = "type";

    public static final String CATEGORY_ID = "category_id";

    public static final String USER_ID = "user_id";

    public static final String TITLE = "title";

    public static final String SUMMARY = "summary";

    public static final String CREATED_TIME = "created_time";

    public static final String PUBLISH_TIME = "publish_time";

    public static final String STICKY = "sticky";

    public static final String RECOMMENDED = "recommended";

    public static final String VISIT_COUNT = "visit_count";

    public static final String LIKE_COUNT = "like_count";

    public static final String FAVORITE_COUNT = "favorite_count";

    public static final String STATUS = "status";

    public static final String SUB_TITLE = "sub_title";

    public static final String SUB_HEAD = "sub_head";

    public static final String AUTHOR = "author";

    public static final String COVER = "cover";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Article{" +
                ", id=" + id +
                ", type=" + type +
                ", categoryId=" + categoryId +
                ", userId=" + userId +
                ", title=" + title +
                ", summary=" + summary +
                ", createdTime=" + createdTime +
                ", updateTime=" + updateTime +
                ", sticky=" + sticky +
                ", recommended=" + recommended +
                ", visitCount=" + visitCount +
                ", likeCount=" + likeCount +
                ", favoriteCount=" + favoriteCount +
                ", status=" + status +
                ", subTitle=" + subTitle +
                ", subHead=" + subHead +
                ", author=" + author +
                ", cover=" + cover +
                "}";
    }
}
