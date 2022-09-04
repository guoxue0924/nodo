package com.bluemobi.po.trend;

import java.util.LinkedHashMap;
import java.util.Map;

import com.appcore.model.AbstractObject;

/**
 * 【属性资源表】持久化对象 数据库表：trend_property
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-02-22 15:44:50
 * 
 */
public class TrendProperty extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Integer propertyId;
    // 参数名
    private String name;
    // 参数值输入标签类型
    private String valInputtype;
    // 标签名称
    private String labelName;
    // 输入框内部的提示文字
    private String placeholder;
    // 参数说明
    private String note;
    // 序号
    private Integer sortOrder;
    // 是否设为定价属性（设为定价属性后将和价格、库存等相关联）。1：是；0：否；
    private Byte isSpec;
    // 状态。1：启用；0：不启用；
    private Byte status;
    // 是否设为图片属性（设为定价属性后每组sku对应一组图片）。1：是；0：否；
    private Byte isImage;
    // 属性对应的属性值列表
    private Map<Integer, TrendPropertyValue> propertyValueMap = new LinkedHashMap<Integer, TrendPropertyValue>();

    /** 获取 属性 */
    public Integer getPropertyId() {
        return propertyId;
    }

    /** 设置 属性 */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    /** 获取 参数名 属性 */
    public String getName() {
        return name;
    }

    /** 设置 参数名 属性 */
    public void setName(String name) {
        this.name = name;
    }

    /** 获取 参数值输入标签类型 属性 */
    public String getValInputtype() {
        return valInputtype;
    }

    /** 设置 参数值输入标签类型 属性 */
    public void setValInputtype(String valInputtype) {
        this.valInputtype = valInputtype;
    }

    /** 获取 标签名称 属性 */
    public String getLabelName() {
        return labelName;
    }

    /** 设置 标签名称 属性 */
    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    /** 获取 输入框内部的提示文字 属性 */
    public String getPlaceholder() {
        return placeholder;
    }

    /** 设置 输入框内部的提示文字 属性 */
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    /** 获取 参数说明 属性 */
    public String getNote() {
        return note;
    }

    /** 设置 参数说明 属性 */
    public void setNote(String note) {
        this.note = note;
    }

    /** 获取 序号 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 是否设为定价属性（设为定价属性后将和价格、库存等相关联）。1：是；0：否； 属性 */
    public Byte getIsSpec() {
        return isSpec;
    }

    /** 设置 是否设为定价属性（设为定价属性后将和价格、库存等相关联）。1：是；0：否； 属性 */
    public void setIsSpec(Byte isSpec) {
        this.isSpec = isSpec;
    }

    /** 获取 状态。1：启用；0：不启用； 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 状态。1：启用；0：不启用； 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 是否设为图片属性（设为定价属性后每组sku对应一组图片）。1：是；0：否； 属性 */
    public Byte getIsImage() {
        return isImage;
    }

    /** 设置 是否设为图片属性（设为定价属性后每组sku对应一组图片）。1：是；0：否； 属性 */
    public void setIsImage(Byte isImage) {
        this.isImage = isImage;
    }

    public Map<Integer, TrendPropertyValue> getPropertyValueMap() {
        return propertyValueMap;
    }

    public void setPropertyValueMap(Map<Integer, TrendPropertyValue> propertyValueMap) {
        this.propertyValueMap = propertyValueMap;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TrendProperty");
        sb.append("{propertyId=").append(propertyId);
        sb.append(", name=").append(name);
        sb.append(", valInputtype=").append(valInputtype);
        sb.append(", labelName=").append(labelName);
        sb.append(", placeholder=").append(placeholder);
        sb.append(", note=").append(note);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", isSpec=").append(isSpec);
        sb.append(", status=").append(status);
        sb.append(", isImage=").append(isImage);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TrendProperty) {
            TrendProperty trendProperty = (TrendProperty) obj;
            if (this.getPropertyId().equals(trendProperty.getPropertyId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPropertyId();
        return pkStr.hashCode();
    }

}