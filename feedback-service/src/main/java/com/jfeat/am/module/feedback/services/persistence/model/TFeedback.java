package com.jfeat.am.module.feedback.services.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author admin
 * @since 2017-11-28
 */
@TableName("t_feedback")
public class TFeedback extends Model<TFeedback> {

    private static final long serialVersionUID = 1L;

    private Long id;
    @TableField("user_id")
    private Long userId;
    private String content;
    @TableField("created_date")
    private Date createdDate;
    private Integer unread;
    private Integer solved;
    @TableField("deal_user_id")
    private Long dealUserId;
    @TableField("deal_opinion")
    private String dealOpinion;
    @TableField("create_name")
    private String createName;

    @NotBlank
    @TableField("connect_way")
    private String connectWay;


    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getConnectWay() {
        return connectWay;
    }

    public void setConnectWay(String connectWay) {
        this.connectWay = connectWay;
    }

    public Long getId() {
        return id;
    }

    public TFeedback setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public TFeedback setUserId(Long userId) {
        this.userId = userId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public TFeedback setContent(String content) {
        this.content = content;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public TFeedback setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Integer getUnread() {
        return unread;
    }

    public TFeedback setUnread(Integer unread) {
        this.unread = unread;
        return this;
    }

    public Integer getSolved() {
        return solved;
    }

    public void setSolved(Integer solved) {
        this.solved = solved;
    }

    public Long getDealUserId() {
        return dealUserId;
    }

    public void setDealUserId(Long dealUserId) {
        this.dealUserId = dealUserId;
    }

    public String getDealOpinion() {
        return dealOpinion;
    }

    public void setDealOpinion(String dealOpinion) {
        this.dealOpinion = dealOpinion;
    }

    public static String getCONTENT() {
        return CONTENT;
    }

    public static String getUNREAD() {
        return UNREAD;
    }

    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String CONTENT = "content";

    public static final String CREATED_DATE = "created_date";

    public static final String UNREAD = "unread";

    public static final String SOLVED = "solved";

    public static final String DEAL_USER_ID = "deal_user_id";

    public static final String DEAL_OPINION = "deal_opinion";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TFeedback{" +
                "id=" + id +
                ", userId=" + userId +
                ", content=" + content +
                ", createdDate=" + createdDate +
                ", unread=" + unread +
                ", solved=" + solved +
                ", dealUserId=" + dealUserId +
                ", dealOpinion=" + dealOpinion +
                "}";
    }
}
