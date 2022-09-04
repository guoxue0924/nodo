package com.bluemobi.po.cas;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【用户积分表】持久化对象 数据库表：cas_user_point
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-08 18:08:04
 * 
 */
public class CasUserPoint extends AbstractObject {

    public static final long serialVersionUID = 1L;

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

	/** 获取 积分ID 属性 */
    public Integer getPointId() {
        return pointId;
    }

    /** 设置 积分ID 属性 */
    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    /** 获取 用户ID 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 用户ID 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 积分类型（1：注册奖励；2：每日登录；3：每日签到; 4：积分消耗） 属性 */
    public Integer getPointType() {
        return pointType;
    }

    /** 设置 积分类型（1：注册奖励；2：每日登录；3：每日签到; 4：积分消耗） 属性 */
    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    /** 获取 积分名称 属性 */
    public String getPointName() {
        return pointName;
    }

    /** 设置 积分名称 属性 */
    public void setPointName(String pointName) {
        this.pointName = pointName;
    }

    /** 获取 积分 属性 */
    public Integer getPoint() {
        return point;
    }

    /** 设置 积分 属性 */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 积分类型ID 属性 */
    public Integer getPointTypeId() {
        return pointTypeId;
    }

    /** 设置 积分类型ID 属性 */
    public void setPointTypeId(Integer pointTypeId) {
        this.pointTypeId = pointTypeId;
    }

    /** 获取 过期时间 属性 */
    public Date getOverdueTime() {
        return overdueTime;
    }

    /** 设置 过期时间 属性 */
    public void setOverdueTime(Date overdueTime) {
        this.overdueTime = overdueTime;
    }

    /** 获取 状态，0：未过期；1：已过期； 属性 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 状态，0：未过期；1：已过期； 属性 */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** 获取 已使用积分 属性 */
    public Integer getIsUsed() {
        return isUsed;
    }

    /** 设置 已使用积分 属性 */
    public void setIsUsed(Integer isUsed) {
        this.isUsed = isUsed;
    }

    /** 获取 使用时间 属性 */
    public Date getUsedTime() {
        return usedTime;
    }

    /** 设置 使用时间 属性 */
    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CasUserPoint");
        sb.append("{pointId=").append(pointId);
        sb.append(", userid=").append(userid);
        sb.append(", pointType=").append(pointType);
        sb.append(", pointName=").append(pointName);
        sb.append(", point=").append(point);
        sb.append(", ctime=").append(ctime);
        sb.append(", pointTypeId=").append(pointTypeId);
        sb.append(", overdueTime=").append(overdueTime);
        sb.append(", status=").append(status);
        sb.append(", isUsed=").append(isUsed);
        sb.append(", usedTime=").append(usedTime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CasUserPoint) {
            CasUserPoint casUserPoint = (CasUserPoint) obj;
            if (this.getPointId().equals(casUserPoint.getPointId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPointId();
        return pkStr.hashCode();
    }

}