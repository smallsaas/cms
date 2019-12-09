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
 * @since 2019-01-07
 */
@TableName("meta_entity_patch_machine")
public class MetaEntityPatchMachine extends Model<MetaEntityPatchMachine> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 实体
     */
    private String entity;
    /**
     * 实体对应表名
     */
    @TableField("entity_table_name")
    private String entityTableName;
    /**
     * 实体字段名
     */
    @TableField("entity_field_name")
    private String entityFieldName;
    /**
     * 实体字段类型
     */
    @TableField("entity_field_type")
    private String entityFieldType;
    /**
     * 数字类型字段的范围下限
     */
    @TableField("number_range_min")
    private Long numberRangeMin;
    /**
     * 数字类型字段的范围上限
     */
    @TableField("number_range_max")
    private Long numberRangeMax;
    /**
     * 操作权限控制
     */
    private String permission;


    public Long getId() {
        return id;
    }

    public MetaEntityPatchMachine setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEntity() {
        return entity;
    }

    public MetaEntityPatchMachine setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public String getEntityTableName() {
        return entityTableName;
    }

    public MetaEntityPatchMachine setEntityTableName(String entityTableName) {
        this.entityTableName = entityTableName;
        return this;
    }

    public String getEntityFieldName() {
        return entityFieldName;
    }

    public MetaEntityPatchMachine setEntityFieldName(String entityFieldName) {
        this.entityFieldName = entityFieldName;
        return this;
    }

    public String getEntityFieldType() {
        return entityFieldType;
    }

    public MetaEntityPatchMachine setEntityFieldType(String entityFieldType) {
        this.entityFieldType = entityFieldType;
        return this;
    }

    public Long getNumberRangeMin() {
        return numberRangeMin;
    }

    public MetaEntityPatchMachine setNumberRangeMin(Long numberRangeMin) {
        this.numberRangeMin = numberRangeMin;
        return this;
    }

    public Long getNumberRangeMax() {
        return numberRangeMax;
    }

    public MetaEntityPatchMachine setNumberRangeMax(Long numberRangeMax) {
        this.numberRangeMax = numberRangeMax;
        return this;
    }

    public String getPermission() {
        return permission;
    }

    public MetaEntityPatchMachine setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public static final String ID = "id";

    public static final String ENTITY = "entity";

    public static final String ENTITY_TABLE_NAME = "entity_table_name";

    public static final String ENTITY_FIELD_NAME = "entity_field_name";

    public static final String ENTITY_FIELD_TYPE = "entity_field_type";

    public static final String NUMBER_RANGE_MIN = "number_range_min";

    public static final String NUMBER_RANGE_MAX = "number_range_max";

    public static final String PERMISSION = "permission";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MetaEntityPatchMachine{" +
                "id=" + id +
                ", entity='" + entity + '\'' +
                ", entityTableName='" + entityTableName + '\'' +
                ", entityFieldName='" + entityFieldName + '\'' +
                ", entityFieldType='" + entityFieldType + '\'' +
                ", numberRangeMin=" + numberRangeMin +
                ", numberRangeMax=" + numberRangeMax +
                ", permission='" + permission + '\'' +
                '}';
    }
}
