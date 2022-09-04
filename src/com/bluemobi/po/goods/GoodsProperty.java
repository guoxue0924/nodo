package com.bluemobi.po.goods;


import com.appcore.model.AbstractObject;

/**
 * 【商品属性表，记录属性和属性值，用于搜索主商品】持久化对象 数据库表：goods_property
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-11-17 17:47:32
 * 
 */
public class GoodsProperty extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Long goodsPropertyId;
    // 所属商品 ID。对应表：goods_content
    private Long contentId;
    // 所属属性 ID。对应表：trend_property
    private Integer propertyId;
    // 属性值 ID。对应表：trend_property_value
    private Integer propertyValueId;

    /** 获取  属性 */
    public Long getGoodsPropertyId() {
        return goodsPropertyId;
    }

    /** 设置  属性 */
    public void setGoodsPropertyId(Long goodsPropertyId) {
        this.goodsPropertyId = goodsPropertyId;
    }

    /** 获取 所属商品 ID。对应表：goods_content 属性 */
    public Long getContentId() {
        return contentId;
    }

    /** 设置 所属商品 ID。对应表：goods_content 属性 */
    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    /** 获取 所属属性 ID。对应表：trend_property 属性 */
    public Integer getPropertyId() {
        return propertyId;
    }

    /** 设置 所属属性 ID。对应表：trend_property 属性 */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    /** 获取 属性值 ID。对应表：trend_property_value 属性 */
    public Integer getPropertyValueId() {
        return propertyValueId;
    }

    /** 设置 属性值 ID。对应表：trend_property_value 属性 */
    public void setPropertyValueId(Integer propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GoodsProperty");
        sb.append("{goodsPropertyId=").append(goodsPropertyId);
        sb.append(", contentId=").append(contentId);
        sb.append(", propertyId=").append(propertyId);
        sb.append(", propertyValueId=").append(propertyValueId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoodsProperty) {
            GoodsProperty goodsProperty = (GoodsProperty) obj;
            if (this.getGoodsPropertyId().equals(goodsProperty.getGoodsPropertyId()) && this.getContentId().equals(goodsProperty.getContentId()) && this.getPropertyId().equals(goodsProperty.getPropertyId()) && this.getPropertyValueId().equals(goodsProperty.getPropertyValueId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getGoodsPropertyId() + this.getContentId() + this.getPropertyId() + this.getPropertyValueId();
        return pkStr.hashCode();
    }

}