package com.bluemobi.po.goods;

import com.appcore.model.AbstractObject;

/**
 * 【品牌与分类关系表】持久化对象 数据库表：goods_brand_category
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-22 15:18:57
 * 
 */
public class GoodsBrandCategory extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 品牌 ID。关联表：goods_brand
    private Integer brandId;
    // 商品分类 ID。关联表：goods_category
    private Integer categoryId;
    // 序号
    private Integer sortOrder;

    /** 获取 品牌 ID。关联表：goods_brand 属性 */
    public Integer getBrandId() {
        return brandId;
    }

    /** 设置 品牌 ID。关联表：goods_brand 属性 */
    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    /** 获取 商品分类 ID。关联表：goods_category 属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 商品分类 ID。关联表：goods_category 属性 */
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
        sb.append("GoodsBrandCategory");
        sb.append("{brandId=").append(brandId);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoodsBrandCategory) {
            GoodsBrandCategory goodsBrandCategory = (GoodsBrandCategory) obj;
            if (this.getBrandId().equals(goodsBrandCategory.getBrandId()) && this.getCategoryId().equals(goodsBrandCategory.getCategoryId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getBrandId() + this.getCategoryId();
        return pkStr.hashCode();
    }

}