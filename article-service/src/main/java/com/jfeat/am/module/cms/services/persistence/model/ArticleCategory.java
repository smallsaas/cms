package com.jfeat.am.module.cms.services.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-03-27
 */
@TableName("article_category")
public class ArticleCategory extends Model<ArticleCategory> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * 类别ID
     */
    @TableField("type_id")
    private Long typeId;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 图片
     */
    private String cover;
    /**
     * 允许上传图片
     */
    @TableField("allow_image")
    private Integer allowImage;

    @TableField("fast_entry")
    private Integer fastEntry;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getAllowImage() {
        return allowImage;
    }

    public void setAllowImage(Integer allowImage) {
        this.allowImage = allowImage;
    }

    public Integer getFastEntry() {
        return fastEntry;
    }

    public void setFastEntry(Integer fastEntry) {
        this.fastEntry = fastEntry;
    }

    public static final String ID = "id";

    public static final String TYPE_ID = "type_id";

    public static final String NAME = "name";

    public static final String COVER = "cover";

    public static final String ALLOW_IMAGE = "allow_image";

    public static final String FAST_ENTRY = "fast_entry";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ArticleCategory{" +
        ", id=" + id +
        ", typeId=" + typeId +
        ", name=" + name +
        ", cover=" + cover +
        ", allowImage=" + allowImage +
        "}";
    }
}
