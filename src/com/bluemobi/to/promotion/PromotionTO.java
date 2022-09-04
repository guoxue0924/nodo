/**
 * Project Name:nodo 
 * File Name:PromotionTO.java 
 * Package Name:com.bluemobi.to.promotion 
 * Date:2016年1月13日上午11:12:49 
 */
package com.bluemobi.to.promotion;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * ClassName: PromotionTO Date: 2016年1月13日上午11:12:49
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
public class PromotionTO extends AbstractObject {

	private static final long serialVersionUID = 3963312646757347485L;

	private int promotionId;

	private String title;

	private String categoryTitle;

	private Date startTime;

	private Date endTime;

	private byte status;

	private Date ctime;

	public int getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(int promotionId) {
		this.promotionId = promotionId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
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

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

}
