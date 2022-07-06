package com.jfeat.module.feedback.services.gen.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;


@TableName("nft_wisp_order")
public class WispOrder extends Model<WispOrder> {
    /**
     * 订单ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "订单号")
    private Long id;
    /**
     * 精灵ID
     */
    @ApiModelProperty(value = "精灵ID")
    private Long wispId;
    /**
     * 精灵副本ID
     */
    @ApiModelProperty(value = "精灵副本ID")
    private Long wispInstanceId;
    /**
     * 挂售场次ID
     */
    @ApiModelProperty(value = "挂售场次ID")
    private Long marketingSessionId;
    /**
     * 卖家ID
     */
    @ApiModelProperty(value = "卖家ID")
    private Long sellerPlayId;
    /**
     * 买家ID
     */
    @ApiModelProperty(value = "买家ID")
    private Long buyerPlayId;
    /**
     * 预订记录ID
     */
    private Long bookRecordId;
    /**
     * 交易金额[GUGU令]
     */
    private Long transactionCoins;
    /**
     * 交易金额[人民币]
     */
    private Long transactionAmount;
/*    *//**
     * 订单状态
     *//*
    @ApiModelProperty(value = "订单状态")
    private WispOrderStatus status;

    *//**
     * 订单支付方式
     *//*
    @ApiModelProperty(value = "订单支付方式")
    private OrderPaymentMethod paymentMethod;*/


    /**
     * 支付凭证截图，图片地址
     */
    @ApiModelProperty(value = "支付凭证截图，图片地址")
    private String pictureUrl;
    /**
     * 订单记录时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDate recordDate;
    /**
     * 记录创建时间
     */
    @ApiModelProperty(value = "记录创建时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 记录更新时间
     */
    @ApiModelProperty(value = "记录更新时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 支付时间
     */
    @JsonFormat(timezone = "GMT+8" , pattern = "yyyy:MM:dd HH:mm:ss")
    private LocalDateTime paymentTime;


    @ApiModelProperty(value = "订单编号")
    private String orderNumber;
    private Long realtimePrice;
    private Long coinsPrice;

    private Integer profitReceived;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWispId() {
        return wispId;
    }

    public void setWispId(Long wispId) {
        this.wispId = wispId;
    }

    public Long getWispInstanceId() {
        return wispInstanceId;
    }

    public void setWispInstanceId(Long wispInstanceId) {
        this.wispInstanceId = wispInstanceId;
    }

    public Long getMarketingSessionId() {
        return marketingSessionId;
    }

    public void setMarketingSessionId(Long marketingSessionId) {
        this.marketingSessionId = marketingSessionId;
    }

    public Long getSellerPlayId() {
        return sellerPlayId;
    }

    public void setSellerPlayId(Long sellerPlayId) {
        this.sellerPlayId = sellerPlayId;
    }

    public Long getBuyerPlayId() {
        return buyerPlayId;
    }

    public void setBuyerPlayId(Long buyerPlayId) {
        this.buyerPlayId = buyerPlayId;
    }

    public Long getBookRecordId() {
        return bookRecordId;
    }

    public void setBookRecordId(Long bookRecordId) {
        this.bookRecordId = bookRecordId;
    }

    public Long getTransactionCoins() {
        return transactionCoins;
    }

    public void setTransactionCoins(Long transactionCoins) {
        this.transactionCoins = transactionCoins;
    }

    public Long getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Long transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public LocalDate getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(LocalDate recordDate) {
        this.recordDate = recordDate;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Long getRealtimePrice() {
        return realtimePrice;
    }

    public void setRealtimePrice(Long realtimePrice) {
        this.realtimePrice = realtimePrice;
    }

    public Long getCoinsPrice() {
        return coinsPrice;
    }

    public void setCoinsPrice(Long coinsPrice) {
        this.coinsPrice = coinsPrice;
    }

    public Integer getProfitReceived() {
        return profitReceived;
    }

    public void setProfitReceived(Integer profitReceived) {
        this.profitReceived = profitReceived;
    }
}
