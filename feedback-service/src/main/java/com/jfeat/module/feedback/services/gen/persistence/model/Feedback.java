package com.jfeat.module.feedback.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
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
 * @since 2022-01-15
 */
@TableName("t_feedback")
@ApiModel(value="Feedback对象", description="")
public class Feedback extends Model<Feedback> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      @ApiModelProperty(value = "反馈用户ID")
      private Long userId;

      @ApiModelProperty(value = "反馈用户名")
      private String userName;

      @ApiModelProperty(value = "联系方式")
      private String contact;

      @ApiModelProperty(value = "反馈内容")
      private String feedback;

      @ApiModelProperty(value = "返馈图片")
      private String feedbackImage;

      @ApiModelProperty(value = "状态[UNREAD,SOLVED,CLOSED]")
      private String status;

      @ApiModelProperty(value = "处理人签名")
      private String solvedBy;

      @ApiModelProperty(value = "处理人意见")
      private String solvedNote;

      @ApiModelProperty(value = "处理人用户ID")
      private Long solvedUserId;

      @ApiModelProperty(value = "反馈时间")
      private Date createTime;

      @ApiModelProperty(value = "最新反馈处理时间")
      private Date updateTime;

    
    public Long getId() {
        return id;
    }

      public Feedback setId(Long id) {
          this.id = id;
          return this;
      }
    
    public Long getUserId() {
        return userId;
    }

      public Feedback setUserId(Long userId) {
          this.userId = userId;
          return this;
      }
    
    public String getUserName() {
        return userName;
    }

      public Feedback setUserName(String userName) {
          this.userName = userName;
          return this;
      }
    
    public String getContact() {
        return contact;
    }

      public Feedback setContact(String contact) {
          this.contact = contact;
          return this;
      }
    
    public String getFeedback() {
        return feedback;
    }

      public Feedback setFeedback(String feedback) {
          this.feedback = feedback;
          return this;
      }
    
    public String getFeedbackImage() {
        return feedbackImage;
    }

      public Feedback setFeedbackImage(String feedbackImage) {
          this.feedbackImage = feedbackImage;
          return this;
      }
    
    public String getStatus() {
        return status;
    }

      public Feedback setStatus(String status) {
          this.status = status;
          return this;
      }
    
    public String getSolvedBy() {
        return solvedBy;
    }

      public Feedback setSolvedBy(String solvedBy) {
          this.solvedBy = solvedBy;
          return this;
      }
    
    public String getSolvedNote() {
        return solvedNote;
    }

      public Feedback setSolvedNote(String solvedNote) {
          this.solvedNote = solvedNote;
          return this;
      }
    
    public Long getSolvedUserId() {
        return solvedUserId;
    }

      public Feedback setSolvedUserId(Long solvedUserId) {
          this.solvedUserId = solvedUserId;
          return this;
      }
    
    public Date getCreateTime() {
        return createTime;
    }

      public Feedback setCreateTime(Date createTime) {
          this.createTime = createTime;
          return this;
      }
    
    public Date getUpdateTime() {
        return updateTime;
    }

      public Feedback setUpdateTime(Date updateTime) {
          this.updateTime = updateTime;
          return this;
      }

      public static final String ID = "id";

      public static final String USER_ID = "user_id";

      public static final String USER_NAME = "user_name";

      public static final String CONTACT = "contact";

      public static final String FEEDBACK = "feedback";

      public static final String FEEDBACK_IMAGE = "feedback_image";

      public static final String STATUS = "status";

      public static final String SOLVED_BY = "solved_by";

      public static final String SOLVED_NOTE = "solved_note";

      public static final String SOLVED_USER_ID = "solved_user_id";

      public static final String CREATE_TIME = "create_time";

      public static final String UPDATE_TIME = "update_time";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "Feedback{" +
              "id=" + id +
                  ", userId=" + userId +
                  ", userName=" + userName +
                  ", contact=" + contact +
                  ", feedback=" + feedback +
                  ", feedbackImage=" + feedbackImage +
                  ", status=" + status +
                  ", solvedBy=" + solvedBy +
                  ", solvedNote=" + solvedNote +
                  ", solvedUserId=" + solvedUserId +
                  ", createTime=" + createTime +
                  ", updateTime=" + updateTime +
              "}";
    }
}
