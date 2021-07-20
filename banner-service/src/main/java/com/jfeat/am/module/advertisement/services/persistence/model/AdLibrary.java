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
 * @since 2018-12-13
 */
@TableName("t_ad_library")
public class AdLibrary extends Model<AdLibrary> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	private String url;


	public Long getId() {
		return id;
	}

	public AdLibrary setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public AdLibrary setUrl(String url) {
		this.url = url;
		return this;
	}

	public static final String ID = "id";

	public static final String URL = "url";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "AdLibrary{" +
			"id=" + id +
			", url=" + url +
			"}";
	}
}
