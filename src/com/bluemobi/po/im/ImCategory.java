package com.bluemobi.po.im;

import java.util.Date;

import com.appcore.model.AbstractObject;
import com.bluemobi.constant.BaseConstant;

/**
 * 【IM消息分类表】持久化对象 数据库表：im_category
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-03-04 16:28:51
 * 
 */
public class ImCategory extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 主键id
    private Integer categoryId;
    // 分类名称
    private String categoryName;
    // 描述
    private String description;
    // 排序
    private Integer sortOrder;
    // 状态 0：关闭  1：启用
    private Byte status = BaseConstant.STATUS_ENABLED;
    // 创建时间
    private Date ctime;
    // 最后修改时间
    private Date mtime;

    /** 获取 主键id 属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 主键id 属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 分类名称 属性 */
    public String getCategoryName() {
        return categoryName;
    }

    /** 设置 分类名称 属性 */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /** 获取 描述 属性 */
    public String getDescription() {
        return description;
    }

    /** 设置 描述 属性 */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 获取 排序 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 排序 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 状态 0：关闭  1：启用 属性 */
    public Byte getStatus() {
        return status;
    }

    /** 设置 状态 0：关闭  1：启用 属性 */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /** 获取 创建时间 属性 */
    public Date getCtime() {
        return ctime;
    }

    /** 设置 创建时间 属性 */
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    /** 获取 最后修改时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后修改时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ImCategory");
        sb.append("{categoryId=").append(categoryId);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", description=").append(description);
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", status=").append(status);
        sb.append(", ctime=").append(ctime);
        sb.append(", mtime=").append(mtime);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ImCategory) {
            ImCategory imCategory = (ImCategory) obj;
            if (this.getCategoryId().equals(imCategory.getCategoryId())) {
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