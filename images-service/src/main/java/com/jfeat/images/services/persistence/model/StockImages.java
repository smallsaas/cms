package com.jfeat.images.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 *
 * </p>
 *
 * @author Code Generator
 * @since 2018-08-07
 */
@TableName("t_stock_images")
public class StockImages extends Model<StockImages> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * images address
     */
    @TableField("url")
    private String url;
    /**
     * owner id
     */
    @TableField("stock_id")
    private Long stockId;
    /**
     * owner type:like product/member and etc...
     */
    @TableField("stock_type")
    private String stockType;
    /**
     * owner type:like product/member and etc...
     */
    @TableField("image_note")
    private String imageNote;
    /**
     * owner type:like product/member and etc...
     */
    @TableField("name")
    private String name;
    /**
     * is primary,0 means not ,1 means primary
     */
    @TableField("is_primary")
    private Integer isPrimary;
    /**
     * upload time
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @TableField("create_time")
    private Date createTime;


    public Long getId() {
        return id;
    }

    public StockImages setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getStockType() {
        return stockType;
    }

    public void setStockType(String stockType) {
        this.stockType = stockType;
    }

    public String getImageNote() {
        return imageNote;
    }

    public void setImageNote(String imageNote) {
        this.imageNote = imageNote;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsPrimary() {
        return isPrimary;
    }

    public StockImages setIsPrimary(Integer isPrimary) {
        this.isPrimary = isPrimary;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public StockImages setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public static final String ID = "id";

    public static final String URL = "url";

    public static final String STOCK_ID = "stock_id";

    public static final String STOCK_TYPE = "stock_type";

    public static final String IMAGE_NOTE = "image_note";

    public static final String NAME = "name";

    public static final String IS_PRIMARY = "is_primary";

    public static final String CREATE_TIME = "create_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StockImages{" +
                "id=" + id +
                ", url=" + url +
                ", stockId=" + stockId +
                ", stockType=" + stockType +
                ", imageNote=" + imageNote +
                ", name=" + name +
                ", isPrimary=" + isPrimary +
                ", createTime=" + createTime +
                "}";
    }
}
