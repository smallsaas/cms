package com.jfeat.module.feedback.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code generator
 * @since 2022-01-27
 */
@TableName("nft_complain_type")
@ApiModel(value="申诉类型", description="")
public class ComplainType extends Model<ComplainType> {

    private static final long serialVersionUID=1L;

      @ApiModelProperty(value = "申诉单ID")
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

    private String title;

      @ApiModelProperty(value = "记录创建时间")
      @JsonFormat(timezone = "GMT+8" , pattern = "yyyy:MM:dd")
      private Date createTime;

      @ApiModelProperty(value = "记录更新时间")
      @JsonFormat(timezone = "GMT+8" , pattern = "yyyy:MM:dd")
      private Date updateTime;

      @ApiModelProperty(value = "类型")
      private String requestType;

    
    public Long getId() {
        return id;
    }

      public ComplainType setId(Long id) {
          this.id = id;
          return this;
      }
    
    public String getTitle() {
        return title;
    }

      public ComplainType setTitle(String title) {
          this.title = title;
          return this;
      }
    
    public Date getCreateTime() {
        return createTime;
    }

      public ComplainType setCreateTime(Date createTime) {
          this.createTime = createTime;
          return this;
      }
    
    public Date getUpdateTime() {
        return updateTime;
    }

      public ComplainType setUpdateTime(Date updateTime) {
          this.updateTime = updateTime;
          return this;
      }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public static final String ID = "id";

      public static final String TITLE = "title";

      public static final String CREATE_TIME = "create_time";

      public static final String UPDATE_TIME = "update_time";

      public static final String REQUEST_TYPE = "request_type";


      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "ComplainType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", requestType='" + requestType + '\'' +
                '}';
    }
}
