package com.jfeat.module.rss.services.gen.persistence.model;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import com.jfeat.module.rss.services.gen.crud.model.RssComponentPropModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 *
 * </p>
 *
 * @author Code generator
 * @since 2022-09-30
 */
@TableName("t_rss_component")
@ApiModel(value = "RssComponent对象", description = "")
public class RssComponent extends Model<RssComponent> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "rss_item_id")
    private Long rssItemId;

    @ApiModelProperty(value = "组件中文名")
    private String name;

    @ApiModelProperty(value = "基于不同属性派生出来的新组件")
    private String componentName;

    @ApiModelProperty(value = "前端已开发的组件类型")
    private String componentType;

    @ApiModelProperty(value = "组件类型")
    private String componentOption;

    @ApiModelProperty(value = "组件表单输入类型")
    private String componentFormInputOption;

    @ApiModelProperty(value = "组件类型子分类")
    private String componentTag;

    @ApiModelProperty("cssName")
    private String cssName;

    private String cssValue;

    private String componentStyle;

    private String componentStyleValue;

    private String componentArrangement;


    private String componentArrangementValue;

    @TableField(exist = false)
    private JSONObject css;

    @TableField(exist = false)
    List<RssComponentProp> rssComponentPropList;


    public String getCssValue() {
        return cssValue;
    }

    public void setCssValue(String cssValue) {
        this.cssValue = cssValue;
    }

    public String getComponentStyle() {
        return componentStyle;
    }

    public void setComponentStyle(String componentStyle) {
        this.componentStyle = componentStyle;
    }

    public String getComponentStyleValue() {
        return componentStyleValue;
    }

    public void setComponentStyleValue(String componentStyleValue) {
        this.componentStyleValue = componentStyleValue;
    }

    public String getComponentArrangement() {
        return componentArrangement;
    }

    public void setComponentArrangement(String componentArrangement) {
        this.componentArrangement = componentArrangement;
    }

    public String getComponentArrangementValue() {
        return componentArrangementValue;
    }

    public void setComponentArrangementValue(String componentArrangementValue) {
        this.componentArrangementValue = componentArrangementValue;
    }

    public String getCssName() {
        return cssName;
    }

    public void setCssName(String cssName) {
        this.cssName = cssName;
    }

    public JSONObject getCss() {
        return css;
    }

    public void setCss(JSONObject css) {
        this.css = css;
    }

    public List<RssComponentProp> getRssComponentPropList() {
        return rssComponentPropList;
    }

    public void setRssComponentPropList(List<RssComponentProp> rssComponentPropList) {
        this.rssComponentPropList = rssComponentPropList;
    }


    public Long getId() {
        return id;
    }

    public RssComponent setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRssItemId() {
        return rssItemId;
    }

    public RssComponent setRssItemId(Long rssItemId) {
        this.rssItemId = rssItemId;
        return this;
    }

    public String getName() {
        return name;
    }

    public RssComponent setName(String name) {
        this.name = name;
        return this;
    }

    public String getComponentName() {
        return componentName;
    }

    public RssComponent setComponentName(String componentName) {
        this.componentName = componentName;
        return this;
    }

    public String getComponentType() {
        return componentType;
    }

    public RssComponent setComponentType(String componentType) {
        this.componentType = componentType;
        return this;
    }

    public String getComponentOption() {
        return componentOption;
    }

    public RssComponent setComponentOption(String componentOption) {
        this.componentOption = componentOption;
        return this;
    }

    public String getComponentFormInputOption() {
        return componentFormInputOption;
    }

    public RssComponent setComponentFormInputOption(String componentFormInputOption) {
        this.componentFormInputOption = componentFormInputOption;
        return this;
    }

    public String getComponentTag() {
        return componentTag;
    }

    public RssComponent setComponentTag(String componentTag) {
        this.componentTag = componentTag;
        return this;
    }

    public static final String ID = "id";

    public static final String RSS_ITEM_ID = "rss_item_id";

    public static final String NAME = "name";

    public static final String COMPONENT_NAME = "component_name";

    public static final String COMPONENT_TYPE = "component_type";

    public static final String COMPONENT_OPTION = "component_option";

    public static final String COMPONENT_FORM_INPUT_OPTION = "component_form_input_option";

    public static final String COMPONENT_TAG = "component_tag";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RssComponent{" +
                "id=" + id +
                ", rssItemId=" + rssItemId +
                ", name=" + name +
                ", componentName=" + componentName +
                ", componentType=" + componentType +
                ", componentOption=" + componentOption +
                ", componentFormInputOption=" + componentFormInputOption +
                ", componentTag=" + componentTag +
                "}";
    }
}
