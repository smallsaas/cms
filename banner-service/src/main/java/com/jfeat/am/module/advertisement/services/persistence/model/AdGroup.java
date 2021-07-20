package com.jfeat.am.module.advertisement.services.persistence.model;

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
 * @author Code Generator
 * @since 2017-11-08
 */
@TableName("t_ad_group")
public class AdGroup extends Model<AdGroup> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String name;
	private String identifier;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	/**
     * 广告组标识
     */
/*
	private String identifier;
*/


	public Long getId() {
		return id;
	}

	public AdGroup setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public AdGroup setName(String name) {
		this.name = name;
		return this;
	}

	/*public String getIdentifier() {
		return identifier;
	}

	public AdGroup setIdentifier(String identifier) {
		this.identifier = identifier;
		return this;
	}*/

	public static final String ID = "id";

	public static final String NAME = "name";

	public static final String IDENTIFIER = "identifier";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AdGroup{" +
			"id=" + id +
			", name=" + name +

			"}";
	}
}
