package com.jfeat.module.feedback.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code generator
 * @since 2022-08-12
 */
@TableName("nft_complain_record")
public class ComplainRecord extends Model<ComplainRecord> {

    private static final long serialVersionUID=1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 鐢宠瘔鑰匢D
     */
      private Long complainantId;

      /**
     * 鍏宠仈璁㈠崟ID
     */
      private Long relationOrderId;

      /**
     * 鐢宠瘔鑰呰韩浠�
     */
      private String complainantRole;

      /**
     * 鐢宠瘔鏍囬
     */
      private String title;

      /**
     * 鐢宠瘔鍐呭
     */
      private String content;

      /**
     * 鍑瘉閾炬帴
     */
      private String credentialLink;

    private String status;

    private Date createTime;

    private Date updateTime;

    private String requestType;

    private String appid;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public Long getId() {
        return id;
    }

      public ComplainRecord setId(Long id) {
          this.id = id;
          return this;
      }
    
    public Long getComplainantId() {
        return complainantId;
    }

      public ComplainRecord setComplainantId(Long complainantId) {
          this.complainantId = complainantId;
          return this;
      }
    
    public Long getRelationOrderId() {
        return relationOrderId;
    }

      public ComplainRecord setRelationOrderId(Long relationOrderId) {
          this.relationOrderId = relationOrderId;
          return this;
      }
    
    public String getComplainantRole() {
        return complainantRole;
    }

      public ComplainRecord setComplainantRole(String complainantRole) {
          this.complainantRole = complainantRole;
          return this;
      }
    
    public String getTitle() {
        return title;
    }

      public ComplainRecord setTitle(String title) {
          this.title = title;
          return this;
      }
    
    public String getContent() {
        return content;
    }

      public ComplainRecord setContent(String content) {
          this.content = content;
          return this;
      }
    
    public String getCredentialLink() {
        return credentialLink;
    }

      public ComplainRecord setCredentialLink(String credentialLink) {
          this.credentialLink = credentialLink;
          return this;
      }
    
    public String getStatus() {
        return status;
    }

      public ComplainRecord setStatus(String status) {
          this.status = status;
          return this;
      }
    
    public Date getCreateTime() {
        return createTime;
    }

      public ComplainRecord setCreateTime(Date createTime) {
          this.createTime = createTime;
          return this;
      }
    
    public Date getUpdateTime() {
        return updateTime;
    }

      public ComplainRecord setUpdateTime(Date updateTime) {
          this.updateTime = updateTime;
          return this;
      }
    
    public String getRequestType() {
        return requestType;
    }

      public ComplainRecord setRequestType(String requestType) {
          this.requestType = requestType;
          return this;
      }

      public static final String ID = "id";

      public static final String COMPLAINANT_ID = "complainant_id";

      public static final String RELATION_ORDER_ID = "relation_order_id";

      public static final String COMPLAINANT_ROLE = "complainant_role";

      public static final String TITLE = "title";

      public static final String CONTENT = "content";

      public static final String CREDENTIAL_LINK = "credential_link";

      public static final String STATUS = "status";

      public static final String CREATE_TIME = "create_time";

      public static final String UPDATE_TIME = "update_time";

      public static final String REQUEST_TYPE = "request_type";

      @Override
    protected Serializable pkVal() {
          return this.id;
      }

    @Override
    public String toString() {
        return "ComplainRecord{" +
              "id=" + id +
                  ", complainantId=" + complainantId +
                  ", relationOrderId=" + relationOrderId +
                  ", complainantRole=" + complainantRole +
                  ", title=" + title +
                  ", content=" + content +
                  ", credentialLink=" + credentialLink +
                  ", status=" + status +
                  ", createTime=" + createTime +
                  ", updateTime=" + updateTime +
                  ", requestType=" + requestType +
              "}";
    }
}
