package com.jfeat.am.module.ff.services.persistence.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Code Generator
 * @since 2018-07-16
 */
@TableName("t_stock_flower")
public class StockFlower extends Model<StockFlower> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 用户ID
     */
	@TableField("member_id")
	private Long memberId;
    /**
     * 点赞用户
     */
	@TableField("member_name")
	private String memberName;
    /**
     * 点赞对象ID
     */
	@TableField("stock_id")
	private Long stockId;
    /**
     * 点赞对象对象
     */
	@TableField("stock_name")
	private String stockName;
    /**
     * 点赞对象类型
     */
	@TableField("stock_type")
	private String stockType;
    /**
     * 点赞次数
     */
	@TableField("flower_count")
	private Integer flowerCount;
    /**
     * 点赞时间
     */
	@TableField("create_time")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public StockFlower setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getMemberId() {
		return memberId;
	}

	public StockFlower setMemberId(Long memberId) {
		this.memberId = memberId;
		return this;
	}

	public String getMemberName() {
		return memberName;
	}

	public StockFlower setMemberName(String memberName) {
		this.memberName = memberName;
		return this;
	}

	public Long getStockId() {
		return stockId;
	}

	public StockFlower setStockId(Long stockId) {
		this.stockId = stockId;
		return this;
	}

	public String getStockName() {
		return stockName;
	}

	public StockFlower setStockName(String stockName) {
		this.stockName = stockName;
		return this;
	}

	public String getStockType() {
		return stockType;
	}

	public StockFlower setStockType(String stockType) {
		this.stockType = stockType;
		return this;
	}

	public Integer getFlowerCount() {
		return flowerCount;
	}

	public StockFlower setFlowerCount(Integer flowerCount) {
		this.flowerCount = flowerCount;
		return this;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public StockFlower setCreateTime(Date createTime) {
		this.createTime = createTime;
		return this;
	}

	public static final String ID = "id";

	public static final String MEMBER_ID = "member_id";

	public static final String MEMBER_NAME = "member_name";

	public static final String STOCK_ID = "stock_id";

	public static final String STOCK_NAME = "stock_name";

	public static final String STOCK_TYPE = "stock_type";

	public static final String FLOWER_COUNT = "flower_count";

	public static final String CREATE_TIME = "create_time";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "StockFlower{" +
			"id=" + id +
			", memberId=" + memberId +
			", memberName=" + memberName +
			", stockId=" + stockId +
			", stockName=" + stockName +
			", stockType=" + stockType +
			", flowerCount=" + flowerCount +
			", createTime=" + createTime +
			"}";
	}
}
