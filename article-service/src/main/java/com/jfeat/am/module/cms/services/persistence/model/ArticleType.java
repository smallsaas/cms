package com.jfeat.am.module.cms.services.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admin
 * @since 2018-03-27
 */
@TableName("article_type")
public class ArticleType extends Model<ArticleType> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value="id", type= IdType.AUTO)
    private Long id;
    /**
     * 类别id
     */
    private Long identifier;
    /**
     * 类别名称
     */
    private String name;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final String ID = "id";

    public static final String IDENTIFIER = "identifier";

    public static final String NAME = "name";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "ArticleType{" +
        ", id=" + id +
        ", identifier=" + identifier +
        ", name=" + name +
        "}";
    }
}
