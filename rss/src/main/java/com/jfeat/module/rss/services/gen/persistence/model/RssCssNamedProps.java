package com.jfeat.module.rss.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

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
 * @since 2022-09-30
 */
@TableName("t_rss_css_named_props")
@ApiModel(value = "RssCssNamedProps对象", description = "")
public class RssCssNamedProps extends Model<RssCssNamedProps> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "rss_item_id")
    private Long rssItemId;

    @ApiModelProperty(value = "所属应用")
    private String appid;

    @ApiModelProperty(value = "tag用于分类")
    private String tags;

    @ApiModelProperty(value = "显示名称")
    private String name;

    @ApiModelProperty(value = "css名称")
    private String cssName;

    @ApiModelProperty(value = "css值")
    private String cssValue;

    @ApiModelProperty(value = "属性值类型")
    private String dataType;

    @ApiModelProperty(value = "数据类型选项")
    private String optionName;

    @TableField(exist = false)
    private List<RssCssPropOption> rssCssPropOptionList;


    public List<RssCssPropOption> getRssCssPropOptionList() {
        return rssCssPropOptionList;
    }

    public void setRssCssPropOptionList(List<RssCssPropOption> rssCssPropOptionList) {
        this.rssCssPropOptionList = rssCssPropOptionList;
    }

    public Long getId() {
        return id;
    }

    public RssCssNamedProps setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getRssItemId() {
        return rssItemId;
    }

    public RssCssNamedProps setRssItemId(Long rssItemId) {
        this.rssItemId = rssItemId;
        return this;
    }

    public String getAppid() {
        return appid;
    }

    public RssCssNamedProps setAppid(String appid) {
        this.appid = appid;
        return this;
    }

    public String getTags() {
        return tags;
    }

    public RssCssNamedProps setTags(String tags) {
        this.tags = tags;
        return this;
    }

    public String getName() {
        return name;
    }

    public RssCssNamedProps setName(String name) {
        this.name = name;
        return this;
    }

    public String getCssName() {
        return cssName;
    }

    public RssCssNamedProps setCssName(String cssName) {
        this.cssName = cssName;
        return this;
    }

    public String getCssValue() {
        return cssValue;
    }

    public RssCssNamedProps setCssValue(String cssValue) {
        this.cssValue = cssValue;
        return this;
    }

    public String getDataType() {
        return dataType;
    }

    public RssCssNamedProps setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

    public String getOptionName() {
        return optionName;
    }

    public RssCssNamedProps setOptionName(String optionName) {
        this.optionName = optionName;
        return this;
    }

    public static final String ID = "id";

    public static final String RSS_ITEM_ID = "rss_item_id";

    public static final String APPID = "appid";

    public static final String TAGS = "tags";

    public static final String NAME = "name";

    public static final String CSS_NAME = "css_name";

    public static final String CSS_VALUE = "css_value";

    public static final String DATA_TYPE = "data_type";

    public static final String OPTION_NAME = "option_name";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RssCssNamedProps{" +
                "id=" + id +
                ", rssItemId=" + rssItemId +
                ", appid=" + appid +
                ", tags=" + tags +
                ", name=" + name +
                ", cssName=" + cssName +
                ", cssValue=" + cssValue +
                ", dataType=" + dataType +
                ", optionName=" + optionName +
                "}";
    }
}
