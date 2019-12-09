package com.jfeat.am.module.meta.services.gen.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-12-19
 */
@TableName("meta_status_machine")
public class MetaStatusMachine extends Model<MetaStatusMachine> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
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
	 * 操作权限控制
	 */
	private String permission;


	public Long getId() {
		return id;
	}

	public MetaStatusMachine setId(Long id) {
		this.id = id;
		return this;
	}

	public String getEntity() {
		return entity;
	}

	public MetaStatusMachine setEntity(String entity) {
		this.entity = entity;
		return this;
	}

	public String getEntityTableName() {
		return entityTableName;
	}

	public MetaStatusMachine setEntityTableName(String entityTableName) {
		this.entityTableName = entityTableName;
		return this;
	}

	public String getFromStatus() {
		return fromStatus;
	}

	public MetaStatusMachine setFromStatus(String fromStatus) {
		this.fromStatus = fromStatus;
		return this;
	}

	public String getToStatus() {
		return toStatus;
	}

	public MetaStatusMachine setToStatus(String toStatus) {
		this.toStatus = toStatus;
		return this;
	}

	public String getPermission() {
		return permission;
	}

	public MetaStatusMachine setPermission(String permission) {
		this.permission = permission;
		return this;
	}

	public static final String ID = "id";

	public static final String ENTITY = "entity";

	public static final String ENTITY_TABLE_NAME = "entity_table_name";

	public static final String ORIGINAL_STATUS = "original_status";

	public static final String CHANGING_STATUS = "changing_status";

	public static final String PERMISSION = "permission";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "MetaStatusMachine{" +
				"id=" + id +
				", entity='" + entity + '\'' +
				", entityTableName='" + entityTableName + '\'' +
				", fromStatus='" + fromStatus + '\'' +
				", toStatus='" + toStatus + '\'' +
				", permission='" + permission + '\'' +
				'}';
	}
}
