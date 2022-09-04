package com.bluemobi.po.trend;

import com.appcore.model.AbstractObject;

/**
 * 【属性资源分组表】持久化对象 数据库表：trend_property_group
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-07-30 10:42:29
 * 
 */
public class TrendPropertyGroup extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Integer propertyGroupId;
    // 分组名称
    private String propertyGroupName;
    // 序号
    private Integer sortOrder;

    /** 获取 属性 */
    public Integer getPropertyGroupId() {
        return propertyGroupId;
    }

    /** 设置 属性 */
    public void setPropertyGroupId(Integer propertyGroupId) {
        this.propertyGroupId = propertyGroupId;
    }

    /** 获取 分组名称 属性 */
    public String getPropertyGroupName() {
        return propertyGroupName;
    }

    /** 设置 分组名称 属性 */
    public void setPropertyGroupName(String propertyGroupName) {
        this.propertyGroupName = propertyGroupName;
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
        sb.append("TrendPropertyGroup");
        sb.append("{propertyGroupId=").append(propertyGroupId);
        sb.append(", propertyGroupName=").append(propertyGroupName);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof TrendPropertyGroup) {
            TrendPropertyGroup trendPropertyGroup = (TrendPropertyGroup) obj;
            if (this.getPropertyGroupId().equals(trendPropertyGroup.getPropertyGroupId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getPropertyGroupId();
        return pkStr.hashCode();
    }

}