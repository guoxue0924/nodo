package com.bluemobi.to.bts;


public class SkuOrderRequestTO extends AbstractOrderRequestTO {

    private static final long serialVersionUID = 1L;

    private Integer skuId;

    private Integer quantity;

    public Integer getSkuId() {
        return skuId;
    }

    public void setSkuId(Integer skuId) {
        this.skuId = skuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
