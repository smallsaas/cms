package com.jfeat.am.module.meta.services.gen.persistence.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 工作流记录表
 * </p>
 *
 * @author Code Generator
 * @since 2019-01-29
 */
@TableName("meta_workflow_lite_activity")
public class MetaWorkflowLiteActivity extends Model<MetaWorkflowLiteActivity> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 实体
     */
    private String entity;
    /**
     * 实体id
     */
    @TableField("entity_id")
    private Long entityId;
    /**
     * 意见
     */
    private String note;
    /**
     * 原状态
     */
    @TableField("from_status")
    private String fromStatus;
    /**
     * 下一个状态
     */
    @TableField("to_status")
    private String toStatus;
    /**
     * 创建者id
     */
    @TableField("created_by_id")
    private Long createdById;
    /**
     * 创建者名称
     */
    @TableField("created_by")
    private String createdBy;
    /**
     * 创建时间
     */
    @TableField("created_time")
    private Date createdTime;
    /**
     * 保留字段1
     */
    private String field1;
    /**
     * 保留字段2
     */
    private String field2;


    public Long getId() {
        return id;
    }

    public MetaWorkflowLiteActivity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEntity() {
        return entity;
    }

    public MetaWorkflowLiteActivity setEntity(String entity) {
        this.entity = entity;
        return this;
    }

    public Long getEntityId() {
        return entityId;
    }

    public MetaWorkflowLiteActivity setEntityId(Long entityId) {
        this.entityId = entityId;
        return this;
    }

    public String getNote() {
        return note;
    }

    public MetaWorkflowLiteActivity setNote(String note) {
        this.note = note;
        return this;
    }

    public String getFromStatus() {
        return fromStatus;
    }

    public MetaWorkflowLiteActivity setFromStatus(String fromStatus) {
        this.fromStatus = fromStatus;
        return this;
    }

    public String getToStatus() {
        return toStatus;
    }

    public MetaWorkflowLiteActivity setToStatus(String toStatus) {
        this.toStatus = toStatus;
        return this;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public MetaWorkflowLiteActivity setCreatedById(Long createdById) {
        this.createdById = createdById;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public MetaWorkflowLiteActivity setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public MetaWorkflowLiteActivity setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getField1() {
        return field1;
    }

    public MetaWorkflowLiteActivity setField1(String field1) {
        this.field1 = field1;
        return this;
    }

    public String getField2() {
        return field2;
    }

    public MetaWorkflowLiteActivity setField2(String field2) {
        this.field2 = field2;
        return this;
    }

    public static final String ID = "id";

    public static final String ENTITY = "entity";

    public static final String ENTITY_ID = "entity_id";

    public static final String NOTE = "note";

    public static final String FROM_STATUS = "from_status";

    public static final String TO_STATUS = "to_status";

    public static final String CREATED_BY_ID = "created_by_id";

    public static final String CREATED_BY = "created_by";

    public static final String CREATED_TIME = "created_time";

    public static final String FIELD1 = "field1";

    public static final String FIELD2 = "field2";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MetaWorkflowLiteActivity{" +
                "id=" + id +
                ", entity=" + entity +
                ", entityId=" + entityId +
                ", note=" + note +
                ", fromStatus=" + fromStatus +
                ", toStatus=" + toStatus +
                ", createdById=" + createdById +
                ", createdBy=" + createdBy +
                ", createdTime=" + createdTime +
                ", field1=" + field1 +
                ", field2=" + field2 +
                "}";
    }
}
