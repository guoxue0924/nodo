package com.bluemobi.to.goods;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 商品和商品sku的
 * 
 * @author zhangzheng
 * @date 2015-11-19
 * 
 */
public class GoodsContentAndSkuTO extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Long contentId;
    // 所属平台的商品分类 ID
    private Long categoryId;
    // 品牌 ID
    private Integer brandId;
    // 商品发布者的用户 ID。对应表：admin_user
    private Integer userid;
    // 商品名称
    private String name;
    // 商品简介
    private String memo;
    // 商品详情
    private String body;
    // 页面标题
    private String metaTitle;
    // 页面关键词
    private String metaKeywords;
    // 页面描述
    private String metaDescription;
    // 序号。值越大，排位越靠前（不同商品间的排序）。
    private Integer sortOrder;
    // 关联商品 ID，多个以半角逗号分隔
    private String related;
    // 是否开启规格，0不开启，1开启
    private Byte isSpec;
    // 是否标记为删除。1：是；0：否；
    private Byte isDel;
    // 是否上架销售。1：是；0：否；
    private Byte isShelf;
    // 上架时间
    private Date onSaleTime;
    // 下架时间
    private Date offSaleTime;
    // 创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;

    private Long skuId;
    private String sku;
    // 库存
    private Integer stock;
    // 重量。单位：克
    private String weight;
    // 长。单位：毫米（mm）
    private String length;
    // 宽。单位：毫米（mm）
    private String wide;
    // 高。单位：毫米（mm）
    private String height;
    // 销售价格
    private BigDecimal price;
    // 市场价格，即参考价格
    private BigDecimal priceMarket;
    // 积分价格
    private BigDecimal priceIntegral;
    // 成本价格。只在后台查看，前台不显示
    private BigDecimal priceCost;
    // 用户保存sku和属性、属性值的关联，数据结构：“属性id_属性值id，属性id_属性值id”，如：“1_12,5_32”。
    private String property;
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
    private Integer skuSortOrder;
    // 商品图片 ID，多个以半角逗号分隔
    private String attachmentids;
    // 是否上架销售。1：是；0：否；
    private Byte skuIsShelf;
    // 是否标记为删除。1：是；0：否；
    private Byte skuIsDel;
    // 创建时间
    private Date skuCtime;
    // 最后一次更新时间
    private Date skuMtime;

    // 默认图片
    private String filepath;

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public Byte getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(Byte isSpec) {
        this.isSpec = isSpec;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getOnSaleTime() {
        return onSaleTime;
    }

    public void setOnSaleTime(Date onSaleTime) {
        this.onSaleTime = onSaleTime;
    }

    public Date getOffSaleTime() {
        return offSaleTime;
    }

    public void setOffSaleTime(Date offSaleTime) {
        this.offSaleTime = offSaleTime;
    }

    public Byte getIsShelf() {
        return isShelf;
    }

    public void setIsShelf(Byte isShelf) {
        this.isShelf = isShelf;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceMarket() {
        return priceMarket;
    }

    public void setPriceMarket(BigDecimal priceMarket) {
        this.priceMarket = priceMarket;
    }

    /**
     * 获取积分价格
     */
    public BigDecimal getPriceIntegral() {
		return priceIntegral;
	}
    
    /**
     * 设置积分价格
     */
	public void setPriceIntegral(BigDecimal priceIntegral) {
		this.priceIntegral = priceIntegral;
	}

	public BigDecimal getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(BigDecimal priceCost) {
        this.priceCost = priceCost;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
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

    public Integer getSkuSortOrder() {
        return skuSortOrder;
    }

    public void setSkuSortOrder(Integer skuSortOrder) {
        this.skuSortOrder = skuSortOrder;
    }

    public String getAttachmentids() {
        return attachmentids;
    }

    public void setAttachmentids(String attachmentids) {
        this.attachmentids = attachmentids;
    }

    public Byte getSkuIsShelf() {
        return skuIsShelf;
    }

    public void setSkuIsShelf(Byte skuIsShelf) {
        this.skuIsShelf = skuIsShelf;
    }

    public Byte getSkuIsDel() {
        return skuIsDel;
    }

    public void setSkuIsDel(Byte skuIsDel) {
        this.skuIsDel = skuIsDel;
    }

    public Date getSkuCtime() {
        return skuCtime;
    }

    public void setSkuCtime(Date skuCtime) {
        this.skuCtime = skuCtime;
    }

    public Date getSkuMtime() {
        return skuMtime;
    }

    public void setSkuMtime(Date skuMtime) {
        this.skuMtime = skuMtime;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

}
