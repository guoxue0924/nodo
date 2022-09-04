package com.bluemobi.po.coupon;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【优惠券主表】持久化对象 数据库表：coupon_category
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-27 14:36:19
 * 
 */
public class CouponCategory extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 优惠券ＩＤ
    private Integer couponId;
    // 优惠券名称
    private String couponName;
    // 优惠券图片
    private String imageDefault;
    // 优惠券发行数量
    private Integer total;
    // 优惠券实际生成数量
    private Integer exchangedTotal;
    // 活动金额基础要求
    private BigDecimal basicPrice;
    // 每人限领
    private Integer eachlimit;
    // 优惠券面额
    private BigDecimal faceValue;
    // 优惠券状态。未启用：0；启用：1；锁定：2；
    private Byte status;
    // 优惠券类型。0：全场；1：满减；
    private Byte couponType;
    // 兑换所需积分
    private Integer couponPoint;
    // 失效,1:失效 0:未失效
    private Byte disabled;
    // 是否允许积分兑换 0 允许 1 不允许
    private Byte isExchange;
    // 使用规则
    private String rule;
    // 使用详情
    private String body;
    // 有效开始时间
    private Date validStime;
    // 有效结束时间
    private Date validEtime;
    // 发放开始时间
    private Date grantStime;
    // 发放结束时间
    private Date grantEtime;
    // 是否标记删除  0 未删除 1 已删除
    private Byte isDel;
    // 创建时间
    private Date ctime;
    // 修改时间
    private Date mtime;

    /** 获取 优惠券ＩＤ 属性 */
    public Integer getCouponId() {
        return couponId;
    }

    /** 设置 优惠券ＩＤ 属性 */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /** 获取 优惠券名称 属性 */
    public String getCouponName() {
        return couponName;
    }

    /** 设置 优惠券名称 属性 */
    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    /** 获取 用户券生成数量 属性 */
    public Integer getTotal() {
        return total;
    }

    /** 设置 用户券生成数量 属性 */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /** 获取 优惠券面额 属性 */
    public BigDecimal getFaceValue() {
        return faceValue;
    }

    /** 设置 优惠券面额 属性 */
    public void setFaceValue(BigDecimal faceValue) {
        this.faceValue = faceValue;
    }

    /** 获取 优惠券状态。未启用：0；启用：1；锁定：2； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 优惠券状态。未启用：0；启用：1；锁定：2； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 优惠券类型。0：全场；1：满减； 属性 */
    public Byte getCouponType() {
        return couponType;
    }

    /** 设置 优惠券类型。0：全场；1：满减； 属性 */
    public void setCouponType(Byte couponType) {
        this.couponType = couponType;
    }

    /** 获取 兑换所需积分 属性 */
    public Integer getCouponPoint() {
        return couponPoint;
    }

    /** 设置 兑换所需积分 属性 */
    public void setCouponPoint(Integer couponPoint) {
        this.couponPoint = couponPoint;
    }

    /** 获取 失效,1:失效 0:未失效 属性 */
    public Byte getDisabled() {
        return disabled;
    }

    /** 设置 失效,1:失效 0:未失效 属性 */
    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
    }

    /** 获取 是否允许积分兑换 0 允许 1 不允许 属性 */
    public Byte getIsExchange() {
        return isExchange;
    }

    /** 设置 是否允许积分兑换 0 允许 1 不允许 属性 */
    public void setIsExchange(Byte isExchange) {
        this.isExchange = isExchange;
    }

    /** 获取 使用规则 属性 */
    public String getRule() {
        return rule;
    }

    /** 设置 使用规则 属性 */
    public void setRule(String rule) {
        this.rule = rule;
    }

    /** 获取 使用详情 属性 */
    public String getBody() {
        return body;
    }

    /** 设置 使用详情 属性 */
    public void setBody(String body) {
        this.body = body;
    }

    /** 获取 有效开始时间 属性 */
    public Date getValidStime() {
        return validStime;
    }

    /** 设置 有效开始时间 属性 */
    public void setValidStime(Date validStime) {
        this.validStime = validStime;
    }

    /** 获取 有效结束时间 属性 */
    public Date getValidEtime() {
        return validEtime;
    }

    /** 设置 有效结束时间 属性 */
    public void setValidEtime(Date validEtime) {
        this.validEtime = validEtime;
    }

    /** 获取 发放开始时间 属性 */
    public Date getGrantStime() {
        return grantStime;
    }

    /** 设置 发放开始时间 属性 */
    public void setGrantStime(Date grantStime) {
        this.grantStime = grantStime;
    }

    /** 获取 发放结束时间 属性 */
    public Date getGrantEtime() {
        return grantEtime;
    }

    /** 设置 发放结束时间 属性 */
    public void setGrantEtime(Date grantEtime) {
        this.grantEtime = grantEtime;
    }

    /** 获取 是否标记删除  0 未删除 1 已删除 属性 */
    public Byte getIsDel() {
        return isDel;
    }

    /** 设置 是否标记删除  0 未删除 1 已删除 属性 */
    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 修改时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 修改时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }
    
    public String getImageDefault() {
		return imageDefault;
	}

	public void setImageDefault(String imageDefault) {
		this.imageDefault = imageDefault;
	}

	public Integer getExchangedTotal() {
		return exchangedTotal;
	}

	public void setExchangedTotal(Integer exchangedTotal) {
		this.exchangedTotal = exchangedTotal;
	}

	public BigDecimal getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(BigDecimal basicPrice) {
		this.basicPrice = basicPrice;
	}

	public Integer getEachlimit() {
		return eachlimit;
	}

	public void setEachlimit(Integer eachlimit) {
		this.eachlimit = eachlimit;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CouponCategory");
        sb.append("{couponId=").append(couponId);
        sb.append(", couponName=").append(couponName);
        sb.append(", total=").append(total);
        sb.append(", faceValue=").append(faceValue);
        sb.append(", status=").append(status);
        sb.append(", couponType=").append(couponType);
        sb.append(", couponPoint=").append(couponPoint);
        sb.append(", disabled=").append(disabled);
        sb.append(", isExchange=").append(isExchange);
        sb.append(", rule=").append(rule);
        sb.append(", body=").append(body);
        sb.append(", validStime=").append(validStime);
        sb.append(", validEtime=").append(validEtime);
        sb.append(", grantStime=").append(grantStime);
        sb.append(", grantEtime=").append(grantEtime);
        sb.append(", isDel=").append(isDel);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CouponCategory) {
            CouponCategory couponCategory = (CouponCategory) obj;
            if (this.getCouponId().equals(couponCategory.getCouponId())) {
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