package com.bluemobi.po.groupon;

import java.util.Date;

import com.appcore.model.AbstractObject;

/**
 * 【团购标签】持久化对象 数据库表：groupon_category
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-20 16:51:18
 * 
 */
public class GrouponCategory extends AbstractObject {

    public static final long serialVersionUID = 1L;

    //
    private Integer categoryId;
    // 标签名称
    private String title;
    // 上级分类 ID
    private Integer parentId;
    // 分类层级关系以:分隔
    private String hid;
    // 排序
    private Integer sortOrder;
    // 创建时间
    private Date ctime;

    /** 获取 属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 标签名称 属性 */
    public String getTitle() {
        return title;
    }

    /** 设置 标签名称 属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    /** 获取 上级分类 ID 属性 */
    public Integer getParentId() {
        return parentId;
    }

    /** 设置 上级分类 ID 属性 */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /** 获取 分类层级关系以:分隔 属性 */
    public String getHid() {
        return hid;
    }

    /** 设置 分类层级关系以:分隔 属性 */
    public void setHid(String hid) {
        this.hid = hid;
    }

    /** 获取 排序 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 排序 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GrouponCategory");
        sb.append("{categoryId=").append(categoryId);
        sb.append(", title=").append(title);
        sb.append(", parentId=").append(parentId);
        sb.append(", hid=").append(hid);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", ctime=").append(ctime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GrouponCategory) {
            GrouponCategory grouponCategory = (GrouponCategory) obj;
            if (this.getCategoryId().equals(grouponCategory.getCategoryId())) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String pkStr = "" + this.getCategoryId();
        return pkStr.hashCode();
    }

}