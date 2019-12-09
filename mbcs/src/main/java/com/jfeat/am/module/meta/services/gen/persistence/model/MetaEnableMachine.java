package com.jfeat.am.module.meta.services.gen.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author Code Generator
 * @since 2019-01-05
 */
@TableName("meta_enable_machine")
public class MetaEnableMachine extends Model<MetaEnableMachine> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 实体
     */
    private String entity;
    /**
     * 实体对应的表名
     */
    @TableField("entity_table_name")
    private String entityTableName;
    /**
     * 实体字段名
     */
    @TableField("entity_field_name")
    private String entityFieldName;
    /**
     * 选取范围下限
     */
    @TableField("range_min")
    private Integer rangeMin;
    /**
     * 选取范围上限
     */
    @TableField("range_max")
    private Integer rangeMax;
    /**
     * 有效位是否需要取反
     */
    private Integer negative;
    /**
     * 操作权限控制
     */
    private String permission;


    public Long getId() {
        return id;
    }

    public MetaEnableMachine setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEntity() {
        return entity;
    }

    public MetaEnableMachine setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public String getEntityTableName() {
        return entityTableName;
    }

    public MetaEnableMachine setEntityTableName(String entityTableName) {
        this.entityTableName = entityTableName;
        return this;
    }

    public String getEntityFieldName() {
        return entityFieldName;
    }

    public MetaEnableMachine setEntityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
        return this;
    }

    public Integer getRangeMin() {
        return rangeMin;
    }

    public MetaEnableMachine setRangeMin(Integer rangeMin) {
        this.rangeMin = rangeMin;
        return this;
    }

    public Integer getRangeMax() {
        return rangeMax;
    }

    public MetaEnableMachine setRangeMax(Integer rangeMax) {
        this.rangeMax = rangeMax;
        return this;
    }

    public Integer getNegative() {
        return negative;
    }

    public void setNegative(Integer negative) {
        this.negative = negative;
    }

    public String getPermission() {
        return permission;
    }

    public MetaEnableMachine setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public static final String ID = "id";

    public static final String ENTITY = "entity";

    public static final String ENTITY_TABLE_NAME = "entity_table_name";

    public static final String ENTITY_FIELD_NAME = "entity_field_name";

    public static final String RANGE_MIN = "range_min";

    public static final String RANGE_MAX = "range_max";

    public static final String NEGATIVE = "negative";

    public static final String PERMISSION = "permission";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MetaEnableMachine{" +
                "id=" + id +
                ", entity='" + entity + '\'' +
                ", entityTableName='" + entityTableName + '\'' +
                ", entityFieldName='" + entityFieldName + '\'' +
                ", rangeMin=" + rangeMin +
                ", rangeMax=" + rangeMax +
                ", negative=" + negative +
                ", permission='" + permission + '\'' +
                '}';
    }
}
