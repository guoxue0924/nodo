package com.bluemobi.to.coupon;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 优惠券查询对象
 * 
 * @ClassName CouponCategoryQueryTO
 * @author liuyt
 * @date 2015-10-28 下午3:50:52
 * @version
 */
public class CouponCategoryQueryTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // 优惠券状态
    private String status;

    // 优惠券类型
    private String type;

    // 是否使用积分
    private String isExchange;

    // 搜索关键字(优惠券名称,使用规则,使用详情模糊查询)
    private String key;

    // 发放开始时间
    private Date grantStartDate;

    // 发放结束时间
    private Date grantEndDate;

    // 有效开始时间
    private Date validStartDate;

    // 有效结束时间
    private Date validEndDate;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(String isExchange) {
        this.isExchange = isExchange;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getGrantStartDate() {
        return grantStartDate;
    }

    public void setGrantStartDate(Date grantStartDate) {
        this.grantStartDate = grantStartDate;
    }

    public Date getGrantEndDate() {
        return grantEndDate;
    }

    public void setGrantEndDate(Date grantEndDate) {
        this.grantEndDate = grantEndDate;
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

}
