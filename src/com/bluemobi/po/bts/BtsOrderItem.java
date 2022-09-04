package com.bluemobi.po.bts;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【订单的商品表(即订单详情表)】持久化对象 数据库表：bts_order_items
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 13:27:05
 * 
 */
public class BtsOrderItem extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键ID
    private Long itemId;
    // 订单 ID
    private Long orderId;
    // 商品ID
    private Long skuId;
    // 商品名称
    private String goodsName;
    // 商品图片
    private String goodsImage;
    // 重量
    private String goodsWeight;
    // 长
    private String goodsLength;
    // 宽
    private String goodsWide;
    // 高
    private String goodsHeight;
    // 商品详细信息（以 json 格式进行存储）
    private String description;
    // 购买价格
    private BigDecimal buyPrice;
    // 购买数量
    private Short buyNum;
    // 是否已经评价 0.否 1.是 
    private Byte isComment = 0;
    // 是否需要包装。1：是；0：否；
    private Byte isPackage = 0;
    // 
    private Date ctime;

    /** 获取 主键ID 属性 */
    public Long getItemId() {
        return itemId;
    }

    /** 设置 主键ID 属性 */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /** 获取 订单 ID 属性 */
    public Long getOrderId() {
        return orderId;
    }

    /** 设置 订单 ID 属性 */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /** 获取 商品ID 属性 */
    public Long getSkuId() {
        return skuId;
    }

    /** 设置 商品ID 属性 */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    /** 获取 商品名称 属性 */
    public String getGoodsName() {
        return goodsName;
    }

    /** 设置 商品名称 属性 */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /** 获取 商品图片 属性 */
    public String getGoodsImage() {
        return goodsImage;
    }

    /** 设置 商品图片 属性 */
    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    /** 获取 重量 属性 */
    public String getGoodsWeight() {
        return goodsWeight;
    }

    /** 设置 重量 属性 */
    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    /** 获取 长 属性 */
    public String getGoodsLength() {
        return goodsLength;
    }

    /** 设置 长 属性 */
    public void setGoodsLength(String goodsLength) {
        this.goodsLength = goodsLength;
    }

    /** 获取 宽 属性 */
    public String getGoodsWide() {
        return goodsWide;
    }

    /** 设置 宽 属性 */
    public void setGoodsWide(String goodsWide) {
        this.goodsWide = goodsWide;
    }

    /** 获取 高 属性 */
    public String getGoodsHeight() {
        return goodsHeight;
    }

    /** 设置 高 属性 */
    public void setGoodsHeight(String goodsHeight) {
        this.goodsHeight = goodsHeight;
    }

    /** 获取 商品详细信息（以 json 格式进行存储） 属性 */
    public String getDescription() {
        return description;
    }

    /** 设置 商品详细信息（以 json 格式进行存储） 属性 */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 获取 购买价格 属性 */
    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    /** 设置 购买价格 属性 */
    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    /** 获取 购买数量 属性 */
    public Short getBuyNum() {
        return buyNum;
    }

    /** 设置 购买数量 属性 */
    public void setBuyNum(Short buyNum) {
        this.buyNum = buyNum;
    }

    /** 获取 是否已经评价 0.否 1.是  属性 */
    public Byte getIsComment() {
        return isComment;
    }

    /** 设置 是否已经评价 0.否 1.是  属性 */
    public void setIsComment(Byte isComment) {
        this.isComment = isComment;
    }

    /** 获取 是否需要包装。1：是；0：否； 属性 */
    public Byte getIsPackage() {
        return isPackage;
    }

    /** 设置 是否需要包装。1：是；0：否； 属性 */
    public void setIsPackage(Byte isPackage) {
        this.isPackage = isPackage;
    }

    /** 获取  属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置  属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BtsOrderItems");
        sb.append("{itemId=").append(itemId);
        sb.append(", orderId=").append(orderId);
        sb.append(", skuId=").append(skuId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsImage=").append(goodsImage);
        sb.append(", goodsWeight=").append(goodsWeight);
        sb.append(", goodsLength=").append(goodsLength);
        sb.append(", goodsWide=").append(goodsWide);
        sb.append(", goodsHeight=").append(goodsHeight);
        sb.append(", description=").append(description);
        sb.append(", buyPrice=").append(buyPrice);
        sb.append(", buyNum=").append(buyNum);
        sb.append(", isComment=").append(isComment);
        sb.append(", isPackage=").append(isPackage);
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BtsOrderItem) {
            BtsOrderItem btsOrderItems = (BtsOrderItem) obj;
            if (this.getItemId().equals(btsOrderItems.getItemId()) && this.getOrderId().equals(btsOrderItems.getOrderId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getItemId() + this.getOrderId();
        return pkStr.hashCode();
    }

}