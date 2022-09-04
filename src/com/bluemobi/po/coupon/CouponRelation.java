package com.bluemobi.po.coupon;

import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：coupon_relation
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-27 14:36:20
 * 
 */
public class CouponRelation extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 优惠券id
    private Integer couponId;
    // 类型。0：全场满减；1： 商品分类；2：商户ID；
    private Byte relationType;
    // 关联内容ID
    private String relationContent;

    /** 获取 优惠券id 属性 */
    public Integer getCouponId() {
        return couponId;
    }

    /** 设置 优惠券id 属性 */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /** 获取 类型。0：全场满减；1： 商品分类；2：商户ID； 属性 */
    public Byte getRelationType() {
        return relationType;
    }

    /** 设置 类型。0：全场满减；1： 商品分类；2：商户ID； 属性 */
    public void setRelationType(Byte relationType) {
        this.relationType = relationType;
    }

    /** 获取 关联内容ID 属性 */
    public String getRelationContent() {
        return relationContent;
    }

    /** 设置 关联内容ID 属性 */
    public void setRelationContent(String relationContent) {
        this.relationContent = relationContent;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CouponRelation");
        sb.append("{couponId=").append(couponId);
        sb.append(", relationType=").append(relationType);
        sb.append(", relationContent=").append(relationContent);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CouponRelation) {
            CouponRelation couponRelation = (CouponRelation) obj;
            if (this.getCouponId().equals(couponRelation.getCouponId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCouponId();
        return pkStr.hashCode();
    }

}