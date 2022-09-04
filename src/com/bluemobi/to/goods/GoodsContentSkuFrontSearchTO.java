package com.bluemobi.to.goods;

import java.math.BigDecimal;

import com.appcore.model.AbstractObject;

public class GoodsContentSkuFrontSearchTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // SKU ID
    private Long skuId;
    // 销售价格
    private BigDecimal price;
    // 商品图片路径，搜索列表页面默认显示图片
    private String filepath;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

}
