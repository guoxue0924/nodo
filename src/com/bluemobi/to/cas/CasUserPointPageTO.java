/**
 * Project Name:nodo 
 * File Name:OrderQueryTO.java 
 * Package Name:com.bluemobi.to.bts 
 * Date:2015年12月28日下午4:50:13 
 */
package com.bluemobi.to.cas;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 
 * ClassName: RefundPageTO Date: 2016年1月27日下午2:33:10
 * 
 * @author kevin
 * @version
 * @since JDK 7
 */
public class CasUserPointPageTO extends AbstractObject {


	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 积分ID
    private Integer pointId;
    // 用户ID
    private Integer userid;
    // 积分类型（1：注册奖励；2：每日登录；3：每日签到; 4：积分消耗）
    private Integer pointType;
    // 积分名称
    private String pointName;
    // 积分
    private Integer point;
    // 创建时间
    private Date ctime;
    // 积分类型ID
    private Integer pointTypeId;
    // 过期时间
    private Date overdueTime;
    // 状态，0：未过期；1：已过期；
    private Integer status;
    // 已使用积分
    private Integer isUsed;
    // 使用时间
    private Date usedTime;
    //订单号
    private String orderNumber;

    public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
    
    private Integer totlePoint;
    
	public Integer getPointId() {
		return pointId;
	}
	public void setPointId(Integer pointId) {
		this.pointId = pointId;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getPointType() {
		return pointType;
	}
	public void setPointType(Integer pointType) {
		this.pointType = pointType;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public Integer getPoint() {
		return point;
	}
	public void setPoint(Integer point) {
		this.point = point;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Integer getPointTypeId() {
		return pointTypeId;
	}
	public void setPointTypeId(Integer pointTypeId) {
		this.pointTypeId = pointTypeId;
	}
	public Date getOverdueTime() {
		return overdueTime;
	}
	public void setOverdueTime(Date overdueTime) {
		this.overdueTime = overdueTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(Integer isUsed) {
		this.isUsed = isUsed;
	}
	public Date getUsedTime() {
		return usedTime;
	}
	public void setUsedTime(Date usedTime) {
}
	public Integer getTotlePoint() {
		return totlePoint;
	}
	public void setTotlePoint(Integer totlePoint) {
		this.totlePoint = totlePoint;
	}
}