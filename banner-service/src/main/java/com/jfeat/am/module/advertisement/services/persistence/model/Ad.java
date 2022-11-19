package com.jfeat.am.module.advertisement.services.persistence.model;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2017-11-08
 */
@TableName("t_ad")
public class Ad extends Model<Ad> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
	@TableField("group_id")
	private Long groupId;
	private String name;
	private String image;
	private Integer enabled;
	@TableField("target_url")
	private String targetUrl;

	private String type;
	private String strategy;

	// url
	public String getUrl() {
		if(!StringUtils.isEmpty(image)){
			try {
				JSONArray images = JSON.parseArray(image);
				if(images!=null && images.size()>0){
					JSONObject img = images.getJSONObject(0);
					return img.getString("url");
				}

			}catch (Exception e){
			}
		}
		return image;
	}

	//排序号
	private Integer seq ;

	@TableField(exist = false)
	private String identifier;

	@TableField(exist = false)
	private Long orgId;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public Long getId() {
		return id;
	}

	public Ad setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getGroupId() {
		return groupId;
	}

	public Ad setGroupId(Long groupId) {
		this.groupId = groupId;
		return this;
	}

	public String getName() {
		return name;
	}

	public Ad setName(String name) {
		this.name = name;
		return this;
	}

	public String getImage() {
		return image;
	}

	public Ad setImage(String image) {
		this.image = image;
		return this;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public Ad setEnabled(Integer enabled) {
		this.enabled = enabled;
		return this;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public Ad setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
		return this;
	}

	public static final String ID = "id";

	public static final String GROUP_ID = "group_id";

	public static final String NAME = "name";

	public static final String IMAGE = "image";

	public static final String ENABLED = "enabled";

	public static final String TARGET_URL = "target_url";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Ad{" +
			"id=" + id +
			", groupId=" + groupId +
			", name=" + name +
			", image=" + image +
			", url=" + getUrl() +
			", enabled=" + enabled +
			", targetUrl=" + targetUrl +
			"}";
	}
}
