package com.jfeat.module.rss.services.gen.persistence.model;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;
import java.util.List;

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
@TableName("t_rss")
@ApiModel(value = "Rss对象", description = "")
public class Rss extends Model<Rss> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "uuid")
    private String uuid;

    @ApiModelProperty(value = "orgId")
    @TableField("orgId")
    private Long orgId;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "排序号")
    private Integer sortNumber;

    @ApiModelProperty(value = "备用")
    private String note;

    @ApiModelProperty("简述")
    private String summary;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @TableField(exist = false)
    private List<RssItem> rssItemList;



    @TableField(exist = false)
    private JSONObject extra;

    @TableField(exist = false)
    private String tags;

    @TableField(exist = false)
    private String tagIds;


    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public JSONObject getExtra() {
        return extra;
    }

    public void setExtra(JSONObject extra) {
        this.extra = extra;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public List<RssItem> getRssItemList() {
        return rssItemList;
    }

    public void setRssItemList(List<RssItem> rssItemList) {
        this.rssItemList = rssItemList;
    }

    public Long getId() {
        return id;
    }

    public Rss setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUuid() {
        return uuid;
    }

    public Rss setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Long getOrgId() {
        return orgId;
    }

    public Rss setOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Rss setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public Rss setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public Integer getSortNumber() {
        return sortNumber;
    }

    public Rss setSortNumber(Integer sortNumber) {
        this.sortNumber = sortNumber;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Rss setNote(String note) {
        this.note = note;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Rss setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Rss setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String ID = "id";

    public static final String UUID = "uuid";

    public static final String ORGID = "orgId";

    public static final String NAME = "name";

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
        return "Rss{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", orgId=" + orgId +
                ", name=" + name +
                ", status=" + status +
                ", sortNumber=" + sortNumber +
                ", note=" + note +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
