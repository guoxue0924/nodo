package com.bluemobi.po.cas;

import java.math.BigDecimal;

import com.appcore.model.AbstractObject;

/**
 * 【用户积分规则表】持久化对象 数据库表：cas_user_point_rule
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-07 14:48:41
 * 
 */
public class CasUserPointRule extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer pointRoleId;
    // 积分类型（1：注册奖励；2：每日登录；3：每日签到）
    private Integer pointType;
    // 积分名称
    private String pointName;
    // 积分
    private Integer point;
    // 积分系数，用于实际获得的积分计算
    private BigDecimal pointCoefficient;

    /** 获取  属性 */
    public Integer getPointRoleId() {
        return pointRoleId;
    }

    /** 设置  属性 */
    public void setPointRoleId(Integer pointRoleId) {
        this.pointRoleId = pointRoleId;
    }

    /** 获取 积分类型（1：注册奖励；2：每日登录；3：每日签到） 属性 */
    public Integer getPointType() {
        return pointType;
    }

    /** 设置 积分类型（1：注册奖励；2：每日登录；3：每日签到） 属性 */
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

    /** 获取 积分系数，用于实际获得的积分计算 属性 */
    public BigDecimal getPointCoefficient() {
        return pointCoefficient;
    }

    /** 设置 积分系数，用于实际获得的积分计算 属性 */
    public void setPointCoefficient(BigDecimal pointCoefficient) {
        this.pointCoefficient = pointCoefficient;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CasUserPointRule");
        sb.append("{pointRoleId=").append(pointRoleId);
        sb.append(", pointType=").append(pointType);
        sb.append(", pointName=").append(pointName);
        sb.append(", point=").append(point);
        sb.append(", pointCoefficient=").append(pointCoefficient);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CasUserPointRule) {
            CasUserPointRule casUserPointRule = (CasUserPointRule) obj;
            if (this.getPointRoleId().equals(casUserPointRule.getPointRoleId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPointRoleId();
        return pkStr.hashCode();
    }

}