package com.bluemobi.to.goods;

import java.math.BigDecimal;
import java.util.List;

import com.appcore.model.AbstractObject;
import com.bluemobi.to.trend.TrendPropertyValueTO;

/**
 * 商品sku的TO对象
 * 
 * @author zhangzheng
 * @date 2015-11-18
 * 
 */
public class GoodsContentSkuTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    //
    private Long skuId;
    private String sku;
    // 主商品id
    private Long contentId;
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
    //积分价格
    private Integer priceIntegral;
    // 市场价格，即参考价格
    private BigDecimal priceMarket;
    // 成本价格。只在后台查看，前台不显示
    private BigDecimal priceCost;
    // sku对应的属性值，生成表格时使用
    private List<TrendPropertyValueTO> propertyValueTos;

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

    public List<TrendPropertyValueTO> getPropertyValueTos() {
        return propertyValueTos;
    }

    public void setPropertyValueTos(List<TrendPropertyValueTO> propertyValueTos) {
        this.propertyValueTos = propertyValueTos;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

	public Integer getPriceIntegral() {
		return priceIntegral;
	}

	public void setPriceIntegral(Integer priceIntegral) {
		this.priceIntegral = priceIntegral;
	}
    
    
}
