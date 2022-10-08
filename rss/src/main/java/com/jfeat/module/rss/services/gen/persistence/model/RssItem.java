package com.jfeat.module.rss.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import com.jfeat.module.rss.services.gen.crud.model.RssComponentModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author Code generator
 * @since 2022-09-26
 */
@TableName("t_rss_item")
@ApiModel(value = "RssItem对象", description = "")
public class RssItem extends Model<RssItem> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "父id")
    private Long pid;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "图片集")
    private String pictures;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    private Boolean fontWeight;

    private Integer fontSize;

    private Integer lineHeight;

    @ApiModelProperty(value = "备用")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @TableField(exist = false)
    List<RssComponent> rssComponentList;

    public List<RssComponent> getRssComponentList() {
        return rssComponentList;
    }

    public void setRssComponentList(List<RssComponent> rssComponentList) {
        this.rssComponentList = rssComponentList;
    }

    public Long getId() {
        return id;
    }

    public RssItem setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getPid() {
        return pid;
    }

    public RssItem setPid(Long pid) {
        this.pid = pid;
        return this;
    }

    public Boolean getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(Boolean fontWeight) {
        this.fontWeight = fontWeight;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Integer getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(Integer lineHeight) {
        this.lineHeight = lineHeight;
    }

    public String getTitle() {
        return title;
    }

    public RssItem setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPictures() {
        return pictures;
    }

    public RssItem setPictures(String pictures) {
        this.pictures = pictures;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public RssItem setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public RssItem setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
        return this;
    }

    public String getNote() {
        return note;
    }

    public RssItem setNote(String note) {
        this.note = note;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public RssItem setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public RssItem setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String ID = "id";

    public static final String PID = "pid";

    public static final String TITLE = "title";

    public static final String PICTURES = "pictures";

    public static final String STATUS = "status";

    public static final String SORT_NUMBER = "sort_number";

    public static final String NOTE = "note";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RssItem{" +
                "id=" + id +
                ", pid=" + pid +
                ", title=" + title +
                ", pictures=" + pictures +
                ", status=" + status +
                ", sortNumber=" + sortNumber +
                ", note=" + note +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
