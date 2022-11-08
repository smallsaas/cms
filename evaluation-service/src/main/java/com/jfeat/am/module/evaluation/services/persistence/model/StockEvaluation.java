package com.jfeat.am.module.evaluation.services.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

import java.util.Date;


/**
 * <p>
 *
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-16
 */
@TableName("t_stock_evaluation")
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class StockEvaluation extends Model<StockEvaluation> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 外键
     */
    @TableField("stock_id")
    private Long stockId;
    /**
     * 外键
     */
    @TableField("member_id")
    private Long memberId;
    /**
     * 用户昵称
     */
    @TableField("member_name")
    private String memberName;
    /**
     * 类型 评价为 商品/订单/其他
     */
    @TableField("stock_type")
    private String stockType;
    /**
     * 类型 评价为 商品/订单/其他
     */
    @TableField("stock_tag")
    private String stockTag;
    /**
     * 评价信息
     */
    private String content;
    /**
     * 默认显示
     */
    @TableField("is_display")
    private Integer isDisplay;
    /**
     * 是否置顶 默认不置顶
     */
    @TableField("is_stick")
    private Integer isStick;
    /**
     * 状态
     */
    private String status;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;
    /**
     * 订单编号
     */
    @TableField("trade_number")
    private String tradeNumber;
    /**
     * 下单时间
     */
    @TableField("trade_time")
    private Date tradeTime;

    @TableField(exist = false)
    private Integer star;

    @TableField("dislike")
    private Integer dislike;

    private Long originId;

    private String originType;

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

    public Long getOriginId() {
        return originId;
    }

    public StockEvaluation setOriginId(Long originId) {
        this.originId = originId;
        return this;
    }

    public String getOriginType() {
        return originType;
    }

    public StockEvaluation setOriginType(String originType) {
        this.originType = originType;
        return this;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public StockEvaluation setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
        return this;
    }

    public String getTradeNumber() {
        return tradeNumber;
    }

    public StockEvaluation setTradeNumber(String tradeNumber) {
        this.tradeNumber = tradeNumber;
        return this;
    }

    public Long getId() {
        return id;
    }

    public StockEvaluation setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getStockId() {
        return stockId;
    }

    public StockEvaluation setStockId(Long stockId) {
        this.stockId = stockId;
        return this;
    }

    public Long getMemberId() {
        return memberId;
    }

    public StockEvaluation setMemberId(Long memberId) {
        this.memberId = memberId;
        return this;
    }

    public String getStockType() {
        return stockType;
    }

    public StockEvaluation setStockType(String type) {
        this.stockType = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsDisplay() {
        return isDisplay;
    }

    public StockEvaluation setIsDisplay(Integer isDisplay) {
        this.isDisplay = isDisplay;
        return this;
    }

    public Integer getIsStick() {
        return isStick;
    }

    public StockEvaluation setIsStick(Integer isStick) {
        this.isStick = isStick;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public StockEvaluation setStatus(String status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public StockEvaluation setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getStockTag() {
        return stockTag;
    }

    public void setStockTag(String stockTag) {
        this.stockTag = stockTag;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public static final String ID = "id";

    public static final String STOCK_ID = "stock_id";

    public static final String MEMBER_ID = "member_id";

    public static final String MEMBER_NAME = "member_name";

    public static final String STOCK_TYPE = "stock_type";

    public static final String STOCK_TAG = "stock_tag";

    public static final String CONTENT = "content";

    public static final String IS_DISPLAY = "is_display";

    public static final String IS_STICK = "is_stick";

    public static final String STATUS = "status";

    public static final String CREATE_TIME = "create_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StockEvaluation{" +
                "id=" + id +
                ", stockId=" + stockId +
                ", memberId=" + memberId +
                ", memberName=" + memberName +
                ", stockType=" + stockType +
                ", stockTag=" + stockTag +
                ", content=" + content +
                ", isDisplay=" + isDisplay +
                ", isStick=" + isStick +
                ", status=" + status +
                ", createTime=" + createTime +
                "}";
    }
}
