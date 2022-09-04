package com.bluemobi.po.bts;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【购物车】持久化对象 数据库表：bts_cart_mark
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-30 11:39:12
 * 
 */
public class BtsCartMark extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 购物车记录ID
    private Integer cartMarkId;
    // 用户主键ID
    private Integer userid;
    // 0用户购物车ID
    private Integer cartId;
    // 修改购物车勾选信息的时间
    private Date mtime;

    /** 获取 购物车记录ID 属性 */
    public Integer getCartMarkId() {
        return cartMarkId;
    }

    /** 设置 购物车记录ID 属性 */
    public void setCartMarkId(Integer cartMarkId) {
        this.cartMarkId = cartMarkId;
    }

    /** 获取 用户主键ID 属性 */
    public Integer getUserid() {
        return userid;
    }

    /** 设置 用户主键ID 属性 */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /** 获取 0用户购物车ID 属性 */
    public Integer getCartId() {
        return cartId;
    }

    /** 设置 0用户购物车ID 属性 */
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    /** 获取 修改购物车勾选信息的时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 修改购物车勾选信息的时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BtsCartMark");
        sb.append("{cartMarkId=").append(cartMarkId);
        sb.append(", userid=").append(userid);
        sb.append(", cartId=").append(cartId);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BtsCartMark) {
            BtsCartMark btsCartMark = (BtsCartMark) obj;
            if (this.getCartMarkId().equals(btsCartMark.getCartMarkId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCartMarkId();
        return pkStr.hashCode();
    }

}