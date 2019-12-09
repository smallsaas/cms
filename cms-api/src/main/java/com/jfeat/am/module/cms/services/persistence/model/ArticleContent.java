package com.jfeat.am.module.cms.services.persistence.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-03-27
 */
@TableName("article_content")
public class ArticleContent extends Model<ArticleContent> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * 文章ID
     */
    @TableField("article_id")
    private Long articleId;
    /**
     * 内容
     */
    private String content;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static final String ID = "id";

    public static final String ARTICLE_ID = "article_id";

    public static final String CONTENT = "content";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ArticleContent{" +
        ", id=" + id +
        ", articleId=" + articleId +
        ", content=" + content +
        "}";
    }
}
