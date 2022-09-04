package com.bluemobi.po.goods;

import com.appcore.model.AbstractObject;

/**
 * 【商品属性与分类关系表】持久化对象 数据库表：goods_property_category
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:57
 * 
 */
public class GoodsPropertyCategory extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 属性 ID。关联表：trend_property
    private Integer propertyId;
    // 属性分组 ID
    private Integer propertyGroupId;
    // 商品分类 ID
    private Integer categoryId;
    // 序号
    private Integer sortOrder;

    /** 获取 属性 ID。关联表：trend_property 属性 */
    public Integer getPropertyId() {
        return propertyId;
    }

    /** 设置 属性 ID。关联表：trend_property 属性 */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    /** 获取 属性分组 ID 属性 */
    public Integer getPropertyGroupId() {
        return propertyGroupId;
    }

    /** 设置 属性分组 ID 属性 */
    public void setPropertyGroupId(Integer propertyGroupId) {
        this.propertyGroupId = propertyGroupId;
    }

    /** 获取 商品分类 ID 属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 商品分类 ID 属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 序号 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GoodsPropertyCategory");
        sb.append("{propertyId=").append(propertyId);
        sb.append(", propertyGroupId=").append(propertyGroupId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoodsPropertyCategory) {
            GoodsPropertyCategory goodsPropertyCategory = (GoodsPropertyCategory) obj;
            if (this.getPropertyId().equals(goodsPropertyCategory.getPropertyId()) && this.getPropertyGroupId().equals(goodsPropertyCategory.getPropertyGroupId())
                    && this.getCategoryId().equals(goodsPropertyCategory.getCategoryId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPropertyId() + this.getPropertyGroupId() + this.getCategoryId();
        return pkStr.hashCode();
    }

}