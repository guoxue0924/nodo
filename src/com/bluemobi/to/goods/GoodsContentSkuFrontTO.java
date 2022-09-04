package com.bluemobi.to.goods;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.appcore.model.AbstractObject;

/**
 * 前台商品详情页面skuTO
 * 
 * @author zhangzheng
 * @date 2016-1-21
 * 
 */
public class GoodsContentSkuFrontTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    //
    private Long skuId;
    // 主商品id
    private Long contentId;
    // 商品货号
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
    private Integer sortOrder;
    // 商品图片 ID，多个以半角逗号分隔
    private String attachmentids;
    // 是否上架销售。1：是；0：否；
    private Byte isShelf;
    // 是否标记为删除。1：是；0：否；
    private Byte isDel;
    // 创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;
    // 属性ID
    private List<Integer> propertyIds;
    // 属性值ID
    private List<Integer> propertyValueIds;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Long getContentId() {
        return contentId;
    }

    public void setContentId(Long contentId) {
        this.contentId = contentId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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
        if (salesAmount == null) {
            salesAmount = new BigDecimal(0);
        }
        this.salesAmount = salesAmount;
    }

    public BigDecimal getRankAverage() {
        return rankAverage;
    }

    public void setRankAverage(BigDecimal rankAverage) {
        if (rankAverage == null) {
            rankAverage = new BigDecimal(0);
        }
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

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getAttachmentids() {
        return attachmentids;
    }

    public void setAttachmentids(String attachmentids) {
        this.attachmentids = attachmentids;
    }

    public Byte getIsShelf() {
        return isShelf;
    }

    public void setIsShelf(Byte isShelf) {
        this.isShelf = isShelf;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
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

    public List<Integer> getPropertyIds() {
        return propertyIds;
    }

    public void setPropertyIds(List<Integer> propertyIds) {
        this.propertyIds = propertyIds;
    }

    public List<Integer> getPropertyValueIds() {
        return propertyValueIds;
    }

    public void setPropertyValueIds(List<Integer> propertyValueIds) {
        this.propertyValueIds = propertyValueIds;
    }

}
