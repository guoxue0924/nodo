package com.bluemobi.po.goods;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.appcore.model.AbstractObject;

/**
 * 【商品分类表】持久化对象 数据库表：goods_category
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2016-01-26 15:17:33
 * 
 */
public class GoodsCategory extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 分类id
    private Integer categoryId;
    // 父级ID
    private Integer parentId;
    // 分类名称
    private String categoryName;
    // 分类等级
    private Integer grade;
    // 分类图片
    private String image;
    // 描述
    private String description;
    // 序号
    private Integer sortOrder;
    // 是否启用。1：是；0：否；
    private Integer status;
    // 创建时间
    private Date ctime;
    // 最后一次更新时间
    private Date mtime;

    /**
     * 当前分类的 子集分类list
     * 
     * @author haojian
     * @date 2015-12-4 下午12:39:00
     */
    private List<GoodsCategory> subList = new ArrayList<GoodsCategory>();

    /** 获取 分类id 属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置 分类id 属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取 父级ID 属性 */
    public Integer getParentId() {
        return parentId;
    }

    /** 设置 父级ID 属性 */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /** 获取 分类名称 属性 */
    public String getCategoryName() {
        return categoryName;
    }

    /** 设置 分类名称 属性 */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /** 获取 分类等级 属性 */
    public Integer getGrade() {
        return grade;
    }

    /** 设置 分类等级 属性 */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /** 获取 分类图片 属性 */
    public String getImage() {
        return image;
    }

    /** 设置 分类图片 属性 */
    public void setImage(String image) {
        this.image = image;
    }

    /** 获取 描述 属性 */
    public String getDescription() {
        return description;
    }

    /** 设置 描述 属性 */
    public void setDescription(String description) {
        this.description = description;
    }

    /** 获取 序号 属性 */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /** 设置 序号 属性 */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /** 获取 是否启用。1：是；0：否； 属性 */
    public Integer getStatus() {
        return status;
    }

    /** 设置 是否启用。1：是；0：否； 属性 */
    public void setStatus(Integer status) {
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

    /** 获取 最后一次更新时间 属性 */
    public Date getMtime() {
        return mtime;
    }

    /** 设置 最后一次更新时间 属性 */
    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public List<GoodsCategory> getSubList() {
        return subList;
    }

    public void setSubList(List<GoodsCategory> subList) {
        this.subList = subList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("GoodsCategory");
        sb.append("{categoryId=").append(categoryId);
        sb.append(", parentId=").append(parentId);
        sb.append(", categoryName=").append(categoryName);
        sb.append(", grade=").append(grade);
        sb.append(", image=").append(image);
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
        if (obj instanceof GoodsCategory) {
            GoodsCategory goodsCategory = (GoodsCategory) obj;
            if (this.getCategoryId().equals(goodsCategory.getCategoryId())) {
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