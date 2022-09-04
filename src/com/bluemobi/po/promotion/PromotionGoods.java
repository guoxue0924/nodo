package com.bluemobi.po.promotion;


import com.appcore.model.AbstractObject;

/**
 * 【优惠促销活动所关联的商品表】持久化对象 数据库表：promotion_goods
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-08 16:25:23
 * 
 */
public class PromotionGoods extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer id;
    // 优惠促销活动 ID。对应表：promotion_content
    private Integer promotionId;
    // 关联商品的货号。对应表：goods_content_sku
    private Long skuId;

    /** 获取  属性 */
    public Integer getId() {
        return id;
    }

    /** 设置  属性 */
    public void setId(Integer id) {
        this.id = id;
    }

    /** 获取 优惠促销活动 ID。对应表：promotion_content 属性 */
    public Integer getPromotionId() {
        return promotionId;
    }

    /** 设置 优惠促销活动 ID。对应表：promotion_content 属性 */
    public void setPromotionId(Integer promotionId) {
        this.promotionId = promotionId;
    }

    /** 获取 关联商品的货号。对应表：goods_content_sku 属性 */
    public Long getSkuId() {
        return skuId;
    }

    /** 设置 关联商品的货号。对应表：goods_content_sku 属性 */
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("PromotionGoods");
        sb.append("{id=").append(id);
        sb.append(", promotionId=").append(promotionId);
        sb.append(", skuId=").append(skuId);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PromotionGoods) {
            PromotionGoods promotionGoods = (PromotionGoods) obj;
            if (this.getId().equals(promotionGoods.getId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getId();
        return pkStr.hashCode();
    }

}