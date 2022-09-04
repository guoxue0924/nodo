package com.bluemobi.po.trend;

import com.appcore.model.AbstractObject;
/**
 * 【属性资源值表】持久化对象
 * 数据库表：trend_property_value
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-30 10:42:29
 *
 */
public class TrendPropertyValue extends AbstractObject {
    
    public static final long serialVersionUID = 1L;
    
    //
    private Integer propertyValueId; 
    //对应的属性 ID
    private Integer propertyId; 
    //参数可选值
    private String propertyValue; 
    //规格图片
    private String propertyImage; 
    //序号
    private Integer sortOrder; 
    //是否设置为默认值。1：是；0：否；
    private Byte isDefault; 
    //(暂时不用)状态。1：启用；0：不启用；
    private Byte status; 

    /**获取  属性*/
    public Integer getPropertyValueId() {
        return propertyValueId;
    }
    
    /** 设置  属性*/
    public void setPropertyValueId(Integer propertyValueId) {
        this.propertyValueId = propertyValueId;
    }
    /**获取 对应的属性 ID 属性*/
    public Integer getPropertyId() {
        return propertyId;
    }
    
    /** 设置 对应的属性 ID 属性*/
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }
    /**获取 参数可选值 属性*/
    public String getPropertyValue() {
        return propertyValue;
    }
    
    /** 设置 参数可选值 属性*/
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
    /**获取 规格图片 属性*/
    public String getPropertyImage() {
        return propertyImage;
    }
    
    /** 设置 规格图片 属性*/
    public void setPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
    }
    /**获取 序号 属性*/
    public Integer getSortOrder() {
        return sortOrder;
    }
    
    /** 设置 序号 属性*/
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    /**获取 是否设置为默认值。1：是；0：否； 属性*/
    public Byte getIsDefault() {
        return isDefault;
    }
    
    /** 设置 是否设置为默认值。1：是；0：否； 属性*/
    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }
    /**获取 (暂时不用)状态。1：启用；0：不启用； 属性*/
    public Byte getStatus() {
        return status;
    }
    
    /** 设置 (暂时不用)状态。1：启用；0：不启用； 属性*/
    public void setStatus(Byte status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TrendPropertyValue");
        sb.append("{propertyValueId=").append(propertyValueId);
        sb.append(", propertyId=").append(propertyId);
        sb.append(", propertyValue=").append(propertyValue);
        sb.append(", propertyImage=").append(propertyImage);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }
    
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj instanceof TrendPropertyValue) {
            TrendPropertyValue trendPropertyValue = (TrendPropertyValue)obj;
            if(this.getPropertyValueId().equals(trendPropertyValue.getPropertyValueId())){
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        String pkStr = "" + this.getPropertyValueId();
        return pkStr.hashCode();
    }
    
}