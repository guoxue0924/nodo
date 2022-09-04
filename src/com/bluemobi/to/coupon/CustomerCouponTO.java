package com.bluemobi.to.coupon;

import java.util.Date;

import com.appcore.model.AbstractObject;

public class CustomerCouponTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // 主键id
    private Integer cpnsId;
    // 优惠券名称
    private String couponName;
    // 优惠金额
    private Integer faceValue;
    // 优惠券类型
    private String couponType;
    // 是否积分兑换
    private Byte isExchange;
    // 兑换所需积分
    private Integer couponPoint;
    // 使用规则
    private String rule;
    // 使用详情
    private String body;
    // 有效开始时间
    private Date validStartDate;
    // 有效结束时间
    private Date validEndDate;

    public Integer getCpnsId() {
        return cpnsId;
    }

    public void setCpnsId(Integer cpnsId) {
        this.cpnsId = cpnsId;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(Integer faceValue) {
        this.faceValue = faceValue;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getValidStartDate() {
        return validStartDate;
    }

    public void setValidStartDate(Date validStartDate) {
        this.validStartDate = validStartDate;
    }

    public Date getValidEndDate() {
        return validEndDate;
    }

    public void setValidEndDate(Date validEndDate) {
        this.validEndDate = validEndDate;
    }

    public Byte getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(Byte isExchange) {
        this.isExchange = isExchange;
    }

    public Integer getCouponPoint() {
        return couponPoint;
    }

    public void setCouponPoint(Integer couponPoint) {
        this.couponPoint = couponPoint;
    }

}
