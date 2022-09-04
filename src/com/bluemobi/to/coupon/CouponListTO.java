package com.bluemobi.to.coupon;

import java.util.Date;

import com.appcore.model.AbstractObject;

public class CouponListTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // 主键id
    private Integer cpnsId;
    // 优惠券名称
    private String couponName;
    // 优惠券状态。未使用：0；已使用：1；锁定：2；
    private Byte cpnsStatus;
    // 是否标记删除 0未删除 1已删除
    private Byte isDel;
    // 是否失效 1:失效 0:未失效
    private Byte disabled;
    // 用户名
    private String username;
    // 创建时间
    private Date ctime;
    // 更新时间
    private Date mtime;

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

    public Byte getCpnsStatus() {
        return cpnsStatus;
    }

    public void setCpnsStatus(Byte cpnsStatus) {
        this.cpnsStatus = cpnsStatus;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Byte getDisabled() {
        return disabled;
    }

    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

}
