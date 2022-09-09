package com.jfeat.am.module.icon.services.gen.persistence.model;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code generator
 * @since 2022-09-02
 */
@TableName("t_front_icon")
public class FrontIcon extends Model<FrontIcon> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private String icon;

    private String name;

    @TableField("`describe`")
    private String describe;



    @TableField(exist = false)
    private JSONObject extra;

    @TableField(exist = false)
    private String tags;

    @TableField(exist = false)
    private String tagIds;

    public JSONObject getExtra() {
        return extra;
    }

    public void setExtra(JSONObject extra) {
        this.extra = extra;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Long getId() {
        return id;
    }

      public FrontIcon setId(Long id) {
          this.id = id;
          return this;
      }
    
    public String getIcon() {
        return icon;
    }

      public FrontIcon setIcon(String icon) {
          this.icon = icon;
          return this;
      }
    
    public String getName() {
        return name;
    }

      public FrontIcon setName(String name) {
          this.name = name;
          return this;
      }

      public static final String ID = "id";

      public static final String ICON = "icon";

      public static final String NAME = "name";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "FrontIcon{" +
              "id=" + id +
                  ", icon=" + icon +
                  ", name=" + name +
              "}";
    }
}
