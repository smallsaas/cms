package com.jfeat.am.module.evaluation.services.domain.model.record;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluation;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationAddition;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationImage;
import com.jfeat.am.module.evaluation.services.persistence.model.StockEvaluationStar;

import java.util.Date;
import java.util.List;

/**
 * Created by Code Generator on 2018-07-16
 */
@JsonIgnoreProperties(value = {"handler","hibernateLazyInitializer","fieldHandler"})
public class StockEvaluationRecord extends StockEvaluation{

    private List<StockEvaluationAddition> stockEvaluationAdditions;
    private List<StockEvaluationImage> images;
    private List<StockEvaluationStar> stockEvaluationStars;
    private List<StockEvaluationRecord> replys;
    private Integer isDeleteAllow;
    private Integer starValue;
    /**
     * 评论回复的回复对象名
     * */
    private String commentName;
    private Integer isReply;
    private Date replyTime;

    private String userName;

    private String avatar;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<StockEvaluationRecord> getReplys() {
        return replys;
    }

    public void setReplys(List<StockEvaluationRecord> replys) {
        this.replys = replys;
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public Integer getIsReply() {
        return isReply;
    }

    public void setIsReply(Integer isReply) {
        this.isReply = isReply;
    }

    public List<StockEvaluationAddition> getStockEvaluationAdditions() {
        return stockEvaluationAdditions;
    }

    public void setStockEvaluationAdditions(List<StockEvaluationAddition> stockEvaluationAdditions) {
        this.stockEvaluationAdditions = stockEvaluationAdditions;
    }

    public List<StockEvaluationImage> getImages() {
        return images;
    }

    public void setImages(List<StockEvaluationImage> images) {
        this.images = images;
    }

    public List<StockEvaluationStar> getStockEvaluationStars() {
        return stockEvaluationStars;
    }

    public void setStockEvaluationStars(List<StockEvaluationStar> stockEvaluationStars) {
        this.stockEvaluationStars = stockEvaluationStars;
    }

    public Integer getStarValue() {
        return starValue;
    }

    public void setStarValue(Integer starValue) {
        this.starValue = starValue;
    }

    public Integer getIsDeleteAllow() {
        return isDeleteAllow;
    }

    public void setIsDeleteAllow(Integer isDeleteAllow) {
        this.isDeleteAllow = isDeleteAllow;
    }
}
