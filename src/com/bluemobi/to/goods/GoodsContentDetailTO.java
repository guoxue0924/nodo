package com.bluemobi.to.goods;

import java.math.BigDecimal;
import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 商品详情TO
 * 
 * @author zhangzheng
 * @date 2015-10-23
 * 
 */
public class GoodsContentDetailTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // 主商品id
    private Long parentId;
    // 所属平台的商品分类 ID
    private String category;
    // 品牌 ID
    private Integer brandId;
    // 商品名称
    private String name;
    // 商品货号
    private String sku;
    // 销售价格
    private BigDecimal price;
    // 市场价格，即参考价格
    private BigDecimal priceMarket;
    // 库存
    private Integer stock;
    // 单件商品销量
    private Integer salesVolume;
    // 单件商品评分（平均值）
    private BigDecimal rankAverage;
    // 是否上架销售。1：是；0：否；
    private Byte isShelf;
    // 商品规格信息排列组合的序列化存储
    private String serializeSpecs;
    // 品牌名称
    private String brandName;
    // 商品分类名称
    private String categoryName;
    // 是否收藏，1：已收藏；0：未收藏
    private String isFavorite;
    // 创建时间
    private Date ctime;
    // 是否点赞，1：已点赞；0：未点赞
    private String isAgree;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSalesVolume() {
        return salesVolume;
    }

    public void setSalesVolume(Integer salesVolume) {
        this.salesVolume = salesVolume;
    }

    public BigDecimal getRankAverage() {
        return rankAverage;
    }

    public void setRankAverage(BigDecimal rankAverage) {
        this.rankAverage = rankAverage;
    }

    public Byte getIsShelf() {
        return isShelf;
    }

    public void setIsShelf(Byte isShelf) {
        this.isShelf = isShelf;
    }

    public String getSerializeSpecs() {
        return serializeSpecs;
    }

    public void setSerializeSpecs(String serializeSpecs) {
        this.serializeSpecs = serializeSpecs;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(String isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getIsAgree() {
        return isAgree;
    }

    public void setIsAgree(String isAgree) {
        this.isAgree = isAgree;
    }

}
