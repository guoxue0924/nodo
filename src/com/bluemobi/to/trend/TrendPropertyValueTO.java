package com.bluemobi.to.trend;

import com.appcore.model.AbstractObject;

/**
 * 属性值to，用于修改商品时传递sku绑定的属性值对象
 * 
 * @author zhangzheng
 * @date 2015-11-18
 * 
 */
public class TrendPropertyValueTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    //
    private Integer propertyValueId;
    // 对应的属性 ID
    private Integer propertyId;
    // 参数可选值
    private String propertyValue;

    public Integer getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(Integer propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

}
