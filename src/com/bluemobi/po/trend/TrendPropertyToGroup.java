package com.bluemobi.po.trend;

import com.appcore.model.AbstractObject;

/**
 * 【商品属性与分组关系表】持久化对象 数据库表：trend_property_to_group
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-30 10:42:29
 * 
 */
public class TrendPropertyToGroup extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Integer propertyId;
    // 属性分组 ID
    private Integer propertyGroupId;
    // 序号
    private Integer sortOrder;

    /** 获取 属性 */
    public Integer getPropertyId() {
        return propertyId;
    }

    /** 设置 属性 */
    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    /** 获取 属性分组 ID 属性 */
    public Integer getPropertyGroupId() {
        return propertyGroupId;
    }

    /** 设置 属性分组 ID 属性 */
    public void setPropertyGroupId(Integer propertyGroupId) {
        this.propertyGroupId = propertyGroupId;
    }

    /** 获取 序号 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TrendPropertyToGroup");
        sb.append("{propertyId=").append(propertyId);
        sb.append(", propertyGroupId=").append(propertyGroupId);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TrendPropertyToGroup) {
            TrendPropertyToGroup trendPropertyToGroup = (TrendPropertyToGroup) obj;
            if (this.getPropertyId().equals(trendPropertyToGroup.getPropertyId()) && this.getPropertyGroupId().equals(trendPropertyToGroup.getPropertyGroupId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPropertyId() + this.getPropertyGroupId();
        return pkStr.hashCode();
    }

}