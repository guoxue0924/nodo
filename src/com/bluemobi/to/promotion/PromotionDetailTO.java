/**
 * Project Name:nodo 
 * File Name:PromotionDetailTO.java 
 * Package Name:com.bluemobi.to.promotion 
 * Date:2016年1月14日下午3:28:45 
 */
package com.bluemobi.to.promotion;

import java.util.Date;
import java.util.List;

import com.appcore.model.AbstractObject;
import com.bluemobi.po.promotion.PromotionCategory;
import com.bluemobi.to.goods.GoodsContentAndSkuTO;

/**
 * ClassName: PromotionDetailTO Date: 2016年1月14日下午3:28:45
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
public class PromotionDetailTO extends AbstractObject {

	private static final long serialVersionUID = 4101123325183460468L;

	//
	private Integer promotionId;
	// 分类 ID
	private Integer categoryId;
	// 活动标题
	private String title;
	// 序列化存储的活动规则具体数据
	private String rules;
	// 是否启用。1：是；0：否；
	private Byte status;
	// 是否标记为删除。1：是；0：否；
	private Byte isDel;
	// 活动开始时间
	private Date startTime;
	// 活动结束时间
	private Date endTime;

	private List<GoodsContentAndSkuTO> skuList;

	private PromotionCategory category;

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRules() {
		return rules;
	}

	public void setRules(String rules) {
		this.rules = rules;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Byte getIsDel() {
		return isDel;
	}

	public void setIsDel(Byte isDel) {
		this.isDel = isDel;
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

	public List<GoodsContentAndSkuTO> getSkuList() {
		return skuList;
	}

	public void setSkuList(List<GoodsContentAndSkuTO> skuList) {
		this.skuList = skuList;
	}

	public PromotionCategory getCategory() {
		return category;
	}

	public void setCategory(PromotionCategory category) {
		this.category = category;
	}

}
