package com.bluemobi.po.coupon;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：coupon_listing
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-27 14:36:20
 * 
 */
public class CouponListing extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键id
    private Integer cpnsId;
    // 优惠券id，关联表：coupon_category
    private Integer couponId;
    // 优惠券状态。未使用：0；已使用：1；锁定：2；
    private Byte cpnsStatus;
    // 是否标记删除 0未删除 1已删除
    private Byte isDel;
    // 是否失效 1:失效 0:未失效
    private Byte disabled;
    // 用户ID
    private Integer userid;
    // 创建时间
    private Date ctime;
    // 更新时间
    private Date mtime;

    /** 获取 主键id 属性 */
    public Integer getCpnsId() {
        return cpnsId;
    }

    /** 设置 主键id 属性 */
    public void setCpnsId(Integer cpnsId) {
        this.cpnsId = cpnsId;
    }

    /** 获取 优惠券id，关联表：coupon_category 属性 */
    public Integer getCouponId() {
        return couponId;
    }

    /** 设置 优惠券id，关联表：coupon_category 属性 */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /** 获取 优惠券状态。未使用：0；已使用：1；锁定：2； 属性 */
    public Byte getCpnsStatus() {
        return cpnsStatus;
    }

    /** 设置 优惠券状态。未使用：0；已使用：1；锁定：2； 属性 */
    public void setCpnsStatus(Byte cpnsStatus) {
        this.cpnsStatus = cpnsStatus;
    }

    /** 获取 是否标记删除 0未删除 1已删除 属性 */
    public Byte getIsDel() {
        return isDel;
    }

    /** 设置 是否标记删除 0未删除 1已删除 属性 */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    /** 获取 是否失效 1:失效 0:未失效 属性 */
    public Byte getDisabled() {
        return disabled;
    }

    /** 设置 是否失效 1:失效 0:未失效 属性 */
    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    /** 获取 用户ID 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 用户ID 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 更新时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 更新时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CouponListing");
        sb.append("{cpnsId=").append(cpnsId);
        sb.append(", couponId=").append(couponId);
        sb.append(", cpnsStatus=").append(cpnsStatus);
        sb.append(", isDel=").append(isDel);
        sb.append(", disabled=").append(disabled);
        sb.append(", userid=").append(userid);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CouponListing) {
            CouponListing couponListing = (CouponListing) obj;
            if (this.getCpnsId().equals(couponListing.getCpnsId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCpnsId();
        return pkStr.hashCode();
    }

}