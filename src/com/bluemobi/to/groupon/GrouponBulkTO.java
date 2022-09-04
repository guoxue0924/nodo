/**
 * Project Name:nodo 
 * File Name:GrouponBulkDetail.java 
 * Package Name:com.bluemobi.to.groupon 
 * Date:2016年1月5日上午11:21:03 
 */
package com.bluemobi.to.groupon;

import java.util.Date;
import java.util.List;

import com.appcore.model.AbstractObject;
import com.bluemobi.po.groupon.GrouponBulkCategory;

/**
 * 团购抢购的详细 ClassName: GrouponBulkDetail Date: 2016年1月5日上午11:21:03
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
public class GrouponBulkTO extends AbstractObject {

	private static final long serialVersionUID = -434133127238150240L;

	//
	private Integer bulkId;
	// 开始时间
	private Date startTime;
	// 结束时间
	private Date endTime;
	// 总库存
	private Integer inventorySum;
	// 当前库存
	private Integer inventory;
	// 价格
	private Float price;
	// 商品skuId
	private Long skuId;
	// 可否使用积分，0代表不可使用积分
	private Integer integral;
	// 抢购：一个用户只能购买N件商品并且只能提交一次成功订单，取消订单可以再次抢购
	private Integer buyNumber;

	private List<GrouponBulkCategory> categorys;

	private SkuTO sku;

	public Integer getBulkId() {
		return bulkId;
	}

	public void setBulkId(Integer bulkId) {
		this.bulkId = bulkId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getInventory() {
		return inventory;
	}

	public void setInventory(Integer inventory) {
		this.inventory = inventory;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public Integer getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}

	public List<GrouponBulkCategory> getCategorys() {
		return categorys;
	}

	public void setCategorys(List<GrouponBulkCategory> categorys) {
		this.categorys = categorys;
	}

	public SkuTO getSku() {
		return sku;
	}

	public void setSku(SkuTO sku) {
		this.sku = sku;
	}

	public Integer getInventorySum() {
		return inventorySum;
	}

	public void setInventorySum(Integer inventorySum) {
		this.inventorySum = inventorySum;
	}

}
