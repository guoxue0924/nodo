package com.bluemobi.to.goods;

import java.math.BigDecimal;

import com.appcore.model.AbstractObject;

public class GoodsContentTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private Long contentId;
    // 所属平台的商品分类 ID
    private Integer categoryId;
    // 品牌 ID
    private Integer brandId;
    // 商品名称
    private String name;
    // 商品简介
    private String memo;
    // 是否开启规格，0不开启，1开启
    private String isSpec;
    // 商品详情
    private String body;
    // 页面标题
    private String metaTitle;
    // 页面关键词
    private String metaKeywords;
    // 页面描述
    private String metaDescription;
    // 关联商品ID
    private String relatedContentId;
    // 序号。值越大，排位越靠前（不同商品间的排序）。
    private Integer sortOrder;
    // 关联商品 ID，多个以半角逗号分隔
    private String related;

    // skuID
    private Integer[] skuId;
    // 库存
    private Integer[] stock;
    // 重量。单位：克
    private String[] weight;
    // 长。单位：毫米（mm）
    private String[] length;
    // 宽。单位：毫米（mm）
    private String[] wide;
    // 高。单位：毫米（mm）
    private String[] height;
    // 销售价格
    private BigDecimal[] price;
    //积分价格
    private Integer[] priceIntegral;
    // 市场价格，即参考价格
    private BigDecimal[] priceMarket;
    // 成本价格。只在后台查看，前台不显示
    private BigDecimal[] priceCost;
    // 单件商品销量
    private Integer salesVolume;
    // 单件商品销售额
    private BigDecimal salesAmount;
    // 单件商品评分（平均值）
    private BigDecimal rankAverage;
    // 商品浏览数
    private Integer viewed;
    // 当前版本号
    private Long rev;
    // 序号。值越大，排位越靠前（同一商品不同属性的排序）。
    private Integer[] skuSortOrder;
    // 商品图片 ID，多个以半角逗号分隔
    private Integer[] attachmentid;
    // 是否上架销售。1：是；0：否；
    private Byte isShelf;

    /* 价格属性，最多可绑定三个 */
    // 第一个价格属性的id
    private Integer[] propertyId;
    // 第一个价格属性值的id
    private Integer[] propertyValueId;
    // 第二个价格属性的id
    private Integer[] propertyId2;
    // 第二个价格属性值的id
    private Integer[] propertyValueId2;
    // 第三个价格属性的id
    private Integer[] propertyId3;
    // 第三个价格属性值的id
    private Integer[] propertyValueId3;

    // 非价格属性
    private String[] property;

    // sku图片的属性值id
    private String[] skuImagePropertyValueIds;
    // sku图片的附件id
    private String[] skuImageAttachmentids;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(String isSpec) {
        this.isSpec = isSpec;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getRelatedContentId() {
        return relatedContentId;
    }

    public void setRelatedContentId(String relatedContentId) {
        this.relatedContentId = relatedContentId;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getRelated() {
        return related;
    }

    public void setRelated(String related) {
        this.related = related;
    }

    public Integer[] getStock() {
        return stock;
    }

    public void setStock(Integer[] stock) {
        this.stock = stock;
    }

    public String[] getWeight() {
        return weight;
    }

    public void setWeight(String[] weight) {
        this.weight = weight;
    }

    public String[] getLength() {
        return length;
    }

    public void setLength(String[] length) {
        this.length = length;
    }

    public String[] getWide() {
        return wide;
    }

    public void setWide(String[] wide) {
        this.wide = wide;
    }

    public String[] getHeight() {
        return height;
    }

    public void setHeight(String[] height) {
        this.height = height;
    }

    public BigDecimal[] getPrice() {
        return price;
    }

    public void setPrice(BigDecimal[] price) {
        this.price = price;
    }

    public BigDecimal[] getPriceMarket() {
        return priceMarket;
    }

    public void setPriceMarket(BigDecimal[] priceMarket) {
        this.priceMarket = priceMarket;
    }

    public BigDecimal[] getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(BigDecimal[] priceCost) {
        this.priceCost = priceCost;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public BigDecimal getSalesAmount() {
        return salesAmount;
    }

    public void setSalesAmount(BigDecimal salesAmount) {
        this.salesAmount = salesAmount;
    }

    public BigDecimal getRankAverage() {
        return rankAverage;
    }

    public void setRankAverage(BigDecimal rankAverage) {
        this.rankAverage = rankAverage;
    }

    public Integer getViewed() {
        return viewed;
    }

    public void setViewed(Integer viewed) {
        this.viewed = viewed;
    }

    public Long getRev() {
        return rev;
    }

    public void setRev(Long rev) {
        this.rev = rev;
    }

    public Integer[] getSkuSortOrder() {
        return skuSortOrder;
    }

    public void setSkuSortOrder(Integer[] skuSortOrder) {
        this.skuSortOrder = skuSortOrder;
    }

    public Integer[] getAttachmentid() {
        return attachmentid;
    }

    public void setAttachmentid(Integer[] attachmentid) {
        this.attachmentid = attachmentid;
    }

    public Byte getIsShelf() {
        return isShelf;
    }

    public void setIsShelf(Byte isShelf) {
        this.isShelf = isShelf;
    }

    public Integer[] getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer[] propertyId) {
        this.propertyId = propertyId;
    }

    public Integer[] getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(Integer[] propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    public Integer[] getPropertyId2() {
        return propertyId2;
    }

    public void setPropertyId2(Integer[] propertyId2) {
        this.propertyId2 = propertyId2;
    }

    public Integer[] getPropertyValueId2() {
        return propertyValueId2;
    }

    public void setPropertyValueId2(Integer[] propertyValueId2) {
        this.propertyValueId2 = propertyValueId2;
    }

    public Integer[] getPropertyId3() {
        return propertyId3;
    }

    public void setPropertyId3(Integer[] propertyId3) {
        this.propertyId3 = propertyId3;
    }

    public Integer[] getPropertyValueId3() {
        return propertyValueId3;
    }

    public void setPropertyValueId3(Integer[] propertyValueId3) {
        this.propertyValueId3 = propertyValueId3;
    }

    public String[] getProperty() {
        return property;
    }

    public void setProperty(String[] property) {
        this.property = property;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String[] getSkuImagePropertyValueIds() {
        return skuImagePropertyValueIds;
    }

    public void setSkuImagePropertyValueIds(String[] skuImagePropertyValueIds) {
        this.skuImagePropertyValueIds = skuImagePropertyValueIds;
    }

    public String[] getSkuImageAttachmentids() {
        return skuImageAttachmentids;
    }

    public void setSkuImageAttachmentids(String[] skuImageAttachmentids) {
        this.skuImageAttachmentids = skuImageAttachmentids;
    }

    public Integer[] getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer[] skuId) {
        this.skuId = skuId;
    }

	public Integer[] getPriceIntegral() {
		return priceIntegral;
	}

	public void setPriceIntegral(Integer[] priceIntegral) {
		this.priceIntegral = priceIntegral;
	}
    
    
}
