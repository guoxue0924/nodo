/**
 * Project Name:nodo 
 * File Name:OrderQueryTO.java 
 * Package Name:com.bluemobi.to.bts 
 * Date:2015年12月28日下午4:50:13 
 */
package com.bluemobi.to.bts;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * ClassName: OrderQueryTO Date: 2015年12月28日下午4:50:13
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
public class OrderQueryTO extends AbstractObject {

	private static final long serialVersionUID = -2815924887338574575L;

	/**
	 * 订单类型
	 */
	private Integer orderType;

	/**
	 * 订单开始日期
	 */
	private Date startDate;

	/**
	 * 订单结束日期
	 */
	private Date endDate;

	/**
	 * 订单状态
	 */
	private Integer orderStatus;

	/**
	 * 支付状态
	 */
	private Integer paymentStatus;

	/**
	 * 搜索关键字
	 */
	private String key;

	/**
	 * 每页显示数量
	 */
	private Integer pageSize;

	/**
	 * 页码
	 */
	private Integer pageIndex;
	
	/**
	 * 是否用户删除
	 */
	private Integer isUserDel;

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getIsUserDel() {
		return isUserDel;
	}

	public void setIsUserDel(Integer isUserDel) {
		this.isUserDel = isUserDel;
	}

}
