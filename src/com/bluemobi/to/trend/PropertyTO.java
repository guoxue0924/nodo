package com.bluemobi.to.trend;

import java.util.List;

import com.appcore.model.AbstractObject;
import com.bluemobi.po.trend.TrendPropertyValue;

/**
 * 属性和属性值TO，用于商品新增或修改时向页面传递属性对象
 * 
 * @author zhangzheng
 * @date 2015-11-18
 * 
 */
public class PropertyTO extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Integer propertyId;
    // 参数名
    private String labelName;
    // 是否是图片属性，1：是；0：否。
    private Byte isImage;
    // 该属性下的属性值集合
    private List<TrendPropertyValue> propertyValues;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Byte getIsImage() {
        return isImage;
    }

    public void setIsImage(Byte isImage) {
        this.isImage = isImage;
    }

    public List<TrendPropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<TrendPropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

}
