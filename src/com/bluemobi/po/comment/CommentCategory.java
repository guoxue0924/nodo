package com.bluemobi.po.comment;


import com.appcore.model.AbstractObject;

/**
 * 【】持久化对象 数据库表：comment_category
 * 
 * @author AutoCode 309444359@qq.com
 * @date 2015-10-21 14:55:27
 * 
 */
public class CommentCategory extends AbstractObject {

    public static final long serialVersionUID = 1L;

    // 
    private Integer categoryId;
    // 
    private String hid;
    // 
    private Integer parentId;
    // 
    private String title;

    /** 获取  属性 */
    public Integer getCategoryId() {
        return categoryId;
    }

    /** 设置  属性 */
    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    /** 获取  属性 */
    public String getHid() {
        return hid;
    }

    /** 设置  属性 */
    public void setHid(String hid) {
        this.hid = hid;
    }

    /** 获取  属性 */
    public Integer getParentId() {
        return parentId;
    }

    /** 设置  属性 */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /** 获取  属性 */
    public String getTitle() {
        return title;
    }

    /** 设置  属性 */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("CommentCategory");
        sb.append("{categoryId=").append(categoryId);
        sb.append(", hid=").append(hid);
        sb.append(", parentId=").append(parentId);
        sb.append(", title=").append(title);
        sb.append('}');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CommentCategory) {
            CommentCategory commentCategory = (CommentCategory) obj;
            if (this.getCategoryId().equals(commentCategory.getCategoryId())) {
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