package com.jfeat.module.rss.services.gen.persistence.model;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RssItemC {
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



    private String imageSort;

    private String imageRatio;

    private String imageExpression;

    private String imageContainer;

    private String imageStyle;

    @TableField(exist = false)
    private JSONObject imageStyleJson;

    @TableField(exist = false)
    private JSONObject imageContainerJson;

    @ApiModelProperty(value = "备用")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;


    @TableField(exist = false)
    List<RssComponent> rssComponentList;


    public String getImageSort() {
        return imageSort;
    }

    public void setImageSort(String imageSort) {
        this.imageSort = imageSort;
    }

    public String getImageRatio() {
        return imageRatio;
    }

    public void setImageRatio(String imageRatio) {
        this.imageRatio = imageRatio;
    }

    public String getImageExpression() {
        return imageExpression;
    }

    public void setImageExpression(String imageExpression) {
        this.imageExpression = imageExpression;
    }

    public String getImageContainer() {
        return imageContainer;
    }

    public void setImageContainer(String imageContainer) {
        this.imageContainer = imageContainer;
    }

    public String getImageStyle() {
        return imageStyle;
    }

    public void setImageStyle(String imageStyle) {
        this.imageStyle = imageStyle;
    }

    public JSONObject getImageStyleJson() {
        return imageStyleJson;
    }

    public void setImageStyleJson(JSONObject imageStyleJson) {
        this.imageStyleJson = imageStyleJson;
    }

    public JSONObject getImageContainerJson() {
        return imageContainerJson;
    }

    public void setImageContainerJson(JSONObject imageContainerJson) {
        this.imageContainerJson = imageContainerJson;
    }

    public List<RssComponent> getRssComponentList() {
        return rssComponentList;
    }

    public void setRssComponentList(List<RssComponent> rssComponentList) {
        this.rssComponentList = rssComponentList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPictures() {
        return pictures;
    }

    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
