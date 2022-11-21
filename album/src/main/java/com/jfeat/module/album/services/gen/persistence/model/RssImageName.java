package com.jfeat.module.album.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Code generator
 * @since 2022-10-21
 */
@TableName("t_rss_image_name")
@ApiModel(value = "RssImageName对象", description = "")
public class RssImageName extends Model<RssImageName> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "社区管理")
    private Long orgId;

    @ApiModelProperty(value = "分类")
    private String category;

    @ApiModelProperty(value = "是否启用")
    private Integer state;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @TableField(exist = false)
    private String images;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }


    public Long getId() {
        return id;
    }

    public RssImageName setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RssImageName setName(String name) {
        this.name = name;
        return this;
    }

    public Long getOrgId() {
        return orgId;
    }

    public RssImageName setOrgId(Long orgId) {
        this.orgId = orgId;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public RssImageName setCategory(String category) {
        this.category = category;
        return this;
    }

    public Integer getState() {
        return state;
    }

    public RssImageName setState(Integer state) {
        this.state = state;
        return this;
    }

    public String getNote() {
        return note;
    }

    public RssImageName setNote(String note) {
        this.note = note;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public RssImageName setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public RssImageName setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String ORG_ID = "org_id";

    public static final String CATEGORY = "category";

    public static final String STATE = "state";

    public static final String NOTE = "note";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RssImageName{" +
                "id=" + id +
                ", name=" + name +
                ", orgId=" + orgId +
                ", category=" + category +
                ", state=" + state +
                ", note=" + note +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
