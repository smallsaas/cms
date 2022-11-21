package com.jfeat.module.album.services.gen.persistence.model;

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
 * @since 2022-10-21
 */
@TableName("t_rss_image_prop")
@ApiModel(value="RssImageProp对象", description="")
public class RssImageProp extends Model<RssImageProp> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "父id")
      private Long pid;

      @ApiModelProperty(value = "图片路径")
      private String imagePath;

      @ApiModelProperty(value = "备注")
      private String note;

    
    public Long getId() {
        return id;
    }

      public RssImageProp setId(Long id) {
          this.id = id;
          return this;
      }
    
    public Long getPid() {
        return pid;
    }

      public RssImageProp setPid(Long pid) {
          this.pid = pid;
          return this;
      }
    
    public String getImagePath() {
        return imagePath;
    }

      public RssImageProp setImagePath(String imagePath) {
          this.imagePath = imagePath;
          return this;
      }
    
    public String getNote() {
        return note;
    }

      public RssImageProp setNote(String note) {
          this.note = note;
          return this;
      }

      public static final String ID = "id";

      public static final String PID = "pid";

      public static final String IMAGE_PATH = "image_path";

      public static final String NOTE = "note";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "RssImageProp{" +
              "id=" + id +
                  ", pid=" + pid +
                  ", imagePath=" + imagePath +
                  ", note=" + note +
              "}";
    }
}
