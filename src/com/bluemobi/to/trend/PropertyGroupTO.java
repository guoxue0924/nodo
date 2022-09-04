package com.bluemobi.to.trend;

import com.appcore.model.AbstractObject;

/**
 * 商品属性分组View对象
 * 
 * @author zhangzheng
 * @date 2015-10-12
 * 
 */
public class PropertyGroupTO extends AbstractObject {

    private static final long serialVersionUID = 1L;

    private int propertyGroupId;
    // 分组名称
    private String propertyGroupName;
    // 序号
    private int sortOrder;
    // 相关属性关联值（“，”分割）
    private String propertyIds;

    public int getPropertyGroupId() {
        return propertyGroupId;
    }

    public void setPropertyGroupId(int propertyGroupId) {
        this.propertyGroupId = propertyGroupId;
    }

    public String getPropertyGroupName() {
        return propertyGroupName;
    }

    public void setPropertyGroupName(String propertyGroupName) {
        this.propertyGroupName = propertyGroupName;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getPropertyIds() {
        return propertyIds;
    }

    public void setPropertyIds(String propertyIds) {
        this.propertyIds = propertyIds;
    }

}
