package com.jfeat.module.rss.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author Code generator
 * @since 2022-10-22
 */
@TableName("t_rss_rules")
@ApiModel(value = "RssRules对象", description = "")
public class RssRules extends Model<RssRules> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "符号")
    private String symbol;

    @ApiModelProperty(value = "分割符")
    private String decollator;

    @ApiModelProperty(value = "执行方法")
    private String handMethod;

    @ApiModelProperty(value = "类别")
    private String category;

    @ApiModelProperty(value = "备注")
    private String note;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty("默认css风格")
    private String cssName;


    @ApiModelProperty("优先级")
    private Integer priority;


    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getCssName() {
        return cssName;
    }

    public void setCssName(String cssName) {
        this.cssName = cssName;
    }

    public Long getId() {
        return id;
    }

    public RssRules setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public RssRules setName(String name) {
        this.name = name;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public RssRules setSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getDecollator() {
        return decollator;
    }

    public RssRules setDecollator(String decollator) {
        this.decollator = decollator;
        return this;
    }

    public String getHandMethod() {
        return handMethod;
    }

    public RssRules setHandMethod(String handMethod) {
        this.handMethod = handMethod;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public RssRules setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getNote() {
        return note;
    }

    public RssRules setNote(String note) {
        this.note = note;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public RssRules setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public RssRules setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String SYMBOL = "symbol";

    public static final String DECOLLATOR = "decollator";

    public static final String HAND_METHOD = "hand_method";

    public static final String CATEGORY = "category";

    public static final String NOTE = "note";

    public static final String PRIORITY = "priority";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RssRules{" +
                "id=" + id +
                ", name=" + name +
                ", symbol=" + symbol +
                ", decollator=" + decollator +
                ", handMethod=" + handMethod +
                ", category=" + category +
                ", note=" + note +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                "}";
    }
}
