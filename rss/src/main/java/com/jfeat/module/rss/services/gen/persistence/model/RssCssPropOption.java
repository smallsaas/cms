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
@TableName("t_rss_css_prop_option")
@ApiModel(value="RssCssPropOption对象", description="")
public class RssCssPropOption extends Model<RssCssPropOption> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "id")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "关联id")
      private Long cssNamePropsId;

      @ApiModelProperty(value = "属性名称")
      private String propName;

      @ApiModelProperty(value = "属性值选项")
      private String propOption;

      @ApiModelProperty(value = "备注")
      private String note;

    
    public Long getId() {
        return id;
    }

      public RssCssPropOption setId(Long id) {
          this.id = id;
          return this;
      }
    
    public Long getCssNamePropsId() {
        return cssNamePropsId;
    }

      public RssCssPropOption setCssNamePropsId(Long cssNamePropsId) {
          this.cssNamePropsId = cssNamePropsId;
          return this;
      }
    
    public String getPropName() {
        return propName;
    }

      public RssCssPropOption setPropName(String propName) {
          this.propName = propName;
          return this;
      }
    
    public String getPropOption() {
        return propOption;
    }

      public RssCssPropOption setPropOption(String propOption) {
          this.propOption = propOption;
          return this;
      }
    
    public String getNote() {
        return note;
    }

      public RssCssPropOption setNote(String note) {
          this.note = note;
          return this;
      }

      public static final String ID = "id";

      public static final String CSS_NAME_PROPS_ID = "css_name_props_id";

      public static final String PROP_NAME = "prop_name";

      public static final String PROP_OPTION = "prop_option";

      public static final String NOTE = "note";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "RssCssPropOption{" +
              "id=" + id +
                  ", cssNamePropsId=" + cssNamePropsId +
                  ", propName=" + propName +
                  ", propOption=" + propOption +
                  ", note=" + note +
              "}";
    }
}
