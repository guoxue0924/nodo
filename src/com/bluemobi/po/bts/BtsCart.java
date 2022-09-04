package com.bluemobi.po.bts;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【购物车】持久化对象 数据库表：bts_cart
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-20 10:36:35
 * 
 */
public class BtsCart extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 购物车ID
    private Integer cartId;
    // 商品加入购物车的时间
    private Date ctime;
    // 商品数量
    private Short quantity;
    // 商品ID
    private Long skuId;
    // 用户主键ID
    private Integer userid;

    /** 获取 购物车ID 属性 */
    public Integer getCartId() {
        return cartId;
    }

    /** 设置 购物车ID 属性 */
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    /** 获取 商品加入购物车的时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 商品加入购物车的时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 商品数量 属性 */
    public Short getQuantity() {
        return quantity;
    }

    /** 设置 商品数量 属性 */
    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    /** 获取 商品ID 属性 */
    public Long getSkuId() {
        return skuId;
    }

    /** 设置 商品ID 属性 */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /** 获取 用户主键ID 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 用户主键ID 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BtsCart");
        sb.append("{cartId=").append(cartId);
        sb.append(", ctime=").append(ctime);
        sb.append(", quantity=").append(quantity);
        sb.append(", skuId=").append(skuId);
        sb.append(", userid=").append(userid);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BtsCart) {
            BtsCart btsCart = (BtsCart) obj;
            if (this.getCartId().equals(btsCart.getCartId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCartId();
        return pkStr.hashCode();
    }

}