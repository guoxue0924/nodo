package com.bluemobi.to.trend;

import com.appcore.model.AbstractObject;

public class PropertyAndPropertyValueTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    // 属性 ID
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
    // 属性序号
    private Integer sortOrder;
    // 是否开启规格（开启后将和价格、库存等相关联）。1：是；0：否；
    private Byte isSpec;
    // 是否是图片属性。1：是；0：否。
    private Byte isImage;
    // 状态。1：启用；0：不启用；
    private Byte status;
    // 属性值ID
    private Integer propertyValueId;
    // 参数可选值
    private String propertyValue;
    // 规格图片
    private String propertyImage;
    // 属性值序号
    private Integer valueSortOrder;
    // 是否设置为默认值。1：是；0：否；
    private Byte isDefault;
    // (暂时不用)状态。1：启用；0：不启用；
    private Byte valueStatus;

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValInputtype() {
        return valInputtype;
    }

    public void setValInputtype(String valInputtype) {
        this.valInputtype = valInputtype;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Byte getIsSpec() {
        return isSpec;
    }

    public void setIsSpec(Byte isSpec) {
        this.isSpec = isSpec;
    }

    public Byte getIsImage() {
        return isImage;
    }

    public void setIsImage(Byte isImage) {
        this.isImage = isImage;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(Integer propertyValueId) {
        this.propertyValueId = propertyValueId;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(String propertyImage) {
        this.propertyImage = propertyImage;
    }

    public Integer getValueSortOrder() {
        return valueSortOrder;
    }

    public void setValueSortOrder(Integer valueSortOrder) {
        this.valueSortOrder = valueSortOrder;
    }

    public Byte getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Byte isDefault) {
        this.isDefault = isDefault;
    }

    public Byte getValueStatus() {
        return valueStatus;
    }

    public void setValueStatus(Byte valueStatus) {
        this.valueStatus = valueStatus;
    }

}
