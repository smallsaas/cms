package com.jfeat.module.rss.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2022-09-30
 */
@TableName("t_rss_component_prop")
@ApiModel(value = "RssComponentProp对象", description = "")
public class RssComponentProp extends Model<RssComponentProp> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "所属标准组件")
    private Long componentId;

    @ApiModelProperty(value = "属性键/名称")
    private String propName;

    @ApiModelProperty(value = "属性中文名称")
    private String propTips;

    @ApiModelProperty(value = "标准组件属性默认值")
    private String propDefaultValue;

    @ApiModelProperty(value = "SELECT单选项  MULTISELECT多选项  NUMBER数字  TEXT文本")
    private String dataType;

    @ApiModelProperty(value = "如果data_type=[select,multiselect],限制属性值选项")
    private String optionName;

    private String propStyle;

    private String propStyleValue;

    private String propCssName;

    private String propCssValue;

    private String propArrangement;

    private String propArrangementValue;

    private String propLimit;

    public String getPropStyle() {
        return propStyle;
    }

    public void setPropStyle(String propStyle) {
        this.propStyle = propStyle;
    }

    public String getPropStyleValue() {
        return propStyleValue;
    }

    public void setPropStyleValue(String propStyleValue) {
        this.propStyleValue = propStyleValue;
    }

    public String getPropCssName() {
        return propCssName;
    }

    public void setPropCssName(String propCssName) {
        this.propCssName = propCssName;
    }

    public String getPropCssValue() {
        return propCssValue;
    }

    public void setPropCssValue(String propCssValue) {
        this.propCssValue = propCssValue;
    }

    public String getPropArrangement() {
        return propArrangement;
    }

    public void setPropArrangement(String propArrangement) {
        this.propArrangement = propArrangement;
    }

    public String getPropArrangementValue() {
        return propArrangementValue;
    }

    public void setPropArrangementValue(String propArrangementValue) {
        this.propArrangementValue = propArrangementValue;
    }

    public String getPropLimit() {
        return propLimit;
    }

    public void setPropLimit(String propLimit) {
        this.propLimit = propLimit;
    }

    public Long getId() {
        return id;
    }

    public RssComponentProp setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getComponentId() {
        return componentId;
    }

    public RssComponentProp setComponentId(Long componentId) {
        this.componentId = componentId;
        return this;
    }

    public String getPropName() {
        return propName;
    }

    public RssComponentProp setPropName(String propName) {
        this.propName = propName;
        return this;
    }

    public String getPropTips() {
        return propTips;
    }

    public RssComponentProp setPropTips(String propTips) {
        this.propTips = propTips;
        return this;
    }

    public String getPropDefaultValue() {
        return propDefaultValue;
    }

    public RssComponentProp setPropDefaultValue(String propDefaultValue) {
        this.propDefaultValue = propDefaultValue;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public RssComponentProp setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getOptionName() {
        return optionName;
    }

    public RssComponentProp setOptionName(String optionName) {
        this.optionName = optionName;
        return this;
    }

    public static final String ID = "id";

    public static final String COMPONENT_ID = "component_id";

    public static final String PROP_NAME = "prop_name";

    public static final String PROP_TIPS = "prop_tips";

    public static final String PROP_DEFAULT_VALUE = "prop_default_value";

    public static final String DATA_TYPE = "data_type";

    public static final String OPTION_NAME = "option_name";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RssComponentProp{" +
                "id=" + id +
                ", componentId=" + componentId +
                ", propName=" + propName +
                ", propTips=" + propTips +
                ", propDefaultValue=" + propDefaultValue +
                ", dataType=" + dataType +
                ", optionName=" + optionName +
                "}";
    }
}
