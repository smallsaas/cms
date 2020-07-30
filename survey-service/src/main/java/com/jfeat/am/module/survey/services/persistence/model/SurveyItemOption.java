package com.jfeat.am.module.survey.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Code Generator
 * @since 2018-10-15
 */
@TableName("t_survey_item_option")
public class SurveyItemOption extends Model<SurveyItemOption> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 问题ID
     */
    @TableField("item_id")
    private Long itemId;
    /**
     * 选择项
     */
    @TableField("option_num")
    private Integer optionNum;
    /**
     * 选择项内容
     */
    private String content;
    /**
     * 图片地址
     */
    @TableField("image_url")
    private String imageUrl;


    /**
     * 是否启用该选项
     */
    @TableField("enabled")
    private Integer enabled;



    public Long getId() {
        return id;
    }

    public SurveyItemOption setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public SurveyItemOption setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public Integer getOptionNum() {
        return optionNum;
    }

    public SurveyItemOption setOptionNum(Integer optionNum) {
        this.optionNum = optionNum;
        return this;
    }

    public String getContent() {
        return content;
    }

    public SurveyItemOption setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public static final String ID = "id";

    public static final String ITEM_ID = "item_id";

    public static final String OPTION_NUM = "option_num";

    public static final String CONTENT = "content";

    public static final String IMAGE_URL = "image_url";

    public static final String ENABLED = "enabled";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SurveyItemOption{" +
                "id=" + id +
                ", itemId=" + itemId +
                ", optionNum=" + optionNum +
                ", content=" + content +
                ", imageUrl=" + imageUrl +
                ", enabled=" + enabled +
                "}";
    }
}
