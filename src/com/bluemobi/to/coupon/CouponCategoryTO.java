package com.bluemobi.to.coupon;

import com.appcore.model.AbstractObject;
import com.bluemobi.po.coupon.CouponCategory;
import com.bluemobi.po.coupon.CouponRelation;

public class CouponCategoryTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private CouponCategory coupon;

    private CouponRelation relation;

    public CouponCategory getCoupon() {
        return coupon;
    }

    public void setCoupon(CouponCategory coupon) {
        this.coupon = coupon;
    }

    public CouponRelation getRelation() {
        return relation;
    }

    public void setRelation(CouponRelation relation) {
        this.relation = relation;
    }
}
